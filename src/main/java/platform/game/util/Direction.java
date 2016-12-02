package platform.game.util;

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

    public int getAngle()
    {
        return angle;
    }
}
