package platform.game;

import platform.game.level.Level;
import platform.game.signal.ConstantSignal;
import platform.game.signal.Signal;
import platform.util.*;

public class Exit extends Block
{
    private Level level;
    private Signal signal;

    public Exit(Vector vector, Level level, Signal signal)
    {
        super(new Box(vector, vector.add(new Vector(1, 1))), null);

        this.level = level;
        this.signal = signal;
    }

    public Exit(Vector vector, Level level)
    {
        this(vector, level, new ConstantSignal(true));
    }

    @Override
    public void draw(Input input, Output output)
    {
        String sprite;
        if(!signal.isActive())
            sprite = "door.closed";
        else
            sprite = "door.open";

        output.drawSprite(getSprite(sprite), getBox());
    }

    @Override
    public boolean hurt(Actor instigator, Damage type, double amount, Vector location)
    {
        if(type == Damage.TOUCH)
        {
            getWorld().setNextLevel(level);
            getWorld().nextLevel();
            return true;
        }
        return false;
    }

        @Override
    public boolean isSolid()
    {
        return false;
    }
}
