package platform.game;

import platform.util.*;

import java.awt.event.KeyEvent;

public class Player extends Actor
{
    private static final double HEALTH_MAX = 5.0 + 2;

    private Vector size;
    private Vector velocity;
    private Vector location;
    private Sprite sprite;
    private boolean colliding;

    private double health = HEALTH_MAX - 1;

    public Player(Vector location, Vector speed)
    {
        if(location == null || speed == null)
            throw new NullPointerException();

        this.size = new Vector(0.5, 0.5);
        this.location = location;
        this.velocity = speed;
    }

    public Player(Vector location)
    {
        this(location, Vector.ZERO);
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

        if(input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed())
        {
            Vector fireballSpeed = velocity.add(velocity.resized(2.0));
            getWorld().register(new Fireball(this, getPosition(), fireballSpeed));
        }

        if(input.getKeyboardButton(KeyEvent.VK_B).isPressed())
        {
            getWorld().hurt(getBox(), this, Damage.AIR, 1.0, getPosition());
        }

        getWorld().hurt(getBox(), this, Damage.TOUCH, 1.0, getPosition());

        if(input.getKeyboardButton(KeyEvent.VK_E).isPressed())
        {
            getWorld().hurt(getBox(), this, Damage.ACTIVATION, 1.0, getPosition());
        }

        location = location.add(velocity.mul(delta));
    }

    @Override
    public void postUpdate()
    {
        getWorld().setView(getPosition(), 8.0);

        if(health <= 0)
            onDeath();
    }

    private void onDeath()
    {
        getWorld().nextLevel();
        getWorld().unregister(this);
    }

    @Override
    public Vector getPosition()
    {
        return new Vector(location.getX(), location.getY());
    }

    @Override
    public int getPriority()
    {
        return Priority.PLAYER;
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

    @Override
    public boolean hurt(Actor instigator, Damage type, double amount, Vector location)
    {
        switch(type)
        {
            case VOID:
                health = 0;
                return true;
            case FIRE:
                return true;
            case AIR:
                if(!(instigator instanceof Player))
                {
                    velocity = getPosition().sub(location).resized(amount);
                    return true;
                }
            case HEAL:
                health = Math.min(getHealth() + amount, getHealthMax());
                return true;
            case PHYSICAL:
                health = Math.max(getHealth() - amount, 0);
                //velocity = getPosition().sub(location).normalized().mul(5);
                return true;
            default:
                return super.hurt(instigator, type, amount, location);
        }
    }

    public double getHealth()
    {
        return health;
    }

    public double getHealthMax()
    {
        return HEALTH_MAX;
    }
}
