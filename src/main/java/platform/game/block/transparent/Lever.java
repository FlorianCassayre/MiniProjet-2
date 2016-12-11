package platform.game.block.transparent;

import platform.game.Actor;
import platform.game.FixedActor;
import platform.game.util.Damage;
import platform.game.signal.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

/**
 * A flip-flop lever signal.
 */
public class Lever extends FixedActor implements Signal
{
    private boolean state = false;

    public Lever(Vector vector)
    {
        super(new Box(vector, vector.add(new Vector(1, 1))));
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
