package platform.game.block.transparent;

import platform.game.Actor;
import platform.game.FixedActor;
import platform.game.level.PlayableLevel;
import platform.game.signal.ConstantSignal;
import platform.game.signal.Signal;
import platform.game.util.Damage;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

import java.util.Objects;

/**
 * A world exit that leads to another level when a player enters it.
 * It also adds the levels in the achieved levels list.
 */
public class Exit extends FixedActor
{
    private PlayableLevel level;
    private Signal signal;

    public Exit(Vector vector, PlayableLevel level, Signal signal)
    {
        super(new Box(Objects.requireNonNull(vector), vector.add(new Vector(1, 1))));

        this.level = Objects.requireNonNull(level);
        this.signal = Objects.requireNonNull(signal);
    }

    public Exit(Vector vector, PlayableLevel level)
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
        if(signal.isActive() && type == Damage.TOUCH)
        {
            getWorld().addDoneLevel(level);
            getWorld().setNextLevel(level.getNextLevelOnClear());
            getWorld().nextLevel();
            return true;
        }
        return false;
    }
}
