package platform.game.util;

public enum ColoredItem
{
    BLUE("blue"),
    GREEN("green"),
    RED("red"),
    YELLOW("yellow");

    private final String sprite;

    ColoredItem(String sprite)
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

    public String getButtonSprite(boolean isPressed)
    {
        return "button." + sprite + "." + (isPressed ? "down" : "up");
    }
}
