package platform.game.level;

import platform.game.*;
import platform.util.Box;
import platform.util.Vector;

public class Level1 extends Level
{
    @Override
    public void register(World world)
    {
        super.register(world);

        final Player player = new Player(new Vector(0, 0), new Vector(0, -1));
        world.register(player);

        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, -1), false));

        world.register(BlockGenerator.BLOCK_3X2.createBlock(new Vector(2, 2)));

        world.register(new Jumper(new Vector(1, 0)));

        world.register(new Exit(new Vector(3, 4), null));


        world.register(new Limits(new Box(Vector.ZERO, 40, 20)));
    }
}
