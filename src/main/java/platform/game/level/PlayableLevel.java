package platform.game.level;

import platform.game.World;
import platform.game.entity.living.Player;
import platform.game.environment.Background;
import platform.game.environment.Limits;
import platform.util.*;

import java.awt.event.KeyEvent;

public abstract class PlayableLevel extends Level
{
    @Override
    public void register(World world)
    {
        super.register(world);

        world.register(new Background(getBackgroundSprite()));

        final Player player = new Player(getSpawn());
        world.register(player);

        world.register(new Limits(new Box(Vector.ZERO, getLimits().getX(), getLimits().getY())));

        getWorld().setNextLevel(getNextLevelOnDeath());
    }

    @Override
    public void update(Input input)
    {
        super.update(input);

        if(input.getKeyboardButton(KeyEvent.VK_ESCAPE).isPressed())
        {
            getWorld().setNextLevel(createDefaultLevel());
            getWorld().nextLevel();
        }
    }

    /**
     * Returns the level id. Each level id must be unique.
     * @return the level id
     */
    public abstract int getId();

    /**
     * Returns the next level to run when the player dies.
     * @return the next level
     */
    public abstract Level getNextLevelOnDeath();

    /**
     * Returns the next level to run when the player clears this one.
     * @return the next level
     */
    public Level getNextLevelOnClear()
    {
        return createDefaultLevel();
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
}
