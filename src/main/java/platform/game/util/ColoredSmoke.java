package platform.game.util;

/**
 * Represents a colored smoke
 */

public enum ColoredSmoke
{
    WHITE("white"),
    GRAY("gray"),
    ORANGE("orange"),
    YELLOW("yellow");

    private final String sprite;

    ColoredSmoke(String sprite)
    {
        this.sprite = sprite;
    }

    /**
     * Returns the smoke sprite associated to this color.
     * @return the sprite name
     */
    public String getSmokeSprite()
    {
        return "smoke." + sprite + ".";
    }
}
