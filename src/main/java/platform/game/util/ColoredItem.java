package platform.game.util;

/**
 * Represents a colored item (key, button, door...)
 */
public enum ColoredItem
{
    BLUE("blue"),
    GREEN("green"),
    RED("red"),
    YELLOW("yellow");

    private final String sprite;

    ColoredItem(String sprite)
    {
        assert sprite != null;

        this.sprite = sprite;
    }

    /**
     * Returns the key sprite associated to this color.
     * @return the sprite name
     */
    public String getKeySprite()
    {
        return "key." + sprite;
    }

    /**
     * Returns the door sprite associated to this color.
     * @return the sprite name
     */
    public String getDoorSprite()
    {
        return "lock." + sprite;
    }

    /**
     * Returns the button sprite associated to this color.
     * @return the sprite name
     */
    public String getButtonSprite(boolean isPressed)
    {
        return "button." + sprite + "." + (isPressed ? "down" : "up");
    }
}
