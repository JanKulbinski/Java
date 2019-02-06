The program creates simulation using multithreading.

Simulation approach and assumptions:
The board for simulation is a rectangle that consists of smaller rectangles. Board's size (width and height), which
determines number of columns and rows, velocity (v) and probability (p) of changing color are
entered by user as parameters. The initial colors of rectangles are random.
Each field is a thread that every now and then (the delay is equal to a random
number of milliseconds in the range from 0.5*v to 1.5*v) performs the following actions:
   - with probability p, change its color for random;
   - with probability 1 - p checks the colors of its four neighbors (board
   it is treated as a two-dimensional torus) and changes it's color for a medium color of them.

The application can change its size.