package platform.game.block.transparent;

import platform.game.Actor;
import platform.game.FixedActor;
import platform.game.util.Damage;
import platform.game.signal.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

import java.util.Objects;

/**
 * A flip-flop lever signal.
 */
public class Lever extends FixedActor implements Signal
{
    private boolean state = false;

    public Lever(Vector position, boolean state){
        super(new Box(Objects.requireNonNull(position), position.add(new Vector(1, 1))));
        this.state = state;
    }

    public Lever(Vector position){
        this(position, false);
    }

    @Override
    public void draw(Input input, Output output)
    {
        String sprite;
        if(state)
            sprite = "lever.right";
        else
            sprite = "lever.left";
        output.drawSprite(getSprite(sprite), getBox());
    }

    @Override
    public boolean hurt(Actor instigator, Damage type, double amount, Vector location)
    {
        if(type == Damage.ACTIVATION)
        {
            state = !state;
            return true;
        }
        return false;
    }

    @Override
    public boolean isActive()
    {
        return state;
    }
}
