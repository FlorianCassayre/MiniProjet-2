package platform.game.util;

/**
 * A cardinal direction.
 */
public enum Direction
{
    LEFT(0),
    UP(1),
    RIGHT(2),
    DOWN(3);

    private final int angle;

    Direction(int angle)
    {
        this.angle = angle;
    }

    /**
     * Returns the angle created by this direction.
     * @return an integer
     */
    public int getAngle()
    {
        return angle;
    }
}
