package platform.game.block.solid;

import platform.game.item.Key;
import platform.game.signal.Not;
import platform.util.Box;
import platform.util.Vector;

/**
 * A block that can appear when the corresponding key is taken.
 * The color of this block is the color of the key.
 */
public class HiddenBlock extends VariableBlock
{
    public HiddenBlock(Vector position, Key key){
        super(new Box(position, position.add(new Vector(1, 1))), key.getColor().getDoorSprite(), key, true);
    }
}