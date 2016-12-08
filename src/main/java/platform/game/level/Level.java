package platform.game.level;

import platform.game.Actor;
import platform.game.World;
import platform.game.entity.living.Player;
import platform.game.environment.Background;
import platform.game.environment.Limits;
import platform.util.*;

/**
 * Base class for level factories, which provides fade in transition.
 * Subclasses must override <code>register</code>.
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

        world.register(new Background(getBackgroundSprite()));

        final Player player = new Player(getSpawn());
        world.register(player);

        world.register(new Limits(new Box(Vector.ZERO, getLimits().getX(), getLimits().getY())));
    }

    /**
     * Returns the location of the level spawn.
     * @return a position vector
     */
    public Vector getSpawn()
    {
        return Vector.ZERO;
    }

    /**
     * Returns the bounds of this level.
     * Every actor that exits these limits will be deleted.
     * @return a box
     */
    public Vector getLimits()
    {
        return new Vector(40, 20);
    }

    /**
     * Returns the image to be used for the background.
     * @return the sprite name
     */
    public String getBackgroundSprite()
    {
        return "pixel.white";
    }

    /**
     * @return a new instance of the default level.
     */
    public static Level createDefaultLevel()
    {
        return new Level5();
    }
}