package platform.game;

import platform.util.Box;

public class FixedActor extends Actor
{
    private final Box box;

    public FixedActor(Box box)
    {
        if(box == null)
            throw new IllegalArgumentException("The box cannot be null.");

        this.box = box;
    }

    @Override
    public Box getBox()
    {
        return box;
    }
}
