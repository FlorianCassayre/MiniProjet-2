package platform.game.level;

import platform.game.*;
import platform.util.Box;
import platform.util.Vector;

public class Level3 extends Level
{
    @Override
    public void register(World world)
    {
        super.register(world);

        //player registration
        final Player player = new Player(new Vector(1, 2), new Vector(0, 0));
        world.register(player);
        world.register(new Overlay(player));

        //block registration
        //1

        //GF
        world.register(BlockGenerator.BLOCK_1X1.createBlock(new Vector(0.5, 0)));
        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(-4, 0)));
        world.register(BlockGenerator.BLOCK_1X1.createBlock(new Vector(-5, 0)));
        world.register(BlockGenerator.BLOCK_1X1.createBlock(new Vector(3, 0)));
        world.register(BlockGenerator.BLOCK_1X1.createBlock(new Vector(4, 0)));
        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(5, 0)));
        //-1
        world.register(BlockGenerator.BLOCK_1X3.createBlock(new Vector(0.5, -3)));
        world.register(BlockGenerator.BLOCK_1X3.createBlock(new Vector(0.5, -6)));
        world.register(BlockGenerator.BLOCK_1X3.createBlock(new Vector(3, -3)));
        world.register(BlockGenerator.BLOCK_1X3.createBlock(new Vector(3, -6)));
        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(0.75, -7)));

        //torch
        final Torch torch= new Torch(new Vector(3,2));
        world.register(torch);

        //creating signals

        //lever
        final Lever lever = new Lever(new Vector(4, 1));
        world.register(lever);

        //creating keys

        //yellow key
        Key yellowKey= new Key(new Vector(-2,1),KeyDoorColor.YELLOW);
        world.register(yellowKey);
        //red key
        Key redKey= new Key(new Vector(-3,1),KeyDoorColor.RED);
        world.register(redKey);
        //blue key
        final Key BlueKey= new Key(new Vector(-4,1),KeyDoorColor.BLUE);
        world.register(BlueKey);
        //green key
        final Key GreenKey= new Key(new Vector(-5,1),KeyDoorColor.GREEN);
        world.register(GreenKey);


        //creating doors
        //Door door1 = new Door(new Box(new Vector(0,1),new Vector(0,3)),KeyDoorColor.RED,redKey);
        //Door door2 = new Door(new Box(Vector.ZERO,4,4),KeyDoorColor.GREEN,greenKey);
        //Door door3 = new Door(new Box(Vector.ZERO,4,4),KeyDoorColor.BLUE,blueKey);
        //Door door4 = new Door(new Box(Vector.ZERO,4,4),KeyDoorColor.YELLOW,yellowKey);

        world.register(new Limits(new Box(Vector.ZERO, 40, 20)));
    }
}
