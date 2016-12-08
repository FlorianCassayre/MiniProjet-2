package platform.game.signal;

/**
 * A logic gate.
 */
public abstract class LogicGate implements Signal
{
    private final Signal left, right;

    public LogicGate(Signal left, Signal right)
    {
        this.left = left;
        this.right = right;
    }

    protected Signal getLeft()
    {
        return left;
    }

    protected Signal getRight()
    {
        return right;
    }
}
