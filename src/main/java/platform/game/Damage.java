package platform.game;

public enum Damage
{
    FIRE,
    PHYSICAL,
    AIR,
    VOID,

    ACTIVATION(false),
    HEAL(false);

    final boolean damageable;

    Damage(boolean damageable)
    {
        this.damageable = damageable;
    }

    Damage()
    {
        this(true);
    }
}
