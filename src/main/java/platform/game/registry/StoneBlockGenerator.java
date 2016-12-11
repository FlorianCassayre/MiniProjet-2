package platform.game.registry;

import platform.game.block.solid.Block;
import platform.util.Box;
import platform.util.Vector;

/**
 * Utility class to generate stone blocks by specifying their size.
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

    /**
     * Returns the sprite of the normal version of this stone block.
     * @return the sprite name
     */
    public String getNormalSprite()
    {
        return spriteNormal;
    }

    /**
     * Returns the sprite of the broken version of this stone block.
     * @return the sprite name
     */
    public String getBrokenSprite()
    {
        return spriteBroken;
    }

    /**
     * Returns the size of this block.
     * @return a vector representing the size
     */
    public Vector getSize()
    {
        return new Vector(width, height);
    }

    /**
     * Creates a new instance of this block, at a given position.
     * @param position where should the block be
     * @param broken true if the block is broken, false else
     * @return a new instance of this block
     */
    public Block createBlock(Vector position, boolean broken)
    {
        return new Block(new Box(position, position.add(getSize())), broken ? getBrokenSprite() : getNormalSprite());
    }

    /**
     * Creates a new instance of this block, at a given position.
     * @param position where should the block be
     * @return a new instance of this block
     */
    public Block createBlock(Vector position)
    {
        return createBlock(position, false);
    }
}
