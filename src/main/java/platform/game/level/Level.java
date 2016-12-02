package platform.game.level;

import platform.game.Actor;
import platform.game.World;
import platform.game.living.Player;
import platform.game.overlay.Overlay;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

/**
 * Base class for level factories, which provides fade in transition. Subclasses
 * are requires to override <code>register</code>.
 */
public abstract class Level extends Actor
{
    private double fadein;

    public Level()
    {
        fadein = 1.0;
    }

    @Override
    public int getPriority()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public void update(Input input)
    {
        fadein -= input.getDeltaTime();
        if(fadein <= 0.0)
            getWorld().unregister(this);
    }

    @Override
    public void draw(Input input, Output output)
    {
        Sprite sprite = getSprite("pixel.black");
        output.drawSprite(sprite, output.getBox(), 0.0, fadein);
    }

    @Override
    public void register(World world)
    {
        super.register(world);

        final Player player = new Player(getSpawn());
        world.register(player);
        world.register(new Overlay(player));
    }

    public abstract Vector getSpawn();

    /**
     * @return a new instance of default level
     */
    public static Level createDefaultLevel()
    {
        return new Level4();
    }
}