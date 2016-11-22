package platform.game.actors;

import platform.game.Actor;
import platform.game.World;
import platform.util.*;

public class Fireball extends Actor
{
    private final double size;
    private Vector velocity;
    private Vector location;
    private Sprite sprite;

    public Fireball(Vector location, Vector speed)
    {
        super(666);

        if(location == null || speed == null)
            throw new NullPointerException();

        this.size = 0.4;

        this.location = location;
        this.velocity = speed;
    }

    @Override
    public void update(Input input)
    {
        super.update(input);
        double delta = input.getDeltaTime();
        Vector acceleration = getWorld().getGravity();
        velocity = velocity.add(acceleration.mul(delta));
        location = location.add(velocity.mul(delta));
    }

    @Override
    public void register(World world)
    {
        super.register(world);

        this.sprite = getWorld().getLoader().getSprite("fireball");
    }

    @Override
    public void draw(Input input, Output output)
    {
        output.drawSprite(sprite, getBox(), 20 * input.getTime());
    }

    @Override
    public Box getBox()
    {
        return new Box(new Vector(location.getX(), location.getY()), size, size);
    }

    @Override
    public void interact(Actor other)
    {
        super.interact(other);

        if(other.isSolid())
        {
            Vector delta = other.getBox().getCollision(location);
            if(delta != null)
            {
                location = location.add(delta);
                velocity = velocity.mirrored(delta);
            }
        }

    }
}
