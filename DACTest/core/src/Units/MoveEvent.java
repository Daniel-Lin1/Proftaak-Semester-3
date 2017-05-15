package Units;

/**
 * Created by Daniel on 15-5-2017.
 */
public class MoveEvent {
    private final double xPos;
    private final double yPos;

    public MoveEvent(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public double getXpos() {
        return xPos;
    }

    public double getYpos() {
        return yPos;
    }
}
