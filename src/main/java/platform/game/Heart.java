package platform.game;

import platform.util.*;

public class Heart extends Actor
{
    private Vector position;
    private static final int COOLDOWN_DEFAULT = 10;
    private double cooldown = 0;

    public Heart(Vector position)
    {
        this.position = position;
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
    public Vector getPosition()
    {
        return position;
    }

    @Override
    public Box getBox()
    {
        return new Box(new Vector(position.getX() + 0.25, position.getY() + 0.25), new Vector(position.getX() + 0.75, position.getY() + 0.75));
    }
}
