package platform.game.signal;

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
