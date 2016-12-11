package platform.game.block.transparent;

import platform.game.Actor;
import platform.game.FixedActor;
import platform.game.entity.Smoke;
import platform.game.util.Damage;
import platform.game.signal.Signal;
import platform.util.*;

/**
 * A flip-flop torch signal.
 * It can be activated using a fireball and disabled by blowing on it.
 */
public class Torch extends FixedActor implements Signal
{
    private boolean lit;

    public Torch(Vector position, boolean isActive)
    {
        super(new Box(position.add(new Vector(0.25, 0.25)), position.add(new Vector(0.75, 0.75))));

        this.lit = isActive;
    }

    public Torch(Vector position)
    {
        this(position, false);
    }

    @Override
    public void draw(Input input, Output output)
    {
        String sprite;
        if(!lit)
            sprite = "torch";
        else if(((int) (input.getTime() * 5)) % 2 == 0)
            sprite = "torch.lit.1";
        else
            sprite = "torch.lit.2";

        output.drawSprite(getSprite(sprite), getBox());
    }

    @Override
    public boolean hurt(Actor instigator, Damage type, double amount, Vector location)
    {
        switch(type)
        {
            case FIRE:
                if(!lit)
                {
                    lit = true;
                    return true;
                }
                else
                    return false;
            case AIR:
                if(lit)
                {
                    for(int i = 0; i < 3; i++)
                        getWorld().register(new Smoke(getPosition().add(new Vector((0.5 - Math.random()) * 0.2, (0.5 - Math.random()) * 0.2))));

                    lit = false;
                    return true;
                }
                return false;
            default:
                return super.hurt(instigator, type, amount, location);
        }
    }

    @Override
    public boolean isActive()
    {
        return lit;
    }
}
