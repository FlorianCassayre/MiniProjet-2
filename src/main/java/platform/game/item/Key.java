package platform.game.item;

import platform.game.Actor;
import platform.game.util.Damage;
import platform.game.util.ColoredItem;
import platform.game.living.Player;
import platform.game.signal.Signal;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Key extends Actor implements Signal
{
    private final Box box;
    private ColoredItem color;
    protected boolean taken;

    public Key(Vector position, ColoredItem color)
    {
        this.box = new Box(position.add(new Vector(0.25, 0.25)), position.add(new Vector(0.75, 0.75)));
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
        if(!taken)
            output.drawSprite(getSprite(this.color.getKeySprite()), getBox());
    }

    @Override
    public boolean hurt(Actor instigator, Damage type, double amount, Vector location)
    {
        if(instigator instanceof Player && type == Damage.TOUCH)
        {
            taken = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isSolid()
    {
        return false;
    }

    @Override
    public boolean isActive()
    {
        return taken;
    }

    public ColoredItem getColor()
    {
        return color;
    }
}