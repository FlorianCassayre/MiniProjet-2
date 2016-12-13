package platform.game.block.solid;

import platform.game.signal.ConstantSignal;
import platform.game.signal.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

import java.util.Objects;

/**
 * A block that can be hidden by a signal.
 * When hidden, the block becomes invisible and entities can walk through it.
 */
public class HideableBlock extends Block
{
    private Signal isVisible;

    public HideableBlock(Box box, String sprite, Signal isVisible)
    {
        super(Objects.requireNonNull(box, "The box cannot be null."), Objects.requireNonNull(sprite, "The sprite cannot be null."));

        this.isVisible = Objects.requireNonNull(isVisible, "The signal cannot be null.");
    }

    public HideableBlock(Box box, String sprite)
    {
        this(box, sprite, new ConstantSignal(true));
    }

    @Override
    public void draw(Input input, Output output)
    {
        if(!isVisible.isActive())
            super.draw(input, output);
    }

    @Override
    public boolean isSolid()
    {
        return !isVisible.isActive();
    }
}
