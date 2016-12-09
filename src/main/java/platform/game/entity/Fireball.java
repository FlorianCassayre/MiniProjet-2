package platform.game.entity;

import platform.game.Actor;
import platform.game.util.Damage;
import platform.game.util.Priority;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Fireball extends Entity
{
    private static final int MAX_BOUNCES = 10;
    private static final double SIZE = 0.4;

    private double rotation = 0;

    private final Actor owner;

    public Fireball(Actor owner, Vector location, Vector speed)
    {
        super(location, new Vector(SIZE, SIZE), speed, MAX_BOUNCES);

        this.owner = owner;
    }

    @Override
    public void draw(Input input, Output output)
    {
        output.drawSprite(getSprite("fireball"), getBox(), 30 * rotation);
    }

    @Override
    public int getPriority()
    {
        return Priority.FIREBALL;
    }

    @Override
    public void update(Input input)
    {
        super.update(input);

        rotation += input.getDeltaTime();
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
                Vector velocity = getVelocity().mirrored(delta);
                setVelocity(new Vector(velocity.getX(), velocity.getY() * 0.9));
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

    @Override
    protected void onDeath()
    {
        super.onDeath();

        getWorld().register(new Smoke(getPosition()));
    }
}
