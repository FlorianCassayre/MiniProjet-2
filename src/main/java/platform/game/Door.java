package platform.game;

import platform.game.signal.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Door extends Block implements Signal
{
    private Signal signal;

    public Door(Vector position, KeyDoorColor color, Signal signal)
    {
        super(new Box(position, position.add(new Vector(1, 1))), color.getDoorSprite());

        this.signal = signal;
    }

    public Door(Vector position, Key key)
    {
        this(position, key.getColor(), key);
    }

    @Override
    public void draw(Input input, Output output)
    {
        if(!isActive())
            super.draw(input, output);
    }

    @Override
    public boolean isSolid()
    {
        return !isActive();
    }

    @Override
    public boolean isActive()
    {
        return signal.isActive();
    }
}
