package platform.game.signal;

import java.util.Objects;

/**
 * A logic gate.
 */
public abstract class LogicGate implements Signal
{
    private final Signal left, right;

    public LogicGate(Signal left, Signal right)
    {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
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
