package platform.game.util;

/**
 * A final class containing the priorities of all the actors.
 */
public final class Priority
{
    public static final int BACKGROUND = Integer.MIN_VALUE;

    public static final int BLOCK = 0;

    public static final int ENTITY_LIVING = 10;

    public static final int ENTITY_PROJECTILE = 15;

    public static final int GROUND_ITEM = 20;

    public static final int BLOCK_INTERACTION = 50;

    public static final int ENTITY_OVERLAY = 1000;

    public static final int ENTITY_PARTICLE = 10000;

    public static final int LIMITS = 1000000;

    public static final int LEVEL = Integer.MAX_VALUE;

    private Priority() {} // Prevents this class from being instanciated
}
