package platform.game.util;

public enum KeyDoorColor
{
    BLUE("blue"),
    GREEN("green"),
    RED("red"),
    YELLOW("yellow");

    private final String sprite;

    KeyDoorColor(String sprite)
    {
        this.sprite = sprite;
    }

    public String getKeySprite()
    {
        return "key." + sprite;
    }

    public String getDoorSprite()
    {
        return "lock." + sprite;
    }
}
