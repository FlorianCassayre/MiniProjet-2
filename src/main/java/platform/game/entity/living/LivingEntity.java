package platform.game.entity.living;

import platform.game.World;
import platform.game.entity.Entity;
import platform.game.util.Priority;
import platform.util.Vector;

/**
 * A living entity, such as a player or a mob.
 * Every living entity will have an overlay.
 */
public class LivingEntity extends Entity
{
    private final Overlay overlay;

    public LivingEntity(Vector position, Vector size, Vector velocity, int maxHealth)
    {
        super(position, size, velocity, maxHealth);

        this.overlay = new Overlay(this);
    }

    public LivingEntity(Vector position, Vector size, int maxHealth)
    {
        this(position, size, Vector.ZERO, maxHealth);
    }

    @Override
    public void register(World world)
    {
        super.register(world);

        getWorld().register(overlay);
    }

    @Override
    public int getPriority()
    {
        return Priority.ENTITY_LIVING;
    }
}
