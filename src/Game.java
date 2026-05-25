import java.awt.*;

import java.util.ArrayList;

public class Game {

    // The graphical user interface (GUI) for the game
    private GridCanvas canvas;

    // Tracks which keys are currently pressed. Updated in the main loop.
    private InputState inputState;

    private Boundary boundary;

    private Player player;
    private Enemy enemy;
    private ArrayList<Position> coinPositions;

    public Game() {
        // =========================================
        // EXAMPLE GAME/SIMULATION STATE
        // =========================================

        player = new Player(new Position(5, 15), new Size(2, 2));
        enemy = new Enemy(new Position(8, 8), new Size(3, 3));

        // number of rows and columns
        boundary = new Boundary(32, 32);

        // Create drawing canvas
        canvas = new GridCanvas(boundary, 10, "GridCanvas Example");

        canvas.showInWindow();

        // Batch drawing example
        coinPositions = new ArrayList<>();

        coinPositions.add(new Position(1, 1));
        coinPositions.add(new Position(8, 5));
        coinPositions.add(new Position(11, 5));
        coinPositions.add(new Position(14, 5));
    }

    public void run() {

        // =========================================
        // LOOP TO UPDATE GAME STATE EACH FRAME
        // =========================================

        while (true) {

            // =========================================
            // PART 1: UPDATE GAME STATE (LOGIC ONLY)
            // =========================================
            updateGameState();


            // -------------------------------------
            // PART 2: REDRAW SCREEN (VISUALS ONLY)
            // -------------------------------------
            redrawVisuals();


            // -------------------------------------
            // PART 3: PAUSE MOMENTARILY EVERY LOOP
            // -------------------------------------
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void updateGameState() {
        // get input
        inputState = canvas.getInputState();

        // respond to input for player
        if (inputState.isLeftPressed()) {
            player.moveLeft(boundary);
        }
        if (inputState.isRightPressed()) {
            player.moveRight(boundary);
        }
        if (inputState.isUpPressed()) {
            player.moveUp(boundary);
        }
        if (inputState.isDownPressed()) {
            player.moveDown(boundary);
        }

        // logic to move enemy automatically
        enemy.moveLeft(boundary);
    }

    private void redrawVisuals() {
        canvas.clear();

        // Player
        canvas.drawRectangle(player.getPosition(), player.getSize(), Color.RED, GridCanvas.DrawStyle.FILLED);

        // Enemy
        canvas.drawOval(enemy.getPosition(), enemy.getSize(), Color.BLUE, GridCanvas.DrawStyle.OUTLINED);

        // Coins
        canvas.drawOvals(coinPositions, new Size(1, 1), Color.YELLOW, GridCanvas.DrawStyle.FILLED);

        // Line example
        canvas.drawLine(new Position(0, 0), new Position(8, 5), Color.BLACK);

        canvas.redraw();
    }
}
