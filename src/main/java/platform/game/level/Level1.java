package platform.game.level;

import platform.game.World;
import platform.game.block.Exit;
import platform.game.block.Jumper;
import platform.game.registry.BlockGenerator;
import platform.util.Vector;

public class Level1 extends Level
{
    @Override
    public void register(World world)
    {
        super.register(world);

        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, -1), false));

        world.register(BlockGenerator.BLOCK_3X2.createBlock(new Vector(2, 2)));

        world.register(new Jumper(new Vector(1, 0)));

        world.register(new Exit(new Vector(3, 4), null));
    }

    @Override
    public Vector getSpawn()
    {
        return new Vector(0, 0);
    }
}
