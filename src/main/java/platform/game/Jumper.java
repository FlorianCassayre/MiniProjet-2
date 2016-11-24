package platform.game;

import platform.game.Actor;
import platform.game.Damage;
import platform.game.World;
import platform.util.*;

public class Jumper extends Actor
{
    private double cooldown;
    private Vector position;

    public Jumper(Vector position)
    {
        this.position = position;
    }

    @Override
    public int getPriority()
    {
        return 1000;
    }

    @Override
    public void update(Input input)
    {
        super.update(input);
        cooldown -= input.getDeltaTime();
    }

    public void draw(Input input, Output output)
    {
        Sprite sprite;
        if(cooldown > 0)
            sprite = getSprite("jumper.extended");
        else
            sprite = getSprite("jumper.normal");
        output.drawSprite(sprite, getBox().add(new Vector(0, 0.5)));
    }

    @Override
    public void interact(Actor other)
    {
        super.interact(other);

        if(cooldown <= 0 && getBox().isColliding(other.getBox()))
        {
            Vector below = new Vector(position.getX(), position.getY() - 1.0);
            if(other.hurt(this, Damage.AIR, 10.0, below))
            {
                cooldown = 0.5;
            }

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
        return new Box(new Vector(position.getX(), position.getY()), 1, 1);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
