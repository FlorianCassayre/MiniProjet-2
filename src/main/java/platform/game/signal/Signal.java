package platform.game.signal;

/**
 * An element that can return a signal.
 */
public interface Signal
{
    /**
     * Returns the state of this signal.
     * @return true if the element is active, false else
     */
    boolean isActive();
}
