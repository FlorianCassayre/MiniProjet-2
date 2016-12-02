package platform.game.block;

import platform.game.signal.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Vector;

public class Mover extends Block
{
    private final Vector vectorOff, vectorOn, size;
    private Signal signal;
    private double current;

    public Mover(String sprite, Vector vectorOff, Vector vectorOn, Vector size, Signal signal)
    {
        super(new Box(vectorOff, vectorOff.add(size)), sprite);

        this.vectorOff = vectorOff;
        this.vectorOn = vectorOn;
        this.size = size;

        this.signal = signal;
    }

    @Override
    public Box getBox()
    {
        Vector position = new Vector((vectorOn.getX() - vectorOff.getX()) * current, (vectorOn.getY() - vectorOff.getY()) * current);
        return new Box(position, position.add(size));
    }

    @Override
    public void update(Input input)
    {
        super.update(input);
        if(signal.isActive())
        {
            current += input.getDeltaTime();
            if(current > 1.0)
                current = 1.0;
        }
        else
        {
            current -= input.getDeltaTime();
            if(current < 0.0)
                current = 0.0;
        }
    }
}
