package platform.game;

import platform.game.Actor;
import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Output;

/**
 * Simple solid actor that does nothing.
 */
public class Block extends Actor
{
    private final Box box;
    private final Sprite sprite;


    public Block(Box box, Sprite sprite)
    {
        if(box == null)
            throw new IllegalArgumentException();

        this.box = box;
        this.sprite = sprite;
    }

    @Override
    public void draw(Input input, Output output)
    {
        if(sprite != null)
        {
            output.drawSprite(sprite, box);
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
        return 0;
    }
}
