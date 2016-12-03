package platform.game.living;

import platform.game.Actor;
import platform.game.util.Damage;
import platform.game.util.Priority;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

import java.awt.event.KeyEvent;

public class Player extends LivingEntity
{
    private static final int HEALTH_MAX = 5;
    private static final double SIZE = 0.5;

    private boolean colliding;

    public Player(Vector position, Vector velocity)
    {
        super(position, new Vector(SIZE, SIZE), velocity, HEALTH_MAX);
    }

    public Player(Vector position)
    {
        this(position, Vector.ZERO);
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
                setPosition(getPosition().add(delta));
                if(delta.getX() != 0.0)
                    setVelocity(new Vector(0.0, getVelocity().getY()));
                if(delta.getY() != 0.0)
                    setVelocity(new Vector(getVelocity().getX(), 0.0));

                colliding = true;
            }
        }
    }

    @Override
    public void draw(Input input, Output output)
    {
        output.drawSprite(getSprite("blocker.happy"), getBox());
    }

    @Override
    public void update(Input input)
    {
        if(colliding)
        {
            double scale = Math.pow(0.001, input.getDeltaTime());
            setVelocity(getVelocity().mul(scale));
        }

        double maxSpeed = 4.0;
        if(input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown())
        {
            if(getVelocity().getX() < maxSpeed)
            {
                double increase = 60.0 * input.getDeltaTime();
                double speed = getVelocity().getX() + increase;
                if(speed > maxSpeed)
                    speed = maxSpeed;
                setVelocity(new Vector(speed, getVelocity().getY()));
            }
        }
        else if(input.getKeyboardButton(KeyEvent.VK_LEFT).isDown()) // FIXME redundant
        {
            if(-getVelocity().getX() < maxSpeed)
            {
                double increase = 60.0 * input.getDeltaTime();
                double speed = getVelocity().getX() - increase;
                if(-speed > maxSpeed)
                    speed = -maxSpeed;
                setVelocity(new Vector(speed, getVelocity().getY()));
            }
        }

        if(input.getKeyboardButton(KeyEvent.VK_UP).isPressed() && colliding)
        {
            setVelocity(new Vector(getVelocity().getX(), 7.0));
        }

        if(input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed())
        {
            Vector fireballSpeed = getVelocity().add(getVelocity().resized(2.0));
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

        super.update(input);
    }

    @Override
    public void postUpdate()
    {
        super.postUpdate();

        getWorld().setView(getPosition(), 8.0);
    }

    @Override
    public void onDeath()
    {
        getWorld().nextLevel();
    }

    @Override
    public int getPriority()
    {
        return Priority.PLAYER;
    }

    @Override
    public boolean hurt(Actor instigator, Damage type, double amount, Vector location)
    {
        switch(type)
        {
            case VOID:
                kill();
                return true;
            case FIRE:
                return true;
            case AIR:
                if(!(instigator instanceof Player))
                {
                    setVelocity(getPosition().sub(location).resized(amount));
                }
                return true;
            case HEAL:
                setHealth(Math.min(getHealth() + amount, getMaxHealth()));
                return true;
            case PHYSICAL:
                setHealth(Math.max(getHealth() - amount, 0));
                //velocity = getPosition().sub(location).normalized().mul(5);
                return true;
            default:
                return super.hurt(instigator, type, amount, location);
        }
    }
}
