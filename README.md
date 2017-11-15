Suppose you have a color picture consisting of a 2-dimensional array A
of pixels, where each pixel specifies a triple of integers
representing the red, green, and blue (RGB) intensities. Suppose that
we wish to compress this picture slightly. Specifically, we wish to
remove one pixel from each of the rows, so that the whole picture
becomes one pixel narrower. To avoid disturbing visual effects,
however, we require that the pixels removed in two adjacent rows be in
the same or adjacent columns; the pixels removed form a “seam” from
the top row to the bottom row where successive pixels in the seam are
adjacent vertically or diagonally.

Along with each pixel `A[i][j]`, there is a 2D-array for the
disruption measure `D[i][j]`, indicating how disruptive it would be to
remove pixel `A[i][j]`. Intuitively, the lower a pixel’s disruption
measure, the more similar the pixel is to its neighbors. Suppose
further that we define the disruption measure of a seam to be the sum
of the disruption measures of its pixels.

Complete the `SeamCarving.carve_seam` function, which takes a disruption
matrix as its input, to implement an algorithm that finds a seam
with the lowest disruption measure. Pay special attention to the
efficiency of your algorithm.  Your solution should be linear in time
with respect to the number of pixels in the image.

This lab is adapted from Problem 15-8 in Cormen, Thomas
H. Introduction to Algorithms (Page 409). MIT Press. Kindle Edition.
