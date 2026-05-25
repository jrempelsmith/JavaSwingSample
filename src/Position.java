/**
 * Represents one grid coordinate.
 */
public class Position {
    // =====================================================
    // POSITION CLASS
    // =====================================================

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
