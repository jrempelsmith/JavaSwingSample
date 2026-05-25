/**
 * Represents the width and height of an object.
 */
public class Size {
    // =====================================================
    // SIZE CLASS
    // =====================================================

    private final int width;
    private final int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

}
