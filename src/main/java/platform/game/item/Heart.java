package platform.game.item;

import platform.game.Actor;
import platform.game.util.Damage;
import platform.game.living.Player;
import platform.game.util.Priority;
import platform.util.*;

public class Heart extends Actor
{
    private final Box box;
    private static final int COOLDOWN_DEFAULT = 10;
    private double cooldown = 0;

    public Heart(Vector position)
    {
        position = position.add(new Vector(0.25, 0.25));
        this.box = new Box(position, position.add(new Vector(0.5, 0.5)));
    }

    @Override
    public int getPriority()
    {
        return Priority.HEART;
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
            cooldown = COOLDOWN_DEFAULT;
        }
    }

    @Override
    public Box getBox()
    {
        return box;
    }
}
