Sample program using Java Swing to get keyboard input and draw shapes on a grid.

Use the program as follows:

In Game.updateGameState():
- call GridCanvas.getInputState() to read live keyboard input.
- - modify what keyboard input can be received by modifying the GridCanvas constructor and InputState.java.
- add any other logic here involving objects in your program.

In Game.redrawVisuals():
- call the following methods to draw shapes on the grid:
- - GridCanvas.drawRectangle()
- - GridCanvas.drawOval()
- - GridCanvas.drawRectangles()
- - GridCanvas.drawOvals()
- - GridCanvas.drawLine()
