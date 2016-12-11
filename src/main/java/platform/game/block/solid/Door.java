package platform.game.block.solid;

import platform.game.item.Key;
import platform.game.signal.Not;
import platform.game.signal.Signal;
import platform.game.util.ColoredItem;
import platform.util.Box;
import platform.util.Vector;

public class Door extends HideableBlock
{
    public Door(Vector position, ColoredItem color, Signal signal)
    {
        super(new Box(position, position.add(new Vector(1, 1))), color.getDoorSprite(), new Not(signal)); // Reverting the signal since the door must be invisible when the key is taken
    }

    public Door(Vector position, Key key)
    {
        this(position, key.getColor(), key);
    }
}
