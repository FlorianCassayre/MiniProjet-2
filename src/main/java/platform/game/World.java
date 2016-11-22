package platform.game;

import platform.util.Loader;
import platform.util.Vector;

/**
 * Represents an environment populated by actors.
 */
public interface World
{
    /**
     * @return associated loader, not null
     */
    Loader getLoader();

    /**
     * Set viewport location and size.
     *
     * @param center viewport center, not null
     * @param radius viewport radius, positive
     */
    void setView(Vector center, double radius);

    void register(Actor actor);

    void unregister(Actor actor);

    Vector getGravity();
}
