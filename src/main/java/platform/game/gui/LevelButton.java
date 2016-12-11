package platform.game.gui;

import platform.game.block.solid.Block;
import platform.game.level.Level;
import platform.util.*;

/**
 * A clickable button that leads to a specified level.
 * Should be used in GUI.
 */
public class LevelButton extends Block
{
    private final int levelId;
    private final Level next;

    public LevelButton(Vector position, int levelId, boolean finished, Level next)
    {
        super(new Box(position, 1, 1), finished ? "box.item.empty" : "box.item.disabled.empty");

        if(next == null)
            throw new NullPointerException("Next level cannot be null!");

        this.levelId = levelId;
        this.next = next;
    }

    @Override
    public void draw(Input input, Output output)
    {
        super.draw(input, output);

        String string = Integer.toString(levelId);

        final Sprite sprite1 = getSprite("digit." + string.charAt(0));

        if(string.length() == 1) // One digit
        {
            output.drawSprite(sprite1, new Box(getPosition(), 0.5 * sprite1.getWidth() / sprite1.getHeight(), 0.5)); // FIXME relative to size
        }
        else // Two digits
        {
            final Sprite sprite2 = getSprite("digit." + string.charAt(1));
            output.drawSprite(sprite1, new Box(getPosition().add(new Vector(-0.20, 0)), 0.5 * sprite1.getWidth() / sprite1.getHeight(), 0.5));
            output.drawSprite(sprite2, new Box(getPosition().add(new Vector(0.20, 0)), 0.5 * sprite2.getWidth() / sprite2.getHeight(), 0.5));
        }
    }

    @Override
    public void update(Input input)
    {
        if(input.getMouseButton(1).isPressed() && getBox().isColliding(input.getMouseLocation()))
        {
            getWorld().setNextLevel(next);
            getWorld().nextLevel();
        }
    }
}
