package platform.game.level;

import platform.game.*;
import platform.game.block.Door;
import platform.game.block.Exit;
import platform.game.block.Jumper;
import platform.game.block.Lever;
import platform.game.block.Spike;
import platform.game.item.Key;
import platform.game.overlay.Limits;
import platform.game.registry.BlockGenerator;
import platform.game.util.KeyDoorColor;
import platform.util.Box;
import platform.util.Vector;

public class Level3 extends Level
{
    @Override
    public void register(World world)
    {
        super.register(world);

        //block registration
        world.register(BlockGenerator.BLOCK_1X1.createBlock(new Vector(4.5, 0)));
        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(5.5, 0)));
        world.register(BlockGenerator.BLOCK_1X3.createBlock(new Vector(1.5, -3)));
        world.register(BlockGenerator.BLOCK_1X3.createBlock(new Vector(1.5, -6)));
        world.register(BlockGenerator.BLOCK_1X3.createBlock(new Vector(3.5, -3)));
        world.register(BlockGenerator.BLOCK_1X3.createBlock(new Vector(3.5, -6)));
        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(1.5, -7)));

        //creating keys
        final Key redKey = new Key(new Vector(2.5, 3), KeyDoorColor.RED);
        world.register(redKey);
        final Key greenKey = new Key(new Vector(4.5, 5), KeyDoorColor.GREEN);
        world.register(greenKey);
        final Key yellowKey = new Key(new Vector(3.5, 4), KeyDoorColor.YELLOW);
        world.register(yellowKey);
        final Key blueKey = new Key(new Vector(0, 6), KeyDoorColor.BLUE);
        world.register(blueKey);

        //creating lever
        final Lever lever = new Lever(new Vector(9, 4));
        world.register(lever);

        //creating doors
        final Door door1 = new Door(new Vector(2.5, -1), KeyDoorColor.RED, redKey);
        final Door door2 = new Door(new Vector(2.5, -3), KeyDoorColor.GREEN, greenKey);
        final Door door4 = new Door(new Vector(2.5, -5), KeyDoorColor.YELLOW, yellowKey);
        final Door door3 = new Door(new Vector(0, 0), KeyDoorColor.BLUE, blueKey);
        final Exit exit = new Exit(new Vector(2.5, -6), null, lever);
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

        //world limits
        world.register(new Limits(new Box(Vector.ZERO, 40, 20)));
    }

    @Override
    public Vector getSpawn()
    {
        return new Vector(0.5, 10);
    }
}
