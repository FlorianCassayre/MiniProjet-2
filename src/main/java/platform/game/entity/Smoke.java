package platform.game.entity;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

/**
 * An animated white smoke effect.
 */
public class Smoke extends Particle
{
    private final double duration;
    private final double offset;
    private double lifetime;

    public Smoke(Vector position)
    {
        super(position, new Vector(0.5, 0.5));

        duration = Math.random() * 0.75 + 0.5; // Randomize the duration
        lifetime = duration;

        offset = Math.random() * 2 * Math.PI; // Randomize the rotation offset
    }

    @Override
    public void update(Input input)
    {
        // No super here, the particle is static

        lifetime -= input.getDeltaTime();

        if(lifetime <= 0)
            kill();
    }

    @Override
    public void draw(Input input, Output output)
    {
        int step = 3 - ((int) Math.floor(3 * (lifetime / duration)));
        step = Math.min(Math.max(1, step), 3);
        output.drawSprite(getSprite("smoke.white." + step), getBox(), offset + lifetime / duration);
    }

    @Override
    public Box getBox()
    {
        final Box box = super.getBox();
        return new Box(box.getCenter(), box.getWidth() + box.getWidth() * (1 - lifetime / duration), box.getHeight() + box.getHeight() * (1 - lifetime / duration));
    }
}
