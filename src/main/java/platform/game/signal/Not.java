package platform.game.signal;

import java.util.Objects;

/**
 * The logic NOT operator.
 */
public final class Not implements Signal
{
    private final Signal signal;

    public Not(Signal signal)
    {
        this.signal = Objects.requireNonNull(signal);
    }

    @Override
    public boolean isActive()
    {
        return !signal.isActive();
    }
}
