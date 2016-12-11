package platform.game.block.solid;

import platform.game.signal.Signal;
import platform.util.Vector;

public class MetalMover extends Mover
{
    public MetalMover(Vector vectorOff, Vector vectorOn, int length, boolean direction, Signal signal) // true for horizontal / false for vertical
    {
        super("metal." + (direction ? "horizontal" : "vertical"), vectorOff, vectorOn, direction ? new Vector(length, 1) : new Vector(1, length), signal);
    }
}
