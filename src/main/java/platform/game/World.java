package platform.game;

import platform.game.level.Level;
import platform.game.level.PlayableLevel;
import platform.game.util.Damage;
import platform.util.Box;
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
     * @param center viewport center, not null
     * @param radius viewport radius, positive
     */
    void setView(Vector center, double radius);

    /**
     * Registers an actor this world.
     * @param actor the actor to be registered
     */
    void register(Actor actor);

    /**
     * Unregisters an actor from this world.
     * @param actor the actor to be unregistered
     */
    void unregister(Actor actor);

    /**
     * Returns the gravity of this world.
     * @return a gravity vector
     */
    Vector getGravity();

    /**
     * Switches to the next level.
     */
    void nextLevel();

    /**
     * Sets the next level to be played.
     * @param level the next level
     */
    void setNextLevel(Level level);

    /**
     * Used to hurt all actors in the specified area.
     * @param area the area to hurt the actors
     * @param instigator the instigator of this attack
     * @param type the damage type
     * @param amount the attack power
     * @param location the attack center
     * @return the number of actors hurt
     */
    int hurt(Box area, Actor instigator, Damage type, double amount, Vector location);

    /**
     * Adds a level to the cleared levels.
     * @param level the level to add
     */
    void addDoneLevel(PlayableLevel level);

    /**
     * Checks if the specified level has been cleared.
     * @param level the level to check
     * @return true if the was cleared, false else
     */
    boolean isDone(PlayableLevel level);
}
