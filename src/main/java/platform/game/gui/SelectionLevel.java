package platform.game.gui;

import platform.game.World;
import platform.game.gui.LevelButton;
import platform.game.level.*;
import platform.util.Vector;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A basic level selector.
 */
public class SelectionLevel extends Level
{
    private static final int ELEMENTS_PER_LINE = 6;

    private static final double BLOCK_SIZE = 1;

    private static final double SPACING_HORIZONTAL = 0.5;
    private static final double SPACING_VERTICAL = 0.5;

    private final Map<Integer, PlayableLevel> levels = new LinkedHashMap<>();

    @Override
    public void register(World world)
    {
        super.register(world);

        // Register your levels from here
        
        registerLevel(1, new Level1());
        registerLevel(2, new Level2());
        registerLevel(3, new Level3());
        registerLevel(4, new Level4());
        registerLevel(5, new Level5());

        // Until here

        createButtons(world);
    }

    /**
     * Creates the buttons associated to the registered levels.
     * Must be called <b>once</b>, at the end of the level registration.
     * @param world the world
     */
    private void createButtons(World world)
    {
        final int lines = (int) Math.ceil((double) levels.size() / ELEMENTS_PER_LINE);
        List<Integer> keys = new ArrayList<>(levels.keySet());

        final double width = ELEMENTS_PER_LINE + SPACING_HORIZONTAL * (ELEMENTS_PER_LINE - 1);
        final double height = lines + SPACING_VERTICAL * (lines - 1);

        for(int line = 0; line < lines; line++)
        {
            final double posY = line * (BLOCK_SIZE + SPACING_VERTICAL) - height / 2.0 + BLOCK_SIZE / 2.0;
            for(int col = 0; col < ELEMENTS_PER_LINE; col++)
            {
                final int index = (lines - 1 - line) * ELEMENTS_PER_LINE + col;
                if(index < levels.size())
                {
                    final double posX = col * (BLOCK_SIZE + SPACING_HORIZONTAL) - width / 2.0 + BLOCK_SIZE / 2.0;

                    final int key = keys.get(index);
                    final PlayableLevel level = levels.get(key);
                    world.register(new LevelButton(new Vector(posX, posY), key, getWorld().isDone(level), level));
                }
            }
        }
    }

    /**
     * Registers a level.
     * The level will be displayed in the selection menu.
     * @param id the level id
     * @param level the level to be registered
     */
    private void registerLevel(int id, PlayableLevel level)
    {
        if(levels.containsKey(id))
            throw new IllegalArgumentException("Another level is using the same id (" + id + ")!");

        levels.put(id, level);
    }
}
