package platform.game.signal;

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
