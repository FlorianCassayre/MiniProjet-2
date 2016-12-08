package platform.game.signal;

/**
 * The logic OR gate.
 */
public final class Or extends LogicGate
{
    public Or(Signal left, Signal right)
    {
        super(left, right);
    }

    @Override
    public boolean isActive()
    {
        return getLeft().isActive() || getRight().isActive();
    }
}
