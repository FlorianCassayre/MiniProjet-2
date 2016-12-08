package platform.game.entity;

import platform.game.Actor;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

/**
 * Base class of every entity.
 * An entity is an actor defined by a position and a momentum (velocity).
 * It also has an amount of health. If its health hits 0 (or less), the entity dies and is removed from the world.
 */
public abstract class Entity extends Actor
{
    private Vector position;
    private final Vector size;
    private Vector velocity;
    private double health;
    private final double maxHealth;

    public Entity(Vector position, Vector size, Vector velocity, int maxHealth)
    {
        this.position = position;
        this.size = size;
        this.velocity = velocity;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public Entity(Vector position, Vector size, int maxHealth)
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

    /**
     * Called when the entity dies.
     */
    protected void onDeath()
    {
        getWorld().unregister(this);
    }

    /**
     * Sets the position of this entity.
     * @param position a position vector
     */
    public void setPosition(Vector position)
    {
        this.position = position;
    }

    /**
     * Returns the entity's velocity.
     * @return a velocity vector.
     */
    public Vector getVelocity()
    {
        return velocity;
    }

    /**
     * Sets the velocity of this entity.
     * @param velocity a velocity vetor
     */
    public void setVelocity(Vector velocity)
    {
        this.velocity = velocity;
    }

    /**
     * Returns the current amount of health of this entity.
     * @return the health amount
     */
    public double getHealth()
    {
        return health;
    }

    /**
     * Sets the current health of this entity.
     * @param health the health amount
     */
    public void setHealth(double health)
    {
        this.health = health;
    }

    /**
     * Returns the maximum health of this entity.
     * @return the maximum health amount
     */
    public double getMaxHealth()
    {
        return maxHealth;
    }

    /**
     * Kills the entity.
     */
    public void kill()
    {
        setHealth(0);
    }
}
