# picture-scratchoff
My solution for Chapter 13 Exercise 1 of “Introduction to Programming Using Java”.

NOTE: This is a javafx program. It requires the javafx library as a dependency. (See bottom of this README for javafx instructions).

Problem Description:
The folder nature-images contains several pictures of animals. (In the web site download,
you can find that folder in the chapter13 directory inside the source directory.) Write
a “scratch off” program that could be used by a small child that works as follows: The
program window starts by showing a large uniformly colored rectangle, with one of the
animal pictures hidden behind it. As the user drags the mouse over the image, part of the
colored overlay is scratched off, revealing the picture underneath.
You can implement this by using one canvas, containing the colored overlay, stacked on
top of another canvas that contains the animal picture. (Stacked canvases were used in
the sample program ToolPaint.java from Subsection 13.2.4.) To implement scratching off
part of the overlay, just clear a small rect in the overlay canvas around the mouse location.
The program should have some way to move on to the next picture. Another idea is to
have several different sizes of scratchers, so that an impatient child can use a giant one
that will remove large swatches of color.

Javafx setup instructions:
Download javafx from: https://gluonhq.com/products/javafx/ (I used javafx 12). Save it to a location of your choice.
Unpack the zip folder.
Open my project with your IDE of choice (I use intellij IDEA).
Add the javafx/lib folder as an external library for the project. For intellij, this means going to "project structure" -> "libraries" -> "add library" ->{javafx location}/lib
Add the following as a VM argument for the project: --module-path "{full path to your javafx/lib folder}" --add-modules javafx.controls,javafx.fxml,javafx.base,javafx.graphics,javafx.media,javafx.swing,javafx.web
Build and run the project as normal.
