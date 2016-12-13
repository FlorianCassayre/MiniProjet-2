package platform.game.level;

import platform.game.World;
import platform.game.block.solid.Door;
import platform.game.block.transparent.Exit;
import platform.game.block.transparent.Jumper;
import platform.game.block.transparent.Lever;
import platform.game.block.transparent.Spike;
import platform.game.item.Key;
import platform.game.registry.StoneBlockGenerator;
import platform.game.util.ColoredItem;
import platform.util.Vector;

public class Level3 extends PlayableLevel
{
    @Override
    public void register(World world)
    {
        super.register(world);

        //creating blocks
        world.register(StoneBlockGenerator.BLOCK_1X1.createBlock(new Vector(4.5, 0)));
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(5.5, 0)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(1.5, -3)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(1.5, -6)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(3.5, -3)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(3.5, -6)));
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(1.5, -7)));

        //creating keys
        final Key blueKey = new Key(new Vector(1.5, 2), ColoredItem.BLUE);
        world.register(blueKey);
        final Key redKey = new Key(new Vector(2.5, 3), ColoredItem.RED);
        world.register(redKey);
        final Key greenKey = new Key(new Vector(4.5, 5), ColoredItem.GREEN);
        world.register(greenKey);
        final Key yellowKey = new Key(new Vector(3.5, 4), ColoredItem.YELLOW);
        world.register(yellowKey);

        //creating lever
        final Lever lever = new Lever(new Vector(9, 4));
        world.register(lever);

        //creating doors
        world.register(new Door(new Vector(2.5,-3), blueKey));
        world.register(new Door(new Vector(2.5, -2), redKey));
        world.register(new Door(new Vector(2.5, -4), greenKey));
        world.register(new Door(new Vector(2.5, -5), yellowKey));

        //creating jumpers
        world.register(new Jumper(new Vector(9, 0)));

        //creating spikes
        world.register(new Spike(new Vector(1.5, 0)));
        world.register(new Spike(new Vector(4.5, 1)));

        //creating exit
        world.register(new Exit(new Vector(2.5, -6), this, lever));
    }

    @Override
    public Level getNextLevelOnDeath()
    {
        return new Level3();
    }

    @Override
    public Vector getSpawn()
    {
        return new Vector(3,10);
    }
}
