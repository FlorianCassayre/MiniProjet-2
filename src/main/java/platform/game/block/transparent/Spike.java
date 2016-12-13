package platform.game.block.transparent;

import platform.game.Actor;
import platform.game.entity.living.LivingEntity;
import platform.game.util.Damage;
import platform.game.util.Direction;
import platform.game.util.Priority;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

import java.util.Objects;

/**
 * Spikes that hurt any living entity that gets too close.
 */
public class Spike extends Actor
{
    private Direction direction;
    private Vector position;
    private double cooldown;

    public Spike(Vector position, Direction direction)
    {
        this.position = Objects.requireNonNull(position);
        this.direction = Objects.requireNonNull(direction);
    }

    public Spike(Vector position)
    {
        this(position, Direction.UP);
    }

    @Override
    public void draw(Input input, Output output)
    {
        output.drawSprite(getSprite("spikes"), getBox(), (direction.getAngle() - Direction.UP.getAngle()) * Math.PI / 2.0);
    }

    @Override
    public void update(Input input)
    {
        cooldown -= input.getDeltaTime();
    }

    @Override
    public void interact(Actor other)
    {
        super.interact(other);

        if(other instanceof LivingEntity && getBox().isColliding(other.getBox()))
        {
            double damages;
            if(cooldown <= 0)
            {
                cooldown = 0.5;
                damages = 0.5;
            }
            else
            {
                damages = 0.0;
            }

            other.hurt(other, Damage.PHYSICAL, damages, getPosition()); // other.getPosition().add(new Vector(0, -1)
        }
    }

    @Override
    public Box getBox()
    {
        Vector position = this.position;
        switch(direction)
        {
            case UP:
                return new Box(position, position.add(new Vector(1, 0.5)));
            case DOWN:
                position = position.add(new Vector(0, 0.5));
                return new Box(position, position.add(new Vector(1, 0.5)));
            case LEFT:
                position = position.add(new Vector(0.5, 0));
                return new Box(position, position.add(new Vector(0.5, 1)));
            case RIGHT:
                return new Box(position, position.add(new Vector(0.5, 1)));
            default:
                throw new IllegalStateException("Someone is messing with the dimensions!");
        }
    }

    @Override
    public int getPriority()
    {
        return Priority.BLOCK_INTERACTION;
    }
}