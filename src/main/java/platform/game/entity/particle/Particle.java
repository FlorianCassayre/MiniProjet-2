package platform.game.entity.particle;

import platform.game.entity.Entity;
import platform.game.util.Priority;
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
        return Priority.ENTITY_PARTICLE;
    }
}
