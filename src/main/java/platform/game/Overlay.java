package platform.game;

import platform.util.*;

public class Overlay extends Actor
{
    public Player player;

    public Overlay(Player player)
    {
        this.player = player;
    }

    @Override
    public void update(Input input)
    {
        if(player.getWorld() == null)
        {
            getWorld().unregister(this);
        }
    }

    @Override
    public void draw(Input input, Output output)
    {
        double health = player.getHealth();
        for(int i = 1; i <= player.getHealthMax(); ++i)
        {
            String name;
            if(health >= i)
                name = "heart.full";
            else if(health >= i - 0.5)
                name = "heart.half";
            else
                name = "heart.empty";

            Sprite sprite = getSprite(name);
            output.drawSprite(sprite, new Box(player.getPosition().add(new Vector(0.25 * (i - player.getHealthMax() / 2) - 0.25 / 2 , 0.5)), 0.25, 0.25));

        }
    }

    @Override
    public Box getBox()
    {
        return player.getBox();
    }

    @Override
    public int getPriority()
    {
        return Priority.OVERLAY;
    }
}
