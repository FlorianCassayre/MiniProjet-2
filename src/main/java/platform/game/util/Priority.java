package platform.game.util;

/**
 * A final class containing the priorities of all the actors.
 */
public final class Priority
{
    public static final int BLOCK = 0;

    public static final int PLAYER = 10;
    public static final int OVERLAY = 1000;

    public static final int HEART = 20;
    public static final int JUMPER = 50;
    public static final int FIREBALL = 100;

    public static final int LIMITS = 1000000;

    public static final int LEVEL = Integer.MAX_VALUE;

    private Priority() {}
}
