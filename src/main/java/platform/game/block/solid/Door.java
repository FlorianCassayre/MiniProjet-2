package platform.game.block.solid;

import platform.game.item.Key;
import platform.game.signal.Not;
import platform.game.signal.Signal;
import platform.game.util.ColoredItem;
import platform.util.Box;
import platform.util.Vector;

/**
 * A block that can be removed using a key.
 * The color of this block depends on the color of the key.
 */
public class Door extends HideableBlock
{
    public Door(Vector position, Key key)
    {
        super(new Box(position, position.add(new Vector(1, 1))), key.getColor().getDoorSprite(), new Not(key)); // Reverting the signal since the door must be invisible when the key is taken
    }
}
