package platform.game.block;

import platform.game.signal.Signal;
import platform.game.util.InterpolationType;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

public class Mover extends Block
{
    private final Vector vectorOff, vectorOn, size;
    private Signal signal;
    private final InterpolationType interpolation;
    private final double duration;
    private double current;

    public Mover(String sprite, Vector vectorOff, Vector vectorOn, Vector size, Signal signal, double duration, InterpolationType interpolation)
    {
        super(new Box(vectorOff, vectorOff.add(size)), sprite);

        this.vectorOff = vectorOff;
        this.vectorOn = vectorOn;
        this.size = size;

        this.signal = signal;

        this.interpolation = interpolation;
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
