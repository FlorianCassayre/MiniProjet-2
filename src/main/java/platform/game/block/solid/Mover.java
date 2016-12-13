package platform.game.block.solid;

import platform.game.Actor;
import platform.game.signal.Signal;
import platform.game.util.InterpolationType;
import platform.game.util.Priority;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

import java.util.Objects;

/**
 * A moving block depending on a signal.
 * The path is defined by two points, and a specific interpolation method can be used.
 */
public class Mover extends Actor
{
    private final String sprite;
    private final Vector vectorOff, vectorOn, size;
    private Signal signal;
    private final InterpolationType interpolation;
    private final double duration;
    private double current;

    public Mover(String sprite, Vector vectorOff, Vector vectorOn, Vector size, Signal signal, double duration, InterpolationType interpolation)
    {
        this.sprite = Objects.requireNonNull(sprite);
        this.vectorOff = Objects.requireNonNull(vectorOff);
        this.vectorOn = Objects.requireNonNull(vectorOn);
        this.size = Objects.requireNonNull(size);

        this.signal = Objects.requireNonNull(signal);

        this.interpolation = Objects.requireNonNull(interpolation, "You must choose a valid interpolation mode.");
        this.duration = duration;
    }

    public Mover(String sprite, Vector vectorOff, Vector vectorOn, Vector size, Signal signal)
    {
        this(sprite, vectorOff, vectorOn, size, signal, 1.0, InterpolationType.CUBIC);
    }

    @Override
    public Box getBox()
    {
        final double currentInterpolation = interpolation.interpolate(current / duration);

        Vector position = new Vector((vectorOn.getX() - vectorOff.getX()) * currentInterpolation + vectorOff.getX(), (vectorOn.getY() - vectorOff.getY()) * currentInterpolation + vectorOff.getY());
        return new Box(position, position.add(size));
    }

    @Override
    public void update(Input input)
    {
        super.update(input);

        if(signal.isActive())
        {
            current += input.getDeltaTime();
            if(current > duration)
                current = duration;
        }
        else
        {
            current -= input.getDeltaTime();
            if(current < 0.0)
                current = 0.0;
        }
    }

    @Override
    public void draw(Input input, Output output)
    {
        output.drawSprite(getSprite(sprite), getBox());
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }

    @Override
    public int getPriority()
    {
        return Priority.BLOCK;
    }
}
