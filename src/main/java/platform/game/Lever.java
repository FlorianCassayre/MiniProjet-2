package platform.game;

import platform.game.signal.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Lever extends Block implements Signal
{
    private boolean state = false;

    public Lever(Vector vector)
    {
        super(new Box(vector, vector.add(new Vector(1, 1))), null); // FIXME
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

    @Override
    public boolean isSolid()
    {
        return false;
    }
}
