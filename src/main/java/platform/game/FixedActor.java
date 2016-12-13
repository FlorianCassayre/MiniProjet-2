package platform.game;

import platform.util.Box;

import java.util.Objects;

/**
 * An actor whose position and size is fixed throughout the time.
 */
public class FixedActor extends Actor
{
    private final Box box;

    public FixedActor(Box box)
    {
        this.box = Objects.requireNonNull(box, "The box cannot be null.");
    }

    @Override
    public Box getBox()
    {
        return box;
    }
}
