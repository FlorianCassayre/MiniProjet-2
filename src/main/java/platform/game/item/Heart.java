package platform.game.item;

import platform.game.Actor;
import platform.game.FixedActor;
import platform.game.util.Damage;
import platform.game.entity.living.Player;
import platform.game.util.Priority;
import platform.util.*;

import java.util.Objects;

/**
 * An item that adds one heart to the player who takes it.
 */
public class Heart extends FixedActor
{
    private static final int COOLDOWN_DEFAULT = 10;
    private double cooldown = 0;
    private boolean respawn;

    public Heart(Vector position, boolean respawn)
    {
        super(new Box(Objects.requireNonNull(position).add(new Vector(0.25, 0.25)), position.add(new Vector(0.75, 0.75))));

        this.respawn = respawn;
    }

    public Heart(Vector position)
    {
        this(position, false);
    }

    @Override
    public int getPriority()
    {
        return Priority.GROUND_ITEM;
    }

    @Override
    public void update(Input input)
    {
        super.update(input);
        cooldown -= input.getDeltaTime();
    }

    @Override
    public void draw(Input input, Output output)
    {
        if(cooldown <= 0)
        {
            output.drawSprite(getSprite("heart.full"), getBox());
        }
    }

    @Override
    public void interact(Actor other)
    {
        super.interact(other);

        if(cooldown <= 0 && other instanceof Player && getBox().isColliding(other.getBox()))
        {
            other.hurt(this, Damage.HEAL, 1.0, getPosition());
            cooldown = respawn ? COOLDOWN_DEFAULT : Double.POSITIVE_INFINITY;
        }
    }
}
