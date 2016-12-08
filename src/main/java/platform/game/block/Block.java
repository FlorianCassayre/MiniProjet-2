package platform.game.block;

import platform.game.Actor;
import platform.game.FixedActor;
import platform.game.util.Priority;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

/**
 * Simple solid actor that does nothing.
 */
public class Block extends FixedActor
{
    private final String sprite;

    public Block(Box box, String sprite)
    {
        super(box);

        if(sprite == null)
            throw new IllegalArgumentException("The sprite cannot be null.");

        this.sprite = sprite;
    }

    @Override
    public void draw(Input input, Output output)
    {
        if(sprite != null)
        {
            output.drawSprite(getSprite(sprite), getBox());
        }
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }

    @Override
    public int getPriority()
    {
        return Priority.BLOCK;
    }
}
