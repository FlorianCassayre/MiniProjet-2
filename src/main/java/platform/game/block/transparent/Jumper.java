package platform.game.block.transparent;

import platform.game.Actor;
import platform.game.FixedActor;
import platform.game.util.Damage;
import platform.game.util.Priority;
import platform.util.*;

/**
 * A block that gives some vertical velocity to a player when touched.
 */
public class Jumper extends FixedActor
{
    private double cooldown;

    public Jumper(Vector position)
    {
        super(new Box(position, position.add(new Vector(1, 0.5))));
    }

    @Override
    public int getPriority()
    {
        return Priority.JUMPER;
    }

    @Override
    public void update(Input input)
    {
        cooldown -= input.getDeltaTime();
    }

    @Override
    public void draw(Input input, Output output)
    {
        Sprite sprite;
        if(cooldown > 0)
            sprite = getSprite("jumper.extended");
        else
            sprite = getSprite("jumper.normal");
        output.drawSprite(sprite, new Box(getBox().getMin(), getBox().getMax().add(new Vector(0, 0.5))));
    }

    @Override
    public void interact(Actor other)
    {
        if(cooldown <= 0 && getBox().isColliding(other.getBox()))
        {
            Vector below = new Vector(getPosition().getX(), getPosition().getY() - 1.0);
            if(other.hurt(this, Damage.AIR, 10.0, below))
            {
                cooldown = 0.5;
            }
        }
    }
}
