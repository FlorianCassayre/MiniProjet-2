package platform.game.level;

import platform.game.World;
import platform.game.block.transparent.Exit;
import platform.game.block.transparent.Jumper;
import platform.game.block.transparent.Lever;
import platform.game.block.transparent.Spike;
import platform.game.registry.StoneBlockGenerator;
import platform.game.util.Direction;
import platform.util.Vector;

public class Level2 extends PlayableLevel
{
    @Override
    public void register(World world)
    {
        super.register(world);

        //creating blocks
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, 1)));
        world.register(StoneBlockGenerator.BLOCK_2X2.createBlock(new Vector(0, -1)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(2, -1)));
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, -3)));
        world.register(StoneBlockGenerator.BLOCK_3X2.createBlock(new Vector(-4, -3)));
        world.register(StoneBlockGenerator.BLOCK_3X2.createBlock(new Vector(2, -6)));
        world.register(StoneBlockGenerator.BLOCK_2X3.createBlock(new Vector(5, -3)));
        world.register(StoneBlockGenerator.BLOCK_1X1.createBlock(new Vector(-1, 0)));

        //creating jumpers
        world.register(new Jumper(new Vector(4, -4)));
        world.register(new Jumper(new Vector(6, 0)));

        //creating lever
        final Lever lever = new Lever(new Vector(1, -2));
        world.register(lever);

        //creating spikes
        world.register(new Spike(new Vector(-1, 2)));
        world.register(new Spike(new Vector(-1, -1), Direction.DOWN));
        world.register(new Spike(new Vector(2, -2), Direction.DOWN));

        //creating exit
        world.register(new Exit(new Vector(-4, -1), this, lever));
    }

    @Override
    public Level getNextLevelOnDeath()
    {
        return new Level2();
    }

    @Override
    public Vector getSpawn()
    {
        return new Vector(1, 2);
    }
}
