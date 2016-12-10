package platform.game.level;

import platform.game.Actor;
import platform.game.World;
import platform.game.entity.living.Player;
import platform.game.environment.Background;
import platform.game.environment.Limits;
import platform.util.*;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * Base class for level factories, which provides a fading transition effect.
 * Subclasses must override <code>register(World)</code> in order to register their actors.
 */
public abstract class Level extends Actor
{
    private static final Set<Integer> levelsDone = new HashSet<>();

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
    }

    @Override
    public void draw(Input input, Output output)
    {
        Sprite sprite = getSprite("pixel.black");
        output.drawSprite(sprite, output.getBox(), 0.0, fadein);
    }

    /**
     * @return a new instance of the default level (here, the level selection GUI).
     */
    public static Level createDefaultLevel()
    {
        return new SelectionLevel();
    }

    /**
     * Adds a level to the cleared levels.
     * @param level the level to add
     */
    public static void addDoneLevel(PlayableLevel level)
    {
        if(level == null)
            throw new NullPointerException("Level cannot be null!");

        levelsDone.add(level.getId());
    }

    /**
     * Checks if the specified level has been cleared.
     * @param level the level to check
     * @return true if the was cleared, false else
     */
    public static boolean isDone(PlayableLevel level)
    {
        return levelsDone.contains(level.getId());
    }
}