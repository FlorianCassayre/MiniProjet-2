package platform.game.level;

import platform.game.World;
import platform.game.block.transparent.Exit;
import platform.game.block.transparent.Jumper;
import platform.game.registry.StoneBlockGenerator;
import platform.util.Vector;

public class Level1 extends PlayableLevel
{
    @Override
    public void register(World world)
    {
        super.register(world);

        //creating blocks
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, -1), false));
        world.register(StoneBlockGenerator.BLOCK_3X2.createBlock(new Vector(2, 2)));

        //creating jumper
        world.register(new Jumper(new Vector(1, 0)));

        //creating exit
        world.register(new Exit(new Vector(3, 4), this));
    }

    @Override
    public Level getNextLevelOnDeath()
    {
        return new Level1();
    }

    @Override
    public Vector getSpawn()
    {
        return new Vector(0, 0);
    }
}
