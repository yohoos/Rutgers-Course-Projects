# import random
import numpy as np
from maze import maze

class Grid:
    def __init__(self, size):
        self.size = size
        self.maze = maze(size, size, 0.75, 0.03)
        
    def get_grid(self):
        # generate grid with 30% blocked
        grid = np.array([[0 for i in range(self.size)] for i in range(self.size)])
        for row in range(self.size):
            for col in range(self.size):
                if self.maze[row, col] == True:
                    grid[row, col] = 1
                else:
                    grid[row][col] = 0
                
#         blocked_cells = set()
#         numOfblockedCells = self.size*self.size*0.3
# 
#         while len(blocked_cells) <= numOfblockedCells:
#             newElement = (random.randint(0,self.size-1), random.randint(0,self.size-1))
#             blocked_cells.add(newElement)
#         
#         for cell in blocked_cells:
#             grid[cell[0], cell[1]] = 1
        return grid
    
if __name__ == "__main__":
    test = Grid(101)
    print test.get_grid()