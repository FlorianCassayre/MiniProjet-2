package platform.game.block.solid;

import platform.game.item.Key;
import platform.game.signal.Not;
import platform.util.Box;
import platform.util.Vector;

import java.util.Objects;

/**
 * A block that can be removed using a key.
 * The color of this block is the color of the key.
 */
public class Door extends HideableBlock
{
    public Door(Vector position, Key key)
    {
        super(new Box(Objects.requireNonNull(position, "The position cannot be null."), position.add(new Vector(1, 1))), Objects.requireNonNull(key, "The key cannot be null.").getColor().getDoorSprite(), key);
    }
}
