package platform.game.registry;

import platform.game.block.solid.Block;
import platform.util.Box;
import platform.util.Vector;

import java.util.Objects;

/**
 * Utility class to generate 1x1 blocks.
 */
public enum MiscBlockGenerator
{
    GRASS_LEFT("grass.left"),
    GRASS_MIDDLE("grass.middle"),
    GRASS_RIGHT("grass.right"),
    GRASS_CENTER("grass.center"),

    BOX_INNER_CROSS_DOUBLE("box.double"),
    BOX_INNER_CROSS_SINGLE("box.single"),
    BOX_INNER_EMPTY("box.empty"),
    BOX_INNER_WARNING_DARK("box.warning.dark"),
    BOX_INNER_WARNING_LIGHT("box.warning.dark"),
    BOX_INNER_WARNING_GOLD("box.warning.color"),
    BOX_INNER_EXCLAMATION_LIGHT("box.item.alt.disabled"),
    BOX_INNER_EXCLAMATION_GOLD("box.item.alt"),
    BOX_OUTER_EXCLAMATION_LIGHT("box.item.disabled"),
    BOX_OUTER_EXCLAMATION_GOLD("box.item"),
    ;

    private final String sprite;

    MiscBlockGenerator(String sprite)
    {
        assert sprite != null;

        this.sprite = sprite;
    }

    /**
     * Creates a new instance of this block, at a given position.
     * @param position where this block should be
     * @return a new instance of this block
     */
    public Block createBlock(Vector position)
    {
        return new Block(new Box(Objects.requireNonNull(position), position.add(new Vector(1, 1))), sprite);
    }
}
