package platform.game.living;

import platform.game.World;
import platform.game.overlay.Overlay;
import platform.util.Vector;

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
}
