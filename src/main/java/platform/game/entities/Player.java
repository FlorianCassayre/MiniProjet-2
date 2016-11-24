package platform.game.entities;

import platform.game.Actor;
import platform.game.World;
import platform.util.*;

import java.awt.event.KeyEvent;

public class Player extends Actor
{
    private Vector size;
    private Vector velocity;
    private Vector location;
    private Sprite sprite;
    private boolean colliding;

    public Player(Vector location, Vector speed)
    {
        if(location == null || speed == null)
            throw new NullPointerException();

        this.size = new Vector(0.5, 0.5);
        this.location = location;
        this.velocity = speed;
    }

    @Override
    public void register(World world)
    {
        super.register(world);

        this.sprite = getSprite("blocker.happy");
    }

    @Override
    public void preUpdate()
    {
        colliding = false;
    }

    @Override
    public void interact(Actor other)
    {
        if(other.isSolid())
        {
            Vector delta = other.getBox().getCollision(getBox());
            if(delta != null)
            {
                location = location.add(delta);
                if(delta.getX() != 0.0)
                    velocity = new Vector(0.0, velocity.getY());
                if(delta.getY() != 0.0)
                    velocity = new Vector(velocity.getX(), 0.0);

                colliding = true;
            }
        }
    }

    @Override
    public void update(Input input)
    {
        double delta = input.getDeltaTime();
        Vector acceleration = getWorld().getGravity();
        velocity = velocity.add(acceleration.mul(delta));

        if(colliding)
        {
            double scale = Math.pow(0.001, input.getDeltaTime());
            velocity = velocity.mul(scale);
        }

        double maxSpeed = 4.0;
        if(input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown())
        {
            if(velocity.getX() < maxSpeed)
            {
                double increase = 60.0 * input.getDeltaTime();
                double speed = velocity.getX() + increase;
                if(speed > maxSpeed)
                    speed = maxSpeed;
                velocity = new Vector(speed, velocity.getY());
            }
        }
        else if(input.getKeyboardButton(KeyEvent.VK_LEFT).isDown()) // FIXME redundant
        {
            if(-velocity.getX() < maxSpeed)
            {
                double increase = 60.0 * input.getDeltaTime();
                double speed = velocity.getX() - increase;
                if(-speed > maxSpeed)
                    speed = -maxSpeed;
                velocity = new Vector(speed, velocity.getY());
            }
        }

        if(input.getKeyboardButton(KeyEvent.VK_UP).isPressed() && colliding)
        {
            velocity = new Vector(velocity.getX(), 7.0);
        }

        if(colliding)
        {
            double scale = Math.pow(0.001, input.getDeltaTime());
            velocity = velocity.mul(scale);
        }

        location = location.add(velocity.mul(delta));


        if(input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed())
        {
            Vector fireballSpeed = velocity.add(velocity.resized(2.0));
            getWorld().register(new Fireball(getPosition(), fireballSpeed));
        }
    }

    @Override
    public void postUpdate()
    {
        getWorld().setView(getPosition(), 8.0);
    }

    @Override
    public Vector getPosition()
    {
        return new Vector(location.getX(), location.getY());
    }

    @Override
    public int getPriority()
    {
        return 42;
    }

    @Override
    public void draw(Input input, Output output)
    {
        output.drawSprite(sprite, getBox());
    }

    @Override
    public Box getBox()
    {
        return new Box(new Vector(location.getX(), location.getY()), size.getX(), size.getY());
    }
}
