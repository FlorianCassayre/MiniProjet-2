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

        //block registration
        world.register(StoneBlockGenerator.BLOCK_1X1.createBlock(new Vector(4.5, 0)));
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(5.5, 0)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(1.5, -3)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(1.5, -6)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(3.5, -3)));
        world.register(StoneBlockGenerator.BLOCK_1X3.createBlock(new Vector(3.5, -6)));
        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(1.5, -7)));

        //creating keys
        final Key redKey = new Key(new Vector(2.5, 3), ColoredItem.RED);
        world.register(redKey);
        final Key greenKey = new Key(new Vector(4.5, 5), ColoredItem.GREEN);
        world.register(greenKey);
        final Key yellowKey = new Key(new Vector(3.5, 4), ColoredItem.YELLOW);
        world.register(yellowKey);
        final Key blueKey = new Key(new Vector(0, 6), ColoredItem.BLUE);
        world.register(blueKey);

        //creating lever
        final Lever lever = new Lever(new Vector(9, 4));
        world.register(lever);

        //creating doors
        final Door door1 = new Door(new Vector(2.5, -1), redKey);
        final Door door2 = new Door(new Vector(2.5, -3), greenKey);
        final Door door4 = new Door(new Vector(2.5, -5), yellowKey);
        final Door door3 = new Door(new Vector(0, 0), blueKey);
        final Exit exit = new Exit(new Vector(2.5, -6), this, lever);
        world.register(door1);
        world.register(door2);
        world.register(door3);
        world.register(door4);
        world.register(exit);

        //creating jumpers
        world.register(new Jumper(new Vector(9, 0)));

        //creating spikes
        world.register(new Spike(new Vector(1.5, 0)));
        world.register(new Spike(new Vector(4.5, 1)));
    }

    @Override
    public Level getNextLevelOnDeath()
    {
        return new Level3();
    }

    @Override
    public Vector getSpawn()
    {
        return new Vector(0.5, 10);
    }
}
