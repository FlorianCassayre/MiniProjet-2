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
    private World world;


    public Actor()
    {

    }

    public void register(World world)
    {
        this.world = world;
    }

    public void unregister()
    {
        world = null;
    }

    public void preUpdate()
    {}

    public void interact(Actor other)
    {}

    public void update(Input input)
    {}

    public void postUpdate()
    {}

    public void draw(Input input, Output output)
    {}

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

    public abstract int getPriority();

    protected Sprite getSprite(String string)
    {
        return getWorld().getLoader().getSprite(string);
    }

    public int compareTo(Actor that)
    {
        if(getPriority() < that.getPriority())
            return 1;
        else if(getPriority() > that.getPriority())
            return -1;
        return 0;
    }
}