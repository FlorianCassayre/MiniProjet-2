package platform.game.signal;

import java.util.Objects;

public class Flip implements Signal
{
    private final Signal signal;
    private boolean isActive;

    public Flip(Signal signal)
    {
        this.signal = Objects.requireNonNull(signal);
    }

    @Override
    public boolean isActive()
    {
        if(signal.isActive())
            isActive = true;
        return isActive;
    }
}