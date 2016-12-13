package platform.game.level;

import platform.game.World;
import platform.game.block.solid.Door;
import platform.game.block.solid.MetalMover;
import platform.game.block.transparent.*;
import platform.game.item.Heart;
import platform.game.item.Key;
import platform.game.registry.StoneBlockGenerator;
import platform.game.signal.And;
import platform.game.signal.Not;
import platform.game.signal.Xor;
import platform.game.util.ColoredItem;
import platform.game.util.Direction;
import platform.util.Vector;

public class Level4 extends PlayableLevel
{
    @Override
    public void register(World world)
    {
        super.register(world);

        //creating blocks
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, -1)));
        world.register(StoneBlockGenerator.BLOCK_2X3.createBlock(new Vector(2, -3)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(3, 0)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(3, 3)));
        world.register(StoneBlockGenerator.BLOCK_2X3.createBlock(new Vector(3, 6)));
        world.register(StoneBlockGenerator.BLOCK_2X2.createBlock(new Vector(0, 9)));
        world.register(StoneBlockGenerator.BLOCK_2X3.createBlock(new Vector(-5, 10)));
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(-4, 13)));
        world.register(StoneBlockGenerator.BLOCK_2X1.createBlock(new Vector(-6, 13)));
        world.register(StoneBlockGenerator.BLOCK_2X1.createBlock(new Vector(1, 6)));
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(-2, 2)));
        world.register(StoneBlockGenerator.BLOCK_1X1.createBlock(new Vector(-1, 3)));
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, -3)));
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(-6, -1)));
        world.register(StoneBlockGenerator.BLOCK_2X3.createBlock(new Vector(-8, 2)));
        world.register(StoneBlockGenerator.BLOCK_2X2.createBlock(new Vector(-3, 6)));
        world.register(StoneBlockGenerator.BLOCK_2X1.createBlock(new Vector(-6, 7)));
        world.register(StoneBlockGenerator.BLOCK_3X2.createBlock(new Vector(-10, 7)));
        world.register(StoneBlockGenerator.BLOCK_1X2.createBlock(new Vector(-2, 8)));
        world.register(StoneBlockGenerator.BLOCK_2X2.createBlock(new Vector(5, 3)));
        world.register(StoneBlockGenerator.BLOCK_2X1.createBlock(new Vector(4, -1)));
        world.register(StoneBlockGenerator.BLOCK_3X2.createBlock(new Vector(6, -1)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(-2, 3)));

        //creating Spikes
        world.register(new Spike(new Vector(2, 5), Direction.DOWN));
        world.register(new Spike(new Vector(1, 5), Direction.DOWN));
        world.register(new Spike(new Vector(0, 3), Direction.UP));
        world.register(new Spike(new Vector(-3, 5), Direction.DOWN));
        world.register(new Spike(new Vector(-6, 8), Direction.UP));
        world.register(new Spike(new Vector(-10, 9), Direction.UP));
        world.register(new Spike(new Vector(4, 9), Direction.UP));
        world.register(new Spike(new Vector(-3, 12), Direction.DOWN));
        world.register(new Spike(new Vector(-8, 6), Direction.DOWN));
        world.register(new Spike(new Vector(8, 1), Direction.UP));

        //creating Hearts
        world.register(new Heart(new Vector(-9, 9)));
        world.register(new Heart(new Vector(5, 0)));

        //creating Jumpers
        world.register(new Jumper(new Vector(2, 0)));
        world.register(new Jumper(new Vector(-1, 4)));
        world.register(new Jumper(new Vector(-4, 0)));
        world.register(new Jumper(new Vector(4, 0)));
        world.register(new Jumper(new Vector(6, 5)));

        //creating Keys
        final Key keyBlue = new Key(new Vector(-8, 5), ColoredItem.BLUE);
        world.register(keyBlue);
        final Key keyRed = new Key(new Vector(-7, -1), ColoredItem.RED);
        world.register(keyRed);

        //creating Doors
        world.register(new Door(new Vector(0, -2), keyBlue));
        world.register(new Door(new Vector(-1, -2), keyRed));

        //creating Lever
        final Lever lever = new Lever(new Vector(2, 7));
        world.register(lever);

        //creating torch
        final Torch torch = new Torch(new Vector(-6, 0));
        world.register(torch);

        //creating movers
        world.register(new MetalMover(new Vector(-2, -3), new Vector(-2, -5), 3, false, torch));
        world.register(new MetalMover(new Vector(-3, 0), new Vector(-3, -3), 3, false, new Xor(lever, torch)));
        world.register(new MetalMover(new Vector(-4, -2), new Vector(-7, -2), 2, true, new Xor(lever, torch)));
        world.register(new MetalMover(new Vector(-7, 5), new Vector(-7, 7), 3, false, new And(new Not(lever), torch)));

        //creating exit
        world.register(new Exit(new Vector(1, -2), this, lever));
    }

    @Override
    public Level getNextLevelOnDeath()
    {
        return new Level4();
    }

    @Override
    public Vector getLimits()
    {
        return new Vector(30, 30);
    }

    @Override
    public String getBackgroundSprite()
    {
        return "background.castle";
    }
}
