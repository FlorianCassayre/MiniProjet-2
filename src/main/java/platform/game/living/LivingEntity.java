package platform.game.living;

import platform.game.Actor;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

public abstract class LivingEntity extends Actor
{
    private Vector position;
    private final Vector size;
    private Vector velocity;
    private double health;
    private final double maxHealth;

    public LivingEntity(Vector position, Vector size, Vector velocity, int maxHealth)
    {
        this.position = position;
        this.size = size;
        this.velocity = velocity;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public LivingEntity(Vector position, Vector size, int maxHealth)
    {
        this(position, size, Vector.ZERO, maxHealth);
    }

    @Override
    public void update(Input input)
    {
        super.update(input);
        double delta = input.getDeltaTime();
        Vector acceleration = getWorld().getGravity();
        velocity = velocity.add(acceleration.mul(delta));
        position = position.add(velocity.mul(delta));
    }

    @Override
    public Box getBox()
    {
        return new Box(position, size.getX(), size.getY());
    }

    @Override
    public void postUpdate()
    {
        if(health <= 0)
            onDeath();
    }

    public void onDeath()
    {
        getWorld().unregister(this);
    }

    public void setPosition(Vector position)
    {
        this.position = position;
    }

    public Vector getSize()
    {
        return size;
    }

    public Vector getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vector velocity)
    {
        this.velocity = velocity;
    }

    public double getHealth()
    {
        return health;
    }

    public void setHealth(double health)
    {
        this.health = health;
    }

    public double getMaxHealth()
    {
        return maxHealth;
    }

    public void kill()
    {
        setHealth(0);
    }
}
