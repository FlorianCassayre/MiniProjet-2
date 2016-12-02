package platform.game.block;

import platform.game.Actor;
import platform.game.util.KeyDoorColor;
import platform.game.item.Key;
import platform.game.signal.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Door extends Actor implements Signal
{
    private final Box box;
    private final KeyDoorColor color;
    private Signal signal;

    public Door(Vector position, KeyDoorColor color, Signal signal)
    {
        this.box = new Box(position, position.add(new Vector(1, 1)));
        this.color = color;
        this.signal = signal;
    }

    public Door(Vector position, Key key)
    {
        this(position, key.getColor(), key);
    }

    @Override
    public Box getBox()
    {
        return box;
    }

    @Override
    public void draw(Input input, Output output)
    {
        if(!isActive())
            output.drawSprite(getSprite(color.getDoorSprite()), getBox());
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
