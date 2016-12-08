package platform.game.registry;

import platform.game.block.Block;
import platform.util.Box;
import platform.util.Vector;

/**
 * Utility class to generate blocks by their size.
 */
public enum StoneBlockGenerator
{
    BLOCK_1X1(1, 1, 1),
    BLOCK_2X1(2, 2, 1),
    BLOCK_3X1(3, 3, 1),
    BLOCK_2X2(4, 2, 2),
    BLOCK_3X2(5, 3, 2),
    BLOCK_2X3(6, 2, 3),
    BLOCK_1X3(7, 1, 3),
    BLOCK_1X2(8, 1, 2);

    private final String spriteNormal, spriteBroken;
    private final int width, height;

    StoneBlockGenerator(int id, int width, int height)
    {
        final String prefix = "stone.";

        this.spriteNormal = prefix + id;
        this.spriteBroken = prefix + "broken." + id;

        this.width = width;
        this.height = height;
    }

    public String getNormalSprite()
    {
        return spriteNormal;
    }

    public String getBrokenSprite()
    {
        return spriteBroken;
    }

    public Vector getSize()
    {
        return new Vector(width, height);
    }

    public Block createBlock(Vector position, boolean broken)
    {
        return new Block(new Box(position, position.add(getSize())), broken ? getBrokenSprite() : getNormalSprite());
    }

    public Block createBlock(Vector position)
    {
        return createBlock(position, false);
    }
}
