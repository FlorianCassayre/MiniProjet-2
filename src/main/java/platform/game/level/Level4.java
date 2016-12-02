package platform.game.level;

import platform.game.*;
import platform.game.block.Door;
import platform.game.block.Exit;
import platform.game.block.Torch;
import platform.game.item.Key;
import platform.game.item.VariableKey;
import platform.game.overlay.Limits;
import platform.game.registry.BlockGenerator;
import platform.game.signal.Not;
import platform.game.util.KeyDoorColor;
import platform.util.Box;
import platform.util.Vector;

public class Level4 extends Level
{
    @Override
    public void register(World world)
    {
        super.register(world);

        world.register(BlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, -1), false));

        world.register(BlockGenerator.BLOCK_3X2.createBlock(new Vector(13, 4)));

        Torch torch= new Torch(new Vector(-1,0));
        world.register(torch);

        //creating keys
        final Key redKey = new Key(new Vector(0, -0.5), KeyDoorColor.RED);
        final Key greenKey = new Key(new Vector(0, -0.5), KeyDoorColor.GREEN);
        VariableKey variableKey= new VariableKey(redKey, greenKey, torch);
        world.register(variableKey);

        //creating doors
        Door redDoor=new Door(new Vector(4,1), KeyDoorColor.RED, new Not(redKey));
        world.register(redDoor);
        Door greenDoor=new Door(new Vector(8,3), KeyDoorColor.GREEN, new Not(greenKey));
        world.register(greenDoor);


        world.register(new Exit(new Vector(14, 6), null));


        world.register(new Limits(new Box(Vector.ZERO, 40, 20)));
    }

    @Override
    public Vector getSpawn()
    {
        return new Vector(0, 0);
    }
}
