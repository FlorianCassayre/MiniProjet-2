package platform.game.block;

import platform.game.Actor;
import platform.game.util.Priority;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

/**
 * Simple solid actor that does nothing.
 */
public class Block extends Actor
{
    private final Box box;
    private final String sprite;

    public Block(Box box, String sprite)
    {
        //if(box == null)
           // throw new IllegalArgumentException();

        this.box = box;
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
    public Box getBox()
    {
        return box;
    }

    @Override
    public int getPriority()
    {
        return Priority.BLOCK;
    }
}
