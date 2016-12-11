package platform.game.entity;

import platform.util.Input;
import platform.util.Vector;

/**
 * Represents a particle effect.
 */
public class Particle extends Entity
{
    public Particle(Vector position, Vector size, Vector velocity)
    {
        super(position, size, velocity, 1);
    }

    public Particle(Vector position, Vector size)
    {
        this(position, size, Vector.ZERO);
    }

    @Override
    public int getPriority()
    {
        return 500000;
    }
}
