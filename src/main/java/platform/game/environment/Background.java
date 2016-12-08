package platform.game.environment;

import platform.game.Actor;
import platform.game.util.Damage;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

/**
 * A background image.
 */
public class Background extends Actor
{
    private final String sprite;

    public Background(String sprite)
    {
        this.sprite = sprite;
    }

    @Override
    public void draw(Input input, Output output)
    {
        output.drawSprite(getSprite(sprite), output.getBox());
    }

    @Override
    public int getPriority()
    {
        return Integer.MIN_VALUE;
    }

    @Override
    public Box getBox()
    {
        return new Box(Vector.ZERO, Vector.ZERO);
    }

    @Override
    public boolean hurt(Actor instigator, Damage type, double amount, Vector location)
    {
        return false; // FIXME: shouldn't be (re)defined here
    }
}
