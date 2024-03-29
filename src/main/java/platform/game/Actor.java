package platform.game;

import platform.game.util.Damage;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;
import platform.util.Output;
import platform.util.Sprite;

import java.util.Objects;

/**
 * Base class of all simulated actors, attached to a world.
 */
public abstract class Actor implements Comparable<Actor>
{
    private World world;

    public Actor()
    {}

    /**
     * Registers the actor to the specified world.
     * @param world the world to register the actor in
     */
    public void register(World world)
    {
        this.world = Objects.requireNonNull(world, "The world cannot be null.");
    }

    /**
     * Unregisters the actor from its current world.
     */
    public void unregister()
    {
        world = null;
    }

    /**
     * All actions that should be processed before update(...).
     */
    public void preUpdate()
    {}

    /**
     * Is called for every actor that has a lower priority.
     */
    public void interact(Actor other)
    {}

    /**
     * Called for every frame in the game.
     */
    public void update(Input input)
    {}

    /**
     * All actions that should be processed after update(...).
     */
    public void postUpdate()
    {}

    /**
     * Deals a damage to this actor, from the specified instigator.
     * @param instigator the actor that dealt this damage
     * @param type the damage type
     * @param amount the power of the damage
     * @param location where the damage has been dealt
     * @return true if the damage was absorbed
     */
    public boolean hurt(Actor instigator, Damage type, double amount, Vector location)
    {
        switch(type)
        {
            case VOID:
                getWorld().unregister(this);
                return true;
            default:
                return false;
        }
    }

    /**
     * Called to draw this actor.
     * @param input the input
     * @param output the output
     */
    public void draw(Input input, Output output)
    {}

    /**
     * Get the instance of the world this actor is registered in.
     * @return the instance of the world
     */
    protected World getWorld()
    {
        return world;
    }

    /**
     * Gets informations about the block solidity.
     * @return true if this block is indeed solid, false if transparent
     */
    public boolean isSolid()
    {
        return false;
    }

    /**
     * Returns the box generated by this actor.
     * @return the box of this actor
     */
    public Box getBox()
    {
        return null;
    }

    /**
     * Returns the actor's current location in the world.
     * @return the position
     */
    public Vector getPosition()
    {
        Box box = getBox();
        if(box == null)
            return null;
        return box.getCenter();
    }

    /**
     * Gets this actor's priority.
     * An actor with a lower priority will be drawn behind the other one.
     * Furthermore, the priority also defines the order of interaction.
     * @return the priority
     */
    public int getPriority()
    {
        return 0;
    }

    /**
     * Returns an instance of the specified sprite.
     * @param string the relative name of the sprite, without the extension
     * @return the sprite resource
     */
    protected Sprite getSprite(String string)
    {
        return getWorld().getLoader().getSprite(string);
    }

    /**
     * Compares two actors by their priority
     * @param that the actor to compare to this one
     * @return an integer representing the comparison result
     */
    @Override
    public int compareTo(Actor that)
    {
        if(getPriority() < that.getPriority())
            return 1;
        else if(getPriority() > that.getPriority())
            return -1;
        return 0;
    }
}