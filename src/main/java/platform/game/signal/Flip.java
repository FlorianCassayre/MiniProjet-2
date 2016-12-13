package platform.game.signal;

public class Flip implements Signal
{
    private final Signal signal;
    private boolean isActive;

    public Flip(Signal signal)
    {
        this.signal = signal;
    }

    @Override
    public boolean isActive()
    {
        if(signal.isActive())
            isActive = true;
        return isActive;
    }
}