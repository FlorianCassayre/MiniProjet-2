package platform.game;

import platform.game.Actor;
import platform.game.Damage;
import platform.util.Box;
import platform.util.Vector;

public class Limits extends Actor
{
    private Box box;

    public Limits(Box box)
    {
        this.box = box;
    }

    @Override
    public int getPriority()
    {
        return Priority.LIMITS;
    }

    @Override
    public void interact(Actor other)
    {
        if(!getBox().isColliding(other.getBox()))
        {
            other.hurt(this, Damage.VOID, Double.POSITIVE_INFINITY, Vector.ZERO);
        }
    }

    @Override
    public Box getBox()
    {
        return box;
    }
}
