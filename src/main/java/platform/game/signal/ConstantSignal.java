package platform.game.signal;

/**
 * A constant signal.
 */
public final class ConstantSignal implements Signal
{
    private final boolean isActive;

    public ConstantSignal(boolean isActive)
    {
        this.isActive = isActive;
    }

    @Override
    public boolean isActive()
    {
        return isActive;
    }
}
