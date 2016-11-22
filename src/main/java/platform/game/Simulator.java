package platform.game;

import java.util.ArrayList;
import java.util.List;

import platform.game.actors.Fireball;
import platform.util.*;

/**
 * Basic implementation of world, managing a complete collection of actors.
 */
public class Simulator implements World
{
    private static final Vector GRAVITY = new Vector(0, -9.81);

    private Loader loader;

    private Vector currentCenter;
    private double currentRadius;

    private Vector expectedCenter = Vector.ZERO;
    private double expectedRadius = 10.0;

    private List<Actor> registered = new ArrayList<>();
    private List<Actor> unregistered = new ArrayList<>();

    private final SortedCollection<Actor> actors = new SortedCollection<>();

    /**
     * Create a new simulator.
     *
     * @param loader associated loader, not null
     * @param args   level arguments, not null
     */
    public Simulator(Loader loader, String[] args)
    {
        if(loader == null)
        {
            throw new NullPointerException();
        }
        this.loader = loader;
        currentCenter = Vector.ZERO;
        currentRadius = 10.0;

        // =-=-=-= TeStS =-=-=-=

        Sprite sprite = loader.getSprite("box.empty");
        Box zone1 = new Box(new Vector(-4, -1), new Vector(4, 0));
        register(new Block(zone1, sprite));

        Box zone2 = new Box(new Vector(-2, 0), new Vector(-1, 1));
        register(new Block(zone2, sprite));

        register(new Fireball(new Vector(0, 1), Vector.ZERO));
    }

    /**
     * Simulate a single step of the simulation.
     *
     * @param input  input object to use, not null
     * @param output output object to use, not null
     */
    public void update(Input input, Output output)
    {
        double factor = 0.001;
        currentCenter = currentCenter.mul(1.0 - factor).add(expectedCenter.mul(factor));
        currentRadius = currentRadius * (1.0 - factor) + expectedRadius * factor;

        View view = new View(input, output);
        view.setTarget(currentCenter, currentRadius);

        if(view.getMouseButton(1).isPressed())
            setView(view.getMouseLocation(), 10.0);


        // Add registered actors
        for(int i = 0; i < registered.size(); ++i)
        {
            Actor actor = registered.get(i);
            if(!actors.contains(actor))
            {
                actors.add(actor);
            }
        }
        registered.clear();
        // Remove unregistered actors
        for(int i = 0; i < unregistered.size(); ++i)
        {
            Actor actor = unregistered.get(i);
            actors.remove(actor);
        }
        unregistered.clear();

        for(Actor a : actors)
            a.preUpdate();

        for(Actor a : actors)
            a.update(view);

        for(Actor actor : actors)
            for(Actor other : actors)
                if(actor.getPriority() > other.getPriority())
                    actor.interact(other);


        for(Actor a : actors.descending())
            a.draw(view, view);


        for(Actor a : actors)
            a.postUpdate();


        /*Sprite sprite = loader.getSprite("heart.full");
        Box zone = new Box(new Vector(0, 0), 32, 32);
        output.drawSprite(sprite, zone);*/




    }

    @Override
    public void register(Actor actor)
    {
        actor.register(this);
        registered.add(actor);
    }

    @Override
    public void unregister(Actor actor)
    {
        actor.unregister();
        unregistered.add(actor);
    }

    @Override
    public Vector getGravity()
    {
        return GRAVITY;
    }

    @Override
    public Loader getLoader()
    {
        return loader;
    }

    @Override
    public void setView(Vector center, double radius)
    {
        if(center == null)
            throw new NullPointerException();
        if(radius <= 0.0)
            throw new IllegalArgumentException("radius must be positive");
        expectedCenter = center;
        expectedRadius = radius;
    }
}
