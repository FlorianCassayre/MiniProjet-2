package platform.game.item;

import platform.game.Actor;
import platform.game.util.Damage;
import platform.game.util.ColoredItem;
import platform.game.entity.living.Player;
import platform.game.signal.Signal;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class VariableKey extends Key
{
    private Signal signal;
    private Key key, key1, key2;
    private ColoredItem color1, color2;

    public VariableKey(Key key1, Key key2, Signal signal)
    {
        super(key1.getPosition(), key1.getColor());
        this.signal = signal;
        this.key1 = key1;
        this.key2 = key2;
        this.color1 = key1.getColor();
        this.color2 = key2.getColor();
    }

    public void update(Input input)
    {
        if(signal.isActive())
        {
            this.key = key2;
        }
        else
        {
            this.key = key1;
        }
    }

    @Override
    public void draw(Input input, Output output)
    {
        if(!key1.taken && this.key == this.key1)
            output.drawSprite(getSprite(this.color1.getKeySprite()), getBox());
        else if(!key2.taken && this.key == this.key2)
            output.drawSprite(getSprite(this.color2.getKeySprite()), getBox());
    }

    @Override
    public boolean hurt(Actor instigator, Damage type, double amount, Vector location)
    {
        if(instigator instanceof Player && type == Damage.TOUCH)
        {
            if(this.key == key1)
            {
                key1.taken = true;
            }
            if(this.key == key2)
            {
                key2.taken = true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isActive()
    {
        return key1.taken && key2.taken;
    }

}