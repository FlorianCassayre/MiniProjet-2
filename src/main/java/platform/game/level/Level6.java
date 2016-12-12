package platform.game.level;

import com.sun.tools.hat.internal.util.Misc;
import platform.game.World;
import platform.game.block.solid.Door;
import platform.game.block.solid.MetalMover;
import platform.game.block.transparent.*;
import platform.game.item.Heart;
import platform.game.item.Key;
import platform.game.registry.MiscBlockGenerator;
import platform.game.registry.StoneBlockGenerator;
import platform.game.signal.*;
import platform.game.util.ColoredItem;
import platform.game.util.Direction;
import platform.util.Vector;

public class Level6 extends PlayableLevel
{
    @Override
    public void register(World world)
    {
        //creating grass blocks
        super.register(world);
        for (int i = -8; i <9 ; i++) {
            world.register(MiscBlockGenerator.GRASS_MIDDLE.createBlock(new Vector(i, 0)));
        }
        world.register(MiscBlockGenerator.GRASS_LEFT.createBlock(new Vector(-9,0)));
        world.register(MiscBlockGenerator.GRASS_RIGHT.createBlock(new Vector(9,0)));

        //creating jumpers
        world.register(new Jumper(new Vector(-5,1)));
        world.register(new Jumper(new Vector(5,1)));

        //creating spikes
        world.register(new Spike(new Vector(-8.5,1)));
        world.register(new Spike(new Vector(8.5,1)));

        //creating torches
        Torch torchLeft = new Torch(new Vector(-8,7));
        Torch torchRight = new Torch(new Vector(8,7));
        Torch torchMiddle = new Torch(new Vector(0,8.5));
        world.register(torchLeft);
        world.register(torchRight);
        world.register(torchMiddle);

        //creating levers
        Lever leverLeft = new Lever(new Vector(-4,1),true);
        Lever leverRight = new Lever(new Vector(4,1));
        world.register(leverLeft);
        world.register(leverRight);

        //verifying that all torches have been turned off before leaving to save energy
        Signal night = new And(new And(new Not(torchRight),new Not(torchLeft)), new Not(torchMiddle));

        //verifying alignment
        Signal leftAligned = new Or( new And(new Not(leverLeft),torchLeft), new And(leverLeft,torchMiddle));
        Signal rightAligned= new Or( new And(new Not(leverRight),torchMiddle), new And(leverRight,torchRight));
        Signal aligned = new And (leftAligned,rightAligned);

        //creating movers
        world.register(new MetalMover(new Vector(0,8.5),new Vector(0,5),1,false,aligned));
        world.register(new MetalMover(new Vector(8,5),new Vector(2,5),2,true,rightAligned));
        world.register(new MetalMover(new Vector(-8,5),new Vector(-3,5),2,true,leftAligned));

        //creating exit triggered by night
        world.register(new Exit(new Vector(0,8.5),new Level1(),night));
    }

    @Override
    public Level getNextLevelOnDeath()
    {
        return new Level6();
    }

    @Override
    public Vector getLimits()
    {
        return new Vector(30, 30);
    }

    @Override
    public String getBackgroundSprite()
    {
        return "background.sky";
    }

    @Override
    public Vector getSpawn()
    {
        return new Vector(0.5, 1);
    }
}
