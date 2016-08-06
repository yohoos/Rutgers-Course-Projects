import heapq
from Cell import Cell

class GlobalA:
    def __init__(self, size, start, end, grid):
        # 0 means open, 1 means blocked, 2 means visited, 3 means in heap, 4 means in path, 5 means start, 6 means end
        self.grid = grid
        # initialize variables
        self.size = size
        self.moves = 0
        self.cost = 10
        self.heap = []
        self.cells = [] # to help with manipulating cells
        self.start = start
        self.end = end
        
        # preprocessing
        self.initialize()
        self.current = None
        heapq.heappush(self.heap, (self.start.f, -self.start.g, self.start)) # allows expansion of first cell
        self.grid[self.start.x, self.start.y] = 2 # update grid to indicate visited
        # runs A* algorithm
        self.solve()
        # labels shortest path
        self.setShortestPath()
        # distinguish start and end positions
        self.grid[self.start.x, self.start.y] = 5
        self.grid[self.end.x, self.end.y] = 6
        print self.moves
        
    def solve(self):
        while True:
            if len(self.heap) == 0:
                print "No Solution" # May not need in final implementation
                break
            min = heapq.heappop(self.heap) # retrieve next best cell
            self.moves += 1
            self.current = min[2]
            if self.current.x == self.end.x and self.current.y == self.end.y:
                break
            self.grid[self.current.x, self.current.y] = 2 # min[0]
            self.add_neighbors(self.current)
        
    def initialize(self):
        # generate start and end cell
        print "start", (self.start.x, self.start.y)
        print "end", (self.end.x, self.end.y)
        # initialize cells list
        for x in range(self.size):
            for y in range(self.size):
                self.cells.append(Cell(x,y))
                
        self.start = self.get_cell(self.start.x, self.start.y)
        self.end = self.get_cell(self.end.x, self.end.y)
        self.setH(self.start)
        self.start.g = 0
        self.setF(self.start)
        # update grid
        self.grid[self.start.x, self.start.y] = 0
        self.grid[self.end.x, self.end.y] = 0
    
    def get_cell(self, x, y):
        cell = self.cells[x * self.size + y]
        return cell
    
    def setH(self, cell):
        cell.h = self.cost*(abs(self.end.x - cell.x) + abs(self.end.y - cell.y))
    
    def setF(self, cell):
        cell.f = cell.h + cell.g
        
    def setCell(self, cell, prev):
        self.setH(cell)
        cell.g = prev.g + 10
        self.setF(cell)
        cell.prev = prev
    
    def make_neighbors(self, cell):
        cells = []
        if cell.x > 0 and self.grid[cell.x-1,cell.y] != 1:
            cells.append(self.get_cell(cell.x-1, cell.y))
        if cell.x < self.size-1 and self.grid[cell.x+1,cell.y] != 1:
            cells.append(self.get_cell(cell.x+1, cell.y))
        if cell.y > 0 and self.grid[cell.x,cell.y-1] != 1:
            cells.append(self.get_cell(cell.x, cell.y-1))
        if cell.y < self.size-1 and self.grid[cell.x,cell.y+1] != 1:
            cells.append(self.get_cell(cell.x, cell.y+1))
        return cells
            
    def add_neighbors(self, cell):
        cells = self.make_neighbors(cell)
        for adj in cells:
            if self.grid[adj.x, adj.y] == 3 and adj.g > cell.g+10:
                self.setCell(adj, cell)
                heapq.heappush(self.heap, (adj.f, -adj.g, adj))
            
            if self.grid[adj.x, adj.y] == 0:
                self.setCell(adj, cell)
                heapq.heappush(self.heap, (adj.f, -adj.g, adj))
                self.grid[adj.x, adj.y] = 3
                
    def setShortestPath(self):
        cell = self.current
        while cell.x != self.start.x or cell.y != self.start.y:
            cell = cell.prev
            self.grid[cell.x, cell.y] = 4
    
    def get_grid(self):
        return self.grid
        