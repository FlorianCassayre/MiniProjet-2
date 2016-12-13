package platform.game.block.solid;

import platform.game.Actor;
import platform.game.signal.Signal;
import platform.game.util.InterpolationType;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

import java.util.Objects;

/**
 * A moving block depending on a signal.
 * The path is defined by two points, and a specific interpolation method can be used.
 */
public class Mover extends Actor
{
    private final Vector vectorOff, vectorOn, size;
    private Signal signal;
    private final InterpolationType interpolation;
    private final double duration;
    private double current;

    public Mover(String sprite, Vector vectorOff, Vector vectorOn, Vector size, Signal signal, double duration, InterpolationType interpolation)
    {
        super(new Box(Objects.requireNonNull(vectorOff), Objects.requireNonNull(vectorOff).add(Objects.requireNonNull(size))), Objects.requireNonNull(sprite));

        this.vectorOff = vectorOff;
        this.vectorOn = vectorOn;
        this.size = size;

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
}
