package platform.game.entity.living;

import platform.game.Actor;
import platform.game.entity.projectile.Fireball;
import platform.game.util.Damage;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

import java.awt.event.KeyEvent;

/**
 * A player that can be controlled using the keyboard.
 */
public class Player extends LivingEntity
{
    private static final int HEALTH_MAX = 3;
    private static final double SIZE = 0.5;
    private static final double MAX_SPEED = 4.0;

    private double sadCooldown;

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
                if(delta.getX() != 0.0) // Player hit a wall (sideway)
                {
                    setVelocity(new Vector(0.0, getVelocity().getY()));
                }
                if(delta.getY() != 0.0) // Player hit the ground/ceiling
                {
                    setVelocity(new Vector(getVelocity().getX(), getVelocity().getY() * 0.5)); // Smooth slowdown
                    colliding = true;
                }
            }
        }
    }

    @Override
    public void draw(Input input, Output output)
    {
        output.drawSprite(getSprite("blocker." + (sadCooldown < 0 ? (getHealth() > 1 ? "happy" : "sad") : "dead")), getBox());
    }

    @Override
    public void update(Input input)
    {
        sadCooldown -= input.getDeltaTime();

        if(colliding) // Ground colliding, we slow him down
        {
            double scale = Math.pow(0.001, input.getDeltaTime());
            setVelocity(getVelocity().mul(scale));
        }


        final boolean keyLeft = input.getKeyboardButton(KeyEvent.VK_LEFT).isDown(), keyRight = input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown();
        if(keyLeft || keyRight) // The user wants to move
        {
            final double coefficient = keyLeft && keyRight ? 0 : (keyLeft ? -1 : 1); // 0 is case the user presses two keys at the same time
            if(coefficient * getVelocity().getX() < MAX_SPEED)
            {
                final double increase = 30.0 * input.getDeltaTime();

                double speed = getVelocity().getX() + coefficient * increase;
                if(coefficient * speed > MAX_SPEED)
                    speed = coefficient * MAX_SPEED;
                setVelocity(new Vector(speed, getVelocity().getY()));
            }
        }

        if(input.getKeyboardButton(KeyEvent.VK_UP).isPressed() && colliding) // The user wants to jump
        {
            setVelocity(new Vector(getVelocity().getX(), 7.0));
        }

        if(input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed()) // The user wants to throw a fireball
        {
            Vector fireballSpeed = getVelocity().add(getVelocity().resized(2.0));
            getWorld().register(new Fireball(this, getPosition(), fireballSpeed));
        }

        if(input.getKeyboardButton(KeyEvent.VK_B).isPressed()) // The user wants to blow
        {
            getWorld().hurt(getBox(), this, Damage.AIR, 1.0, getPosition());
        }

        if(input.getKeyboardButton(KeyEvent.VK_E).isPressed()) // The user wants to activate an block
        {
            getWorld().hurt(getBox(), this, Damage.ACTIVATION, 1.0, getPosition());
        }

        getWorld().hurt(getBox(), this, Damage.TOUCH, 1.0, getPosition());

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
    public void setHealth(double health)
    {
        if(health < getHealth())
            sadCooldown = 1;

        super.setHealth(health);
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
                setVelocity(getVelocity().mul(0.2).add(getPosition().sub(location).normalized().mul(5)));
                return true;
            default:
                return super.hurt(instigator, type, amount, location);
        }
    }
}
