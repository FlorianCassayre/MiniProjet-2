package platform.game.level;

import platform.game.World;
import platform.game.entities.Block;
import platform.game.entities.Fireball;
import platform.game.entities.Player;
import platform.util.Box;
import platform.util.Sprite;
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

        Sprite sprite = getSprite("box.empty");
        Box zone1 = new Box(new Vector(-4, -1), new Vector(4, 0));
        world.register(new Block(zone1, sprite));

        Box zone2 = new Box(new Vector(-2, 0), new Vector(-1, 1));
        world.register(new Block(zone2, sprite));

        world.register(new Fireball(new Vector(0, 1), Vector.ZERO));

        world.register(new Player(new Vector(2, 3), new Vector(0, -1)));

    }

}
