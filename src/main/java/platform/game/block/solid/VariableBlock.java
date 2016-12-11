package platform.game.block.solid;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import platform.game.signal.ConstantSignal;
import platform.game.signal.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

/**
 * A block that can be initially hidden or not, and then triggered by a signal, to respectively appear or disappear.
 * The block is initially hidden. When hidden, the block is invisible and an entity can cross by.
 */
public class VariableBlock extends Block
{
    private Signal trigger;
    private boolean hidden;
    private boolean triggered=false;

    public VariableBlock(Box box, String sprite, Signal trigger, boolean hidden)
    {
        super(box, sprite);
        this.trigger = trigger;
        this.hidden = hidden;
    }

    public VariableBlock(Box box, String sprite, Signal trigger)
    {
        super(box, sprite);
        this.trigger = trigger;
        this.hidden = true;
    }

    public VariableBlock(Box box, String sprite)
    {
        this(box, sprite, new ConstantSignal(true));
    }

    @Override
    public void update(Input input){
if (trigger.isActive() && !triggered) {
    hidden = !hidden;
    triggered = true;
}
}
    @Override
    public void draw(Input input, Output output)
    {
        if(!hidden)
            super.draw(input, output);
    }

    @Override
    public boolean isSolid()
    {
        return !hidden;
    }
}
