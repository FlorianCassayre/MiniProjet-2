package platform.game.living;

import platform.game.Actor;
import platform.game.util.Damage;
import platform.game.util.Priority;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Fireball extends LivingEntity
{
    private static final int MAX_BOUNCES = 10;
    private static final double SIZE = 0.4;

    private final Actor owner;

    public Fireball(Actor owner, Vector location, Vector speed)
    {
        super(location, new Vector(SIZE, SIZE), speed, MAX_BOUNCES);

        this.owner = owner;
    }

    @Override
    public void draw(Input input, Output output)
    {
        output.drawSprite(getSprite("fireball"), getBox(), 20 * input.getTime());
    }

    @Override
    public int getPriority()
    {
        return Priority.FIREBALL;
    }

    @Override
    public void interact(Actor other)
    {
        super.interact(other);

        if(other.isSolid())
        {
            Vector delta = other.getBox().getCollision(getPosition());
            if(delta != null)
            {
                setPosition(getPosition().add(delta));
                setVelocity(getVelocity().mirrored(delta));
                setHealth(getHealth() - 1);
            }
        }

        if(other.getBox().isColliding(getBox()))
        {
            if(other != owner && other.hurt(this, Damage.FIRE, 1.0, getPosition()))
            {
                kill();
            }
        }
    }
}
