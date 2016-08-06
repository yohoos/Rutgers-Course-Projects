import pygame
from Astar import Astar
from AstarB import AstarB
from AstarGsmall import AstarGsmall
from AAstar import AAstar
from Cell import Cell
from Grid import Grid
import numpy as np

class Maze:
    def __init__(self, size, grid):
        # Defines some colors
        BLACK = (0, 0, 0)
        WHITE = (255, 255, 255)
        GREEN = (0, 255, 0)
        RED = (255, 0, 0)
        BLUE = (0, 0, 255)
        PURPLE = (138, 0 ,138)
        YELLOW = (255, 255, 0)
        PINK = (255, 192, 203)
        
        # Sets width and height of each grid block
        WIDTH = 5
        HEIGHT = 5
        
        # Sets margin between each cell block
        MARGIN = 1
        
        pygame.init()
         
        # Set the width and height of the screen [width, height]
        dimensions = (619, 619)
        screen = pygame.display.set_mode(dimensions)
        
        pygame.display.set_caption("My Maze")
         
        # Loop until the user clicks the close button.
        done = False
         
        # Used to manage how fast the screen updates
        clock = pygame.time.Clock()
         
        # -------- Main Program Loop -----------
        while not done:
            # --- Main event loop
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    done = True
         
            # background image.
            screen.fill(GREEN)
            
            # --- Drawing the grid
            for row in range(size):
                for column in range(size):
                    color = WHITE
                    if grid[row, column] == 1:
                        color = BLACK
                    if grid[row, column] == 2:
                        color = PINK
#                     if grid[row, column] == 3:
#                         color = YELLOW
                    if grid[row, column] == 4:
                        color = YELLOW
                    if grid[row, column] == 5:
                        color = BLUE
                    if grid[row, column] == 6:
                        color = RED
                    pygame.draw.rect(screen,
                                     color,
                                     [(MARGIN + WIDTH) * column + MARGIN,
                                      (MARGIN + HEIGHT) * row + MARGIN,
                                      WIDTH,
                                      HEIGHT])

            
            # --- update the screen with what we've drawn.
            pygame.display.flip()
            
            # --- Limit to 60 frames per second
            clock.tick(60)
         
        # Close the window and quit.
        pygame.quit()
        
if __name__ == '__main__':
    # 1 is Forward Repeated A*, 2 is Forward Repeated A* favoring small g-values, 3 is Backward Repeated A*
    size = 103
    start = Cell(np.random.randint(1, size-1), np.random.randint(0, size-1))
    end = Cell(np.random.randint(1, size-1), np.random.randint(0, size-1))
    # nonrandom start and finish
#     start = Cell(1, 1)
#     end = Cell(size-2, size-2)
    maze = Grid(size).get_grid()
    # special test modificiation
#     maze[5, 4] = 1
#     maze[4,4] = 1
#     maze[4,3] = 1
#     maze[3,4] = 1
#     maze[3,3] = 1
#     maze[2,3] = 1
    maze2 = np.copy(maze)
    maze3 = np.copy(maze)
    maze4 = np.copy(maze)
    # Repeated Forward
    alg1 = Astar(size, start, end, maze)
    grid = alg1.get_grid()
    Maze(size, grid)
    # Repeated Forward favoring small g
    alg2 = AstarGsmall(size, start, end, maze2)
    grid2 = alg2.get_grid()
    Maze(size, grid2)
    # Repeated Backward
    alg3 = AstarB(size, end, start, maze3)
    grid3 = alg3.get_grid()
    Maze(size, grid3)
    # Adaptive A*
    alg4 = AAstar(size, start, end, maze4)
    grid4 = alg4.get_grid()
    Maze(size, grid4)
    