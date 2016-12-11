package platform.game.level;

import platform.game.World;
import platform.game.block.solid.Block;
import platform.game.block.solid.Door;
import platform.game.block.solid.Mover;
import platform.game.block.transparent.*;
import platform.game.item.Key;
import platform.game.util.ColoredItem;
import platform.util.Box;
import platform.util.Vector;

public class Level0 extends PlayableLevel
{
    @Override
    public void register(World world)
    {
        super.register(world);

        // Register a new instance, to restart level automatically
        world.setNextLevel(new Level0());

        // Create blocks
        //world.register(new Block(new Box(new Vector(0, 0), 4, 2), world.getLoader().getSprite("stone.broken.2")));
        ///world.register(new Block(new Box(new Vector(-1.5, 1.5), 1, 1), world.getLoader().getSprite("stone.broken.1")));

        Box zone1 = new Box(new Vector(-4, -1), new Vector(18, 0));
        world.register(new Block(zone1, "box.empty"));

        Box zone2 = new Box(new Vector(-2, 0), new Vector(-1, 1));
        world.register(new Block(zone2, "box.empty"));

        final Key key = new Key(new Vector(-2, 1), ColoredItem.BLUE);

        final Lever lever = new Lever(new Vector(4, 0));

        world.register(key);

        world.register(new Jumper(new Vector(0, 0)));

        world.register(new Door(new Vector(2, 0), ColoredItem.BLUE, lever));

        //world.register(new Fireball(new Vector(0, 1), Vector.ZERO));

        //world.register(new Jumper(new Vector(0, 0)));

        //world.register(new Spike(new Vector(-1, 0)));

        world.register(new Torch(new Vector(-1, 0)));

        world.register(lever);

        world.register(new Button(new Vector(7, 0), ColoredItem.BLUE));

        world.register(new Mover("box.empty", new Vector(10, 0), new Vector(10, 5), new Vector(2, 1), lever));

        world.register(new Exit(new Vector(10, 0), this));
    }

    @Override
    public Level getNextLevelOnDeath()
    {
        return new Level0();
    }

    @Override
    public Vector getSpawn()
    {
        return new Vector(2, 3);
    }
}
