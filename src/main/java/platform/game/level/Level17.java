package platform.game.level;

import platform.game.World;
import platform.game.block.Jumper;
import platform.game.block.Lever;
import platform.game.block.Spike;
import platform.game.registry.BlockGenerator;
import platform.game.util.Direction;
import platform.util.Vector;

public class Level17 extends Level
{
    @Override
    public void register(World world)
    {
        super.register(world);

        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, -1)));
        world.register(BlockGenerator.BLOCK_2X3.createBlock(new Vector(2, -3)));
        world.register(BlockGenerator.BLOCK_1X3.createBlock(new Vector(3, 0)));
        world.register(BlockGenerator.BLOCK_1X3.createBlock(new Vector(3, 3)));
        world.register(BlockGenerator.BLOCK_1X3.createBlock(new Vector(3, 6)));

        world.register(BlockGenerator.BLOCK_2X1.createBlock(new Vector(1, 7)));

        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(-2, 2)));

        world.register(BlockGenerator.BLOCK_1X1.createBlock(new Vector(-1, 3)));

        world.register(new Spike(new Vector(2, 6), Direction.DOWN));
        world.register(new Spike(new Vector(1, 6), Direction.DOWN));

        world.register(new Spike(new Vector(-2, 3), Direction.UP));
        world.register(new Spike(new Vector(0, 3), Direction.UP));

        world.register(new Jumper(new Vector(2, 0)));
        world.register(new Jumper(new Vector(-1, 4)));

        final Lever leverA = new Lever(new Vector(2, 8));
        world.register(leverA);
    }
}
