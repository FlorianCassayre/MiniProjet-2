package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;
import platform.util.Output;
import platform.util.Sprite;

/**
 * Base class of all simulated actors, attached to a world.
 */
public abstract class Actor implements Comparable<Actor>
{
    private final int priority;
    private World world;

    @Deprecated
    public Actor()
    {
        throw new UnsupportedOperationException();
    }

    public Actor(int priority)
    {
        this.priority = priority;
    }

    public void update(Input input)
    {}

    public void preUpdate()
    {}

    public void postUpdate()
    {}

    public void draw(Input input, Output output)
    {}

    public void interact(Actor other)
    {}

    public void register(World world)
    {
        this.world = world;
    }

    public void unregister()
    {
        world = null;
    }

    protected World getWorld()
    {
        return world;
    }

    public boolean isSolid()
    {
        return false;
    }

    public Box getBox()
    {
        return null;
    }

    public Vector getPosition()
    {
        Box box = getBox();
        if(box == null)
            return null;
        return box.getCenter();
    }

    public int getPriority()
    {
        return priority;
    }

    public int compareTo(Actor that)
    {
        if(this.priority < that.priority)
            return 1;
        else if(this.priority > that.priority)
            return -1;
        return 0;
    }
}