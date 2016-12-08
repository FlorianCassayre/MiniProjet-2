package platform.game.signal;

/**
 * The logic AND gate.
 */
public final class And extends LogicGate
{
    public And(Signal left, Signal right)
    {
        super(left, right);
    }

    @Override
    public boolean isActive()
    {
        return getLeft().isActive() && getRight().isActive();
    }
}
