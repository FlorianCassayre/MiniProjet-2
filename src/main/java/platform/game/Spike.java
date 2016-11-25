package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Spike extends Block
{
    public Spike(Vector position)
    {
        super(new Box(position, position.add(new Vector(1, 0.5))), "spike");
    }

    @Override
    public void draw(Input input, Output output)
    {
        output.drawSprite(getSprite("spikes"), getBox());
    }

    @Override
    public void interact(Actor other)
    {
        super.interact(other);

        if(other instanceof Player && getBox().isColliding(other.getBox()))
        {
            other.hurt(other, Damage.PHYSICAL, 0.5, other.getPosition().add(new Vector(0, -1)));
        }
    }

    @Override
    public int getPriority()
    {
        return 4000;
    }
}