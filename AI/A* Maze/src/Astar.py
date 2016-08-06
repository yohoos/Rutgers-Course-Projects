import heapq
import numpy as np
from Cell import Cell
from sys import getsizeof

class Astar:
    def __init__(self, size, start, end, grid):
        # 0 means open, 1 means blocked, 2 means visited, 3 means in heap, 4 means in path, 5 means start, 6 means end
        self.grid = grid
        self.size = size
        self.origin = start # original start position
        self.start = start # also current agent position
        self.end = end
        print "start", (self.start.x, self.start.y)
        print "end", (self.end.x, self.end.y)
        self.grid[self.start.x, self.start.y] = 0
        self.grid[self.end.x, self.end.y] = 0
        # declare variables
        self.solvable = True
        self.moves = 0
        # initialize cells list
        self.cells = []
        for x in range(self.size):
            for y in range(self.size):
                self.cells.append(Cell(x,y))
        # creates outer wall rim to as the original maze generator did
        self.blocked = np.zeros((self.size, self.size), np.int)
        self.blocked[0] = 1; self.blocked[self.size-1] = 1; self.blocked[:, 0] = 1; self.blocked[:, self.size-1] = 1;
        self.check_neighbors(self.start)
        
        # preprocessing
        while True:
            self.heap = []
            self.current = None # current A* position
            self.initialize()
            heapq.heappush(self.heap, (self.current.f, -self.current.g, self.current)) # allows expansion of first cell
            self.map = np.copy(self.blocked)
            self.map[self.current.x, self.current.y] = 2 # update grid to indicate visited
            # runs A* algorithm
            self.solve()
            if self.solvable == False:
                break
            # labels shortest presumed unblocked path
            path = self.setShortestPath()
            self.move(path)
            if self.start.x == self.end.x and self.start.y == self.end.y:
                print "Success!"
                break
#         distinguish start and end positions
        self.grid[self.origin.x, self.origin.y] = 5
        self.grid[self.end.x, self.end.y] = 6
        print self.moves
        
    def solve(self):
        while True:
            if len(self.heap) == 0:
                print "No Solution" # May not need in final implementation
                self.solvable = False # May not need in final implementation
                break
            min = heapq.heappop(self.heap) # retrieve next best cell
            self.moves += 1
            self.current = min[2]
            if self.current.x == self.end.x and self.current.y == self.end.y:
                break
            self.map[self.current.x, self.current.y] = 2 # min[0]
            self.add_neighbors(self.current)
        
    def initialize(self):
        self.current = self.get_cell(self.start.x, self.start.y)
        self.setH(self.current)
        self.current.g = 0
        self.setF(self.current)
    
    def get_cell(self, x, y):
        cell = self.cells[x * self.size + y]
        return cell
    
    def setH(self, cell):
        cell.h = (abs(self.end.x - cell.x) + abs(self.end.y - cell.y))
    
    def setF(self, cell):
        cell.f = cell.h + cell.g
        
    def setCell(self, cell, prev):
        self.setH(cell)
        cell.g = prev.g + 1
        self.setF(cell)
        cell.prev = prev
    
    def check_neighbors(self, cell): # use with move()
        if cell.x > 1 and self.grid[cell.x-1,cell.y] == 1:
            self.blocked[cell.x-1, cell.y] = 1
        if cell.x < self.size-2 and self.grid[cell.x+1,cell.y] == 1:
            self.blocked[cell.x+1, cell.y] = 1
        if cell.y > 1 and self.grid[cell.x,cell.y-1] == 1:
            self.blocked[cell.x, cell.y-1] = 1
        if cell.y < self.size-2 and self.grid[cell.x,cell.y+1] == 1:
            self.blocked[cell.x, cell.y+1] = 1
    
    def make_neighbors(self, cell):
        cells = []
        if cell.x > 1 and self.map[cell.x-1, cell.y] != 1:
            cells.append(self.get_cell(cell.x-1, cell.y))
        if cell.x < self.size-2 and self.map[cell.x+1, cell.y] != 1:
            cells.append(self.get_cell(cell.x+1, cell.y))
        if cell.y > 1 and self.map[cell.x, cell.y-1] != 1:
            cells.append(self.get_cell(cell.x, cell.y-1))
        if cell.y < self.size-2 and self.map[cell.x, cell.y+1] != 1:
            cells.append(self.get_cell(cell.x, cell.y+1))
        return cells
            
    def add_neighbors(self, cell):
        cells = self.make_neighbors(cell)
        for adj in cells:
            if self.map[adj.x, adj.y] == 3 and adj.g > cell.g+1:
                self.setCell(adj, cell)
                heapq.heappush(self.heap, (adj.f, -adj.g, adj))
            
            if self.map[adj.x, adj.y] == 0:
                self.setCell(adj, cell)
                heapq.heappush(self.heap, (adj.f, -adj.g, adj))
                self.map[adj.x, adj.y] = 3
                
    def setShortestPath(self):
        path = []
        cell = self.current
        path.append(cell)
        while cell.x != self.start.x or cell.y != self.start.y:
            cell = cell.prev
            path.append(cell)
        path.reverse()
        return path

    def move(self, path):
        for cell in path:
            if self.grid[cell.x, cell.y] != 1:
                self.grid[cell.x, cell.y] = 4
                self.check_neighbors(cell)
                self.start = cell
            else:
                self.start = cell.prev
                break
    
    def get_grid(self):
        return self.grid
        