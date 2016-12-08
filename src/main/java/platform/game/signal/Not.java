package platform.game.signal;

/**
 * The logic NOT operator.
 */
public final class Not implements Signal
{
    private final Signal signal;

    public Not(Signal signal)
    {
        if(signal == null)
            throw new NullPointerException();
        this.signal = signal;
    }

    @Override
    public boolean isActive()
    {
        return !signal.isActive();
    }
}
