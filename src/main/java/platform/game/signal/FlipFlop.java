package platform.game.signal;

public class FlipFlop implements Signal
{
    private final Signal signal;
    private boolean lastState;
    private boolean isActive;

    public FlipFlop(boolean initial, Signal signal)
    {
        this.isActive = initial;
        this.signal = signal;
    }

    @Override
    public boolean isActive()
    {
        if(lastState != signal.isActive())
        {
            lastState = signal.isActive();
            if(signal.isActive())
                isActive = !isActive;
        }

        return isActive;
    }
}