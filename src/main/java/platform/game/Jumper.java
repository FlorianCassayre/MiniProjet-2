package platform.game;

import platform.game.Actor;
import platform.game.Damage;
import platform.game.World;
import platform.util.*;

public class Jumper extends Block
{
    private double cooldown;

    public Jumper(Vector position)
    {
        super(new Box(position, position.add(new Vector(1, 0.5))), "jumper.normal");
    }

    @Override
    public int getPriority()
    {
        return 1000;
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
