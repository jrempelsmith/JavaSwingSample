public class Player {
    private Position position;
    private Size size;

    public Player(Position pos, Size size) {
        this.position = pos;
        this.size = size;
    }

    public void moveLeft(Boundary boundary) {
        int newX;
        if (position.X() < 1) {
            newX = boundary.getGridWidth() - size.width();
        }
        else {
            newX = position.X() - 1;
        }
        position.setPosition(newX, position.Y());
    }

    public void moveRight(Boundary boundary) {
        int newX;
        if (position.X() + size.width() > boundary.getGridWidth() - 1) {
            newX = 0;
        }
        else {
            newX = position.X() + 1;
        }
        position.setPosition(newX, position.Y());
    }

    public void moveUp(Boundary boundary) {
        int newY;
        if (position.Y() < 1) {
            newY = boundary.getGridHeight() - size.height();
        }
        else {
            newY = position.Y() - 1;
        }
        position.setPosition(position.X(), newY);
    }

    public void moveDown(Boundary boundary) {
        int newY;
        if (position.Y() + size.height() > boundary.getGridHeight() - 1) {
            newY = 0;
        }
        else {
            newY = position.Y() + 1;
        }
        position.setPosition(position.X(), newY);
    }

    public Position getPosition() {
        return position;
    }
    public Size getSize() {
        return size;
    }
}
