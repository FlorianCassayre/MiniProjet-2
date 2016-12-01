package platform.game.level;

import platform.game.*;
import platform.util.Box;
import platform.util.Vector;

public class Level2 extends Level
{
    @Override
    public void register(World world)
    {
        super.register(world);

        final Player player = new Player(new Vector(1, 2), new Vector(0, 0));
        world.register(player);
        world.register(new Overlay(player));

        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, 1)));
        world.register(BlockGenerator.BLOCK_2X2.createBlock(new Vector(0, -1)));
        world.register(BlockGenerator.BLOCK_1X2.createBlock(new Vector(2, 0)));
        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, -3)));
        world.register(BlockGenerator.BLOCK_3X2.createBlock(new Vector(-4, -3)));
        world.register(BlockGenerator.BLOCK_3X2.createBlock(new Vector(2, -6)));
        world.register(BlockGenerator.BLOCK_2X3.createBlock(new Vector(5, -3)));

        world.register(new Jumper(new Vector(4, -4)));
        world.register(new Jumper(new Vector(6, 0)));

        final Lever lever = new Lever(new Vector(1, -2));
        world.register(lever);

        world.register(new Spike(new Vector(-1, 2)));
        world.register(new Spike(new Vector(-1, 0), Direction.DOWN));

        world.register(new Exit(new Vector(-4, -1), null, lever));


        world.register(new Limits(new Box(Vector.ZERO, 40, 20)));
    }
}
