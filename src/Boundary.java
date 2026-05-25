public class Boundary {
    private final int gridWidth;
    private final int numRows;

    public Boundary(int gridWidth, int numRows) {
        this.gridWidth = gridWidth;
        this.numRows = numRows;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return numRows;
    }
}
