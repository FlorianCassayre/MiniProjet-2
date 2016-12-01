package platform.game;

import platform.game.signal.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;

public class Door extends Block implements Signal
{
    private Signal signal;

    public Door(Box box, KeyDoorColor color, Signal signal)
    {
        super(box, color.getDoorSprite());

        this.signal = signal;
    }

    public Door(Box box, Key key)
    {
        this(box, key.getColor(), key);
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
