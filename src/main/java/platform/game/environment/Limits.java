package platform.game.environment;

import platform.game.Actor;
import platform.game.util.Damage;
import platform.game.util.Priority;
import platform.util.Box;
import platform.util.Vector;

import java.util.Objects;

/**
 * A world border.
 */
public class Limits extends Actor
{
    private Box box;

    public Limits(Box box)
    {
        this.box = Objects.requireNonNull(box);
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
