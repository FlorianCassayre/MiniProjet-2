package platform.game.level;

import platform.game.*;
import platform.game.signal.ConstantSignal;
import platform.util.Box;
import platform.util.Vector;

public class BasicLevel extends Level
{
    @Override
    public void register(World world)
    {
        super.register(world);

        // Register a new instance, to restart level automatically
        world.setNextLevel(new BasicLevel());

        // Create blocks
        //world.register(new Block(new Box(new Vector(0, 0), 4, 2), world.getLoader().getSprite("stone.broken.2")));
        ///world.register(new Block(new Box(new Vector(-1.5, 1.5), 1, 1), world.getLoader().getSprite("stone.broken.1")));

        Box zone1 = new Box(new Vector(-4, -1), new Vector(18, 0));
        world.register(new Block(zone1, "box.empty"));

        Box zone2 = new Box(new Vector(-2, 0), new Vector(-1, 1));
        world.register(new Block(zone2, "box.empty"));

        final Key key = new Key(new Vector(-2, 1), KeyDoorColor.BLUE);

        final Lever lever = new Lever(new Vector(4, 0));

        world.register(key);

        world.register(new Jumper(new Vector(0, 0)));

        world.register(new Door(new Box(new Vector(2, 0), new Vector(3, 1)), KeyDoorColor.BLUE, lever));

        //world.register(new Fireball(new Vector(0, 1), Vector.ZERO));

        final Player player = new Player(new Vector(2, 3), new Vector(0, -1));
        world.register(player);

        //world.register(new Jumper(new Vector(0, 0)));

        world.register(new Overlay(player));

        //world.register(new Spike(new Vector(-1, 0)));

        world.register(new Torch(new Vector(-1, 0)));

        world.register(lever);

        world.register(new Mover("box.empty", new Vector(10, 0), new Vector(10, 5), new Vector(2, 1), lever));

        world.register(new Exit(new Vector(10, 0), new BasicLevel()));

        world.register(new Limits(new Box(Vector.ZERO, 40, 30)));
    }

}
