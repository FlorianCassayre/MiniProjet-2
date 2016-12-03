package platform.game.level;

import platform.game.Actor;
import platform.game.World;
import platform.game.living.Player;
import platform.game.overlay.Limits;
import platform.util.*;

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

        world.register(new Limits(new Box(Vector.ZERO, getLimits().getX(), getLimits().getY())));
    }

    public Vector getSpawn()
    {
        return Vector.ZERO;
    }

    public Vector getLimits()
    {
        return new Vector(40, 20);
    }

    /**
     * @return a new instance of default level
     */
    public static Level createDefaultLevel()
    {
        return new BasicLevel();
    }
}