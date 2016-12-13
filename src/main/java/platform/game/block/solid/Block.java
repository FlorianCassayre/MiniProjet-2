package platform.game.block.solid;

import platform.game.FixedActor;
import platform.game.util.Priority;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

import java.util.Objects;

/**
 * Simple solid actor that an entity cannot cross by.
 */
public class Block extends FixedActor
{
    private final String sprite;

    public Block(Box box, String sprite)
    {
        super(box);

        this.sprite = Objects.requireNonNull(sprite, "The sprite cannot be null.");
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
