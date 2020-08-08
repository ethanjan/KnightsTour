# Knight's Tour
This program solves the infamous Knight's Tour problem using an appropriate implementation of Warnsdorff's rule.

## Implementation
This software utilizes the Warnsdorff's rule to solve the Knight's Tour problem. When utilizing this rule, each square of the chessboard is denoted by a certain accessibility value. The squares that are closer to the center of the board are given higher accessibility values because they can be accessed from a greater number of squares. When the Knight moves to a new square, the accessibility value of that square is set to zero, and the accessibility values of all the squares that can be reached by the knight from its current position are decreased by one (in order to reflect the fact that the knight is being restricted in its range of motion). If there are ties in the accessibility values, this program will randomly choose a square using Java's built-in random number generator.

## Prerequisites
Because this file loads its accessibility values from the file access.txt, this file is a necessary requisite in order for this program to properly function. Aside from this file, the only prerequisites include knowing how to utilize the software.

## Usage
In order to utilize this program, it is necessary to download this project and Eclipse and to import this project within Eclipse. Afterwards, it is necessary to run this program within Eclipse.