public class InputState {
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean ePressed;

    public boolean isUpPressed() {
        return upPressed;
    }
    public boolean isDownPressed() {
        return downPressed;
    }
    public boolean isLeftPressed() {
        return leftPressed;
    }
    public boolean isRightPressed() {
        return rightPressed;
    }
    public boolean isEPressed() {
        return ePressed;
    }

    public void setUpStatus(boolean status) {
        upPressed = status;
    }
    public void setDownStatus(boolean status) {
        downPressed = status;
    }
    public void setLeftStatus(boolean status) {
        leftPressed = status;
    }
    public void setRightStatus(boolean status) {
        rightPressed = status;
    }
    public void setEStatus(boolean status) {
        ePressed = status;
    }
}
