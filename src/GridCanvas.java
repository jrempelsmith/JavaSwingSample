import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * GridCanvas
 *
 * A reusable Swing drawing class for beginner/intermediate
 * Java projects.
 *
 * Features:
 * - Integer grid coordinates
 * - Configurable grid size
 * - Filled or outlined shapes
 * - Batch drawing support
 * - Line drawing support
 * - Validation + exceptions
 *
 * Supported Shapes:
 * - Rectangles
 * - Ovals
 * - Lines
 */
public class GridCanvas extends JPanel {

    // =====================================================
    // FIELDS
    // =====================================================

    // Java Swing class for a GUI window
    private final JFrame window;

    // stores the keyboard input
    private final InputState inputState;

    private final int gridWidth;
    private final int gridHeight;

    private final int cellSize;

    private final List<DrawableShape> shapes;
    private final List<DrawableLine> lines;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    /**
     * Creates a new grid canvas.
     *
     * @param boundary container for the number of horizontal and vertical cells
     * @param cellSize size of each cell in pixels
     */
    public GridCanvas(Boundary boundary, int cellSize, String title) {

        validateGridValues(boundary.getGridWidth(), boundary.getGridHeight(), cellSize);

        this.gridWidth = boundary.getGridWidth();
        this.gridHeight = boundary.getGridHeight();

        this.cellSize = cellSize;

        this.shapes = new ArrayList<GridCanvas.DrawableShape>();
        this.lines = new ArrayList<GridCanvas.DrawableLine>();

        this.window = new JFrame(title);
        window.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {

                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        inputState.setUpStatus(true);
                        break;

                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        inputState.setDownStatus(true);
                        break;

                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        inputState.setLeftStatus(true);
                        break;

                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        inputState.setRightStatus(true);
                        break;

                    case KeyEvent.VK_E:
                        inputState.setEStatus(true);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                switch (e.getKeyCode()) {

                    case KeyEvent.VK_W:
                    case KeyEvent.VK_UP:
                        inputState.setUpStatus(false);
                        break;

                    case KeyEvent.VK_S:
                    case KeyEvent.VK_DOWN:
                        inputState.setDownStatus(false);
                        break;

                    case KeyEvent.VK_A:
                    case KeyEvent.VK_LEFT:
                        inputState.setLeftStatus(false);
                        break;

                    case KeyEvent.VK_D:
                    case KeyEvent.VK_RIGHT:
                        inputState.setRightStatus(false);
                        break;

                    case KeyEvent.VK_E:
                        inputState.setEStatus(false);
                        break;
                }
            }
        });

        this.inputState = new InputState(); // stores updated WASD input

        int pixelWidth = gridWidth * cellSize;
        int pixelHeight = gridHeight * cellSize;

        setPreferredSize(new Dimension(pixelWidth, pixelHeight));

        setBackground(Color.WHITE);
    }

    /**
     * Allows polling of input state.
     */
    public InputState getInputState() {
        return inputState;
    }

    // =====================================================
    // PUBLIC DRAW METHODS
    // =====================================================

    /**
     * Draws one rectangle.
     */
    public void drawRectangle(Position pos, Size size, Color color, DrawStyle drawStyle) {
        addShape(pos.X(), pos.Y(), size.width(), size.height(), color, ShapeType.RECTANGLE, drawStyle);
    }

    /**
     * Draws one oval.
     */
    public void drawOval(Position pos, Size size, Color color, DrawStyle drawStyle) {
        addShape(pos.X(), pos.Y(), size.width(), size.height(), color, ShapeType.OVAL, drawStyle);
    }

    /**
     * Draws one line.
     */
    public void drawLine(Position start, Position end, Color color) {

        validateLine(start, end, color);
        DrawableLine line = new DrawableLine(start, end, color);
        lines.add(line);
    }

    /**
     * Draws many rectangles.
     */
    public void drawRectangles(List<Position> positions, Size size, Color color, DrawStyle drawStyle) {

        validatePositionList(positions);

        for (Position position : positions) {
            drawRectangle(position, size, color, drawStyle);
        }
    }

    /**
     * Draws many ovals.
     */
    public void drawOvals(List<Position> positions, Size size, Color color, DrawStyle drawStyle) {

        validatePositionList(positions);

        for (Position position : positions) {
            drawOval(position, size, color, drawStyle);
        }
    }

    /**
     * Removes all shapes and lines.
     */
    public void clear() {
        shapes.clear();
        lines.clear();
    }

    /**
     * Redraws the screen.
     */
    public void redraw() {
        repaint();
    }

    /**
     * Opens the canvas in a window.
     */
    public void showInWindow() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setFocusable(true);
        window.requestFocusInWindow();
    }

    // =====================================================
    // PRIVATE METHODS
    // =====================================================

    /**
     * Adds one validated shape.
     */
    private void addShape(int x, int y, int width, int height, Color color, ShapeType shapeType, DrawStyle drawStyle) {
        validateShapeValues(x, y, width, height, color, drawStyle);
        DrawableShape shape = new DrawableShape(x, y, width, height, color, shapeType, drawStyle);
        shapes.add(shape);
    }

    /**
     * Validates grid constructor values.
     */
    private void validateGridValues(int gridWidth, int gridHeight, int cellSize) {

        if (gridWidth <= 0) {

            System.out.println("ERROR: Grid width must be greater than 0.");
            throw new IllegalArgumentException("Grid width must be greater than 0.");
        }

        if (gridHeight <= 0) {
            System.out.println("ERROR: Grid height must be greater than 0.");
            throw new IllegalArgumentException("Grid height must be greater than 0.");
        }

        if (cellSize <= 0) {
            System.out.println("ERROR: Cell size must be greater than 0.");
            throw new IllegalArgumentException("Cell size must be greater than 0.");
        }
    }

    /**
     * Validates shape values.
     */
    private void validateShapeValues(int x, int y, int width, int height, Color color, DrawStyle drawStyle) {

        if (x < 0 || y < 0) {

            System.out.println("ERROR: Shape position cannot be negative.");
            throw new IllegalArgumentException("Shape position cannot be negative.");
        }

        if (width <= 0 || height <= 0) {
            System.out.println("ERROR: Shape width and height must be greater than 0.");
            throw new IllegalArgumentException("Shape width and height must be greater than 0.");
        }

        if (color == null) {
            System.out.println("ERROR: Color cannot be null.");
            throw new IllegalArgumentException("Color cannot be null.");
        }

        if (drawStyle == null) {
            System.out.println("ERROR: Draw style cannot be null.");
            throw new IllegalArgumentException("Draw style cannot be null.");
        }

        if (x + width > gridWidth || y + height > gridHeight) {

            System.out.println("ERROR: Shape exceeds grid boundaries.");
            throw new IllegalArgumentException("Shape exceeds grid boundaries.");
        }
    }

    /**
     * Validates a list of positions.
     */
    private void validatePositionList(List<Position> positions) {

        if (positions == null) {
            System.out.println("ERROR: Position list cannot be null.");
            throw new IllegalArgumentException("Position list cannot be null.");
        }

        for (Position position : positions) {
            if (position == null) {
                System.out.println("ERROR: Position cannot be null.");
                throw new IllegalArgumentException("Position cannot be null.");
            }
        }
    }

    /**
     * Validates line values.
     */
    private void validateLine(Position start, Position end, Color color) {
        if (start == null || end == null) {
            System.out.println("ERROR: Line positions cannot be null.");
            throw new IllegalArgumentException("Line positions cannot be null.");
        }

        if (color == null) {
            System.out.println("ERROR: Line color cannot be null.");
            throw new IllegalArgumentException("Line color cannot be null.");
        }
    }

    // =====================================================
    // PAINTING
    // =====================================================

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGrid(g2);
        drawShapes(g2);
        drawLines(g2);
    }

    /**
     * Draws the background grid.
     */
    private void drawGrid(Graphics2D g2) {

        g2.setColor(Color.LIGHT_GRAY);

        // Vertical lines
        for (int x = 0; x <= gridWidth; x++) {

            int pixelX = x * cellSize;

            g2.drawLine(pixelX, 0, pixelX, gridHeight * cellSize);
        }

        // Horizontal lines
        for (int y = 0; y <= gridHeight; y++) {
            int pixelY = y * cellSize;
            g2.drawLine(0, pixelY, gridWidth * cellSize, pixelY);
        }
    }

    /**
     * Draws all stored shapes.
     */
    private void drawShapes(Graphics2D g2) {

        for (DrawableShape shape : shapes) {

            g2.setColor(shape.color);

            int pixelX = shape.x * cellSize;
            int pixelY = shape.y * cellSize;

            int pixelWidth = shape.width * cellSize;
            int pixelHeight = shape.height * cellSize;

            switch (shape.shapeType) {

                case RECTANGLE:
                    if (shape.drawStyle == DrawStyle.FILLED) {
                        g2.fillRect(pixelX, pixelY, pixelWidth, pixelHeight);
                    }
                    else {
                        g2.drawRect(pixelX, pixelY, pixelWidth, pixelHeight);
                    }
                    break;

                case OVAL:
                    if (shape.drawStyle == DrawStyle.FILLED) {
                        g2.fillOval(pixelX, pixelY, pixelWidth, pixelHeight);
                    }
                    else {
                        g2.drawOval(pixelX, pixelY, pixelWidth, pixelHeight);
                    }
                    break;
            }
        }
    }

    /**
     * Draws all stored lines.
     */
    private void drawLines(Graphics2D g2) {

        for (DrawableLine line : lines) {

            g2.setColor(line.color);

            int startX = line.start.X() * cellSize;
            int startY = line.start.Y() * cellSize;
            int endX = line.end.X() * cellSize;
            int endY = line.end.Y() * cellSize;

            g2.drawLine(startX, startY, endX, endY);
        }
    }


    // =====================================================
    // ENUMS
    // =====================================================

    public enum ShapeType {
        RECTANGLE,
        OVAL
    }

    public enum DrawStyle {
        FILLED,
        OUTLINED
    }


    // =====================================================
    // HELPER CLASSES (used internally only)
    // =====================================================

    /**
     * Stores one drawable shape.
     * This is a helper class for GridCanvas; you can't use it outside the GridCanvas class.
     * To draw a shape on the grid, call the draw methods (drawRectangle, drawOval, drawRectangles, drawOvals)
     */
    private static class DrawableShape {

        private int x;
        private int y;

        private int width;
        private int height;

        private Color color;
        private ShapeType shapeType;
        private DrawStyle drawStyle;

        public DrawableShape(int x, int y, int width, int height, Color color, ShapeType shapeType, DrawStyle drawStyle) {
            this.x = x;
            this.y = y;

            this.width = width;
            this.height = height;

            this.color = color;
            this.shapeType = shapeType;
            this.drawStyle = drawStyle;
        }
    }

    /**
     * Stores one drawable line.
     * This is a helper class for GridCanvas; you can't use it outside the GridCanvas class.
     * To draw a line on the grid, call the draw methods (drawLine, drawLines)
     */
    private static class DrawableLine {

        private Position start;
        private Position end;

        private Color color;

        public DrawableLine(Position start, Position end, Color color) {
            this.start = start;
            this.end = end;

            this.color = color;
        }
    }
}
