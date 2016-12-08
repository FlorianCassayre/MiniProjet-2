package platform.game.signal;

/**
 * The logic XOR gate.
 */
public final class Xor extends LogicGate
{
    public Xor(Signal left, Signal right)
    {
        super(left, right);
    }

    @Override
    public boolean isActive()
    {
        return getLeft().isActive() && !getRight().isActive() || !getLeft().isActive() && getRight().isActive();
    }
}
