package platform.game.block;

import platform.game.Actor;
import platform.game.signal.Signal;
import platform.game.util.ColoredItem;
import platform.game.util.Damage;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Button extends Actor implements Signal
{
    private boolean isPressed = false;
    private final ColoredItem color;
    private final Box box;

    public Button(Vector position, ColoredItem color)
    {
        this.box = new Box(position, position.add(new Vector(1, 1)));
        this.color = color;
    }

    @Override
    public Box getBox()
    {
        return box;
    }

    @Override
    public void draw(Input input, Output output)
    {
        output.drawSprite(getSprite(color.getButtonSprite(isPressed)), getBox());
    }

    @Override
    public boolean hurt(Actor instigator, Damage type, double amount, Vector location)
    {
        if(type == Damage.TOUCH && !isPressed)
        {
            isPressed = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isActive()
    {
        return isPressed;
    }
}
