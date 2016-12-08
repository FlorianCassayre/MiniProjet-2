package platform.game.entity.living;

import platform.game.Actor;
import platform.game.entity.Entity;
import platform.game.util.Priority;
import platform.util.*;

public class Overlay extends Actor
{
    public Entity entity;

    public Overlay(Entity entity)
    {
        this.entity = entity;
    }

    @Override
    public void update(Input input)
    {
        if(entity.getHealth() <= 0.0)
        {
            getWorld().unregister(this);
        }
    }

    @Override
    public void draw(Input input, Output output)
    {
        double health = entity.getHealth();
        for(int i = 1; i <= entity.getMaxHealth(); ++i)
        {
            String name;
            if(health >= i)
                name = "heart.full";
            else if(health >= i - 0.5)
                name = "heart.half";
            else
                name = "heart.empty";

            Sprite sprite = getSprite(name);
            output.drawSprite(sprite, new Box(entity.getPosition().add(new Vector(0.25 * (i - entity.getMaxHealth() / 2) - 0.25 / 2 , 0.5)), 0.25, 0.25));
        }
    }

    @Override
    public Box getBox()
    {
        return entity.getBox();
    }

    @Override
    public int getPriority()
    {
        return Priority.OVERLAY;
    }
}
