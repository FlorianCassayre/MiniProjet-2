package platform.game.level;

import platform.game.Actor;
import platform.game.util.Priority;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;

/**
 * Base class for level factories, which provides a fading transition effect.
 * Subclasses must override <code>register(World)</code> in order to register their actors.
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
        return Priority.LEVEL;
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
}