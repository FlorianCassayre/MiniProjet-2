package platform.game.level;

import platform.game.World;
import platform.game.block.solid.HiddenBlock;
import platform.game.block.transparent.Exit;
import platform.game.block.transparent.Torch;
import platform.game.item.Key;
import platform.game.item.VariableKey;
import platform.game.block.solid.Door;
import platform.game.registry.StoneBlockGenerator;
import platform.game.util.ColoredItem;
import platform.util.Vector;

public class Level4 extends PlayableLevel
{
    @Override
    public void register(World world)
    {
        super.register(world);

        world.register(StoneBlockGenerator.BLOCK_3X1.createBlock(new Vector(-1, -1), false));

        world.register(StoneBlockGenerator.BLOCK_3X2.createBlock(new Vector(13, 4)));

        Torch torch= new Torch(new Vector(-1,0));
        world.register(torch);

        //creating keys
        final Key redKey = new Key(new Vector(0, -0.5), ColoredItem.RED);
        final Key greenKey = new Key(new Vector(0, -0.5), ColoredItem.GREEN);
        VariableKey variableKey= new VariableKey(redKey, greenKey, torch);
        world.register(variableKey);

        //creating hidden blocks
        HiddenBlock redBlock = new HiddenBlock(new Vector(4,1), redKey);
        world.register(redBlock);
        HiddenBlock greenBlock = new HiddenBlock(new Vector(8,3),greenKey);
        world.register(greenBlock);


        world.register(new Exit(new Vector(14, 6), this));
    }

    @Override
    public Level getNextLevelOnDeath()
    {
        return new Level4();
    }

    @Override
    public Vector getSpawn()
    {
        return new Vector(0, 0);
    }
}
