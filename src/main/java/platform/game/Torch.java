package platform.game;

import platform.util.*;

public class  Torch extends Block
{
    private boolean lit = false;

    public Torch(Vector position)
    {
        super(new Box(position.add(new Vector(0.25, 0.25)), position.add(new Vector(0.75, 0.75))), "torch");
    }

    @Override
    public void draw(Input input, Output output)
    {
        String sprite;
        if(!lit)
            sprite = "torch";
        else if(((int) (input.getTime() * 5)) % 2 == 0)
            sprite = "torch.lit.1";
        else
            sprite = "torch.lit.2";

        output.drawSprite(getSprite(sprite), getBox());
    }

    @Override
    public boolean hurt(Actor instigator, Damage type, double amount, Vector location)
    {
        switch(type)
        {
            case FIRE:
                if(!lit)
                {
                    lit = true;
                    return true;
                }
                else
                    return false;
            case AIR:
                if(lit)
                {
                    lit = false;
                    return true;
                }
                return false;
            default:
                return super.hurt(instigator, type, amount, location);
        }
    }

    @Override
    public boolean isSolid()
    {
        return false;
    }

}
