class Cell:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.prev = None
        self.g = 0
        self.h = 0
        self.f = 0
        self.hn = -1
        
    def __str__(self):
        return "{} {}".format(self.x, self.y)
    def __repr__(self):
        return str(self)
        