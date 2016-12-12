package platform.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import platform.game.level.Level;
import platform.game.level.PlayableLevel;
import platform.game.util.Damage;
import platform.util.*;

/**
 * Basic implementation of world, managing a complete collection of actors.
 */
public class Simulator implements World
{
    private static final Vector GRAVITY = new Vector(0, -9.81);

    private Loader loader;

    private final Vector defaultCenter = Vector.ZERO;
    private final double defaultRadius = 5.0;

    private Vector currentCenter;
    private double currentRadius;

    private Vector expectedCenter = defaultCenter;
    private double expectedRadius = defaultRadius;

    private boolean transition = false;
    private Level next = null;

    private List<Actor> registered = new ArrayList<>();
    private List<Actor> unregistered = new ArrayList<>();

    private final SortedCollection<Actor> actors = new SortedCollection<>();

    private final Set<Class<? extends PlayableLevel>> levelsDone = new HashSet<>();

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
        currentCenter = defaultCenter;
        currentRadius = defaultRadius;

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
            unregistered.clear();

            actors.add(level);

            level.register(this);

            expectedRadius = defaultRadius;
            expectedCenter = defaultCenter;

            currentCenter = defaultCenter;
            currentRadius = defaultRadius;
        }


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
            actor.unregister();
            actors.remove(actor);
        }
        unregistered.clear();



        for(Actor a : actors.descending())
            a.draw(view, view);
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

    @Override
    public int hurt(Box area, Actor instigator, Damage type, double amount, Vector location)
    {
        int victims = 0;
        for(Actor actor : actors)
            if(area.isColliding(actor.getBox()))
                if(actor.hurt(instigator, type, amount, location))
                    ++victims;
        return victims;
    }

    @Override
    public void addDoneLevel(PlayableLevel level)
    {
        if(level == null)
            throw new NullPointerException("Level cannot be null!");

        levelsDone.add(level.getClass());
    }

    @Override
    public boolean isDone(PlayableLevel level)
    {
        return levelsDone.contains(level.getClass());
    }
}
