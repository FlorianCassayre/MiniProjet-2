package platform.game;

import java.util.ArrayList;
import java.util.List;

import platform.game.entities.Block;
import platform.game.entities.Fireball;
import platform.game.entities.Player;
import platform.game.level.Level;
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

    private boolean transition = false;
    private Level next = null;

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

        register(Level.createDefaultLevel());
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

        //if(view.getMouseButton(1).isPressed())


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

        for(Actor a : actors.descending())
            a.draw(view, view);

        for(Actor a : actors)
            a.preUpdate();

        for(Actor actor : actors)
            for(Actor other : actors)
                if(actor.getPriority() > other.getPriority())
                    actor.interact(other);



        for(Actor a : actors)
            a.update(view);




        for(Actor a : actors)
            a.postUpdate();


        // si un acteur a mis transition à true pour demander le passage
        // à un autre niveau :
        if(transition)
        {
            if(next == null)
            {
                next = Level.createDefaultLevel();
            }
            // si un acteur a appelé setNextLevel , next ne sera pas null :
            Level level = next;
            transition = false;
            next = null;
            actors.clear();
            registered.clear();
            // tous les anciens acteurs sont désenregistrés ,
            // y compris le Level précédent :
            unregistered.clear();
            register(level);
        }
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
    public void nextLevel()
    {
        transition = true;
    }

    @Override
    public void setNextLevel(Level level)
    {
        next = level;
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
