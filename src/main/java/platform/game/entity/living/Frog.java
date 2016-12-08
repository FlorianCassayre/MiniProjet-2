package platform.game.entity.living;

import platform.game.Actor;
import platform.game.util.Priority;
import platform.util.Input;
import platform.util.Output;
import platform.util.Vector;

public class Frog extends LivingEntity
{
    private static final int MAX_HEALTH = 4;

    private double jumpCooldown;
    private boolean direction;
    private boolean colliding;
    private double directionCooldown;

    public Frog(Vector position)
    {
        super(position, new Vector(1, 1), MAX_HEALTH);

        direction = Math.random() < 0.5;
        setJumpCooldown();
    }

    @Override
    public void update(Input input)
    {
        if(colliding)
        {
            double scale = Math.pow(0.001, input.getDeltaTime());
            setVelocity(getVelocity().mul(scale));
        }

        if(colliding)
        {
            jumpCooldown -= input.getDeltaTime();
            directionCooldown -= input.getDeltaTime();

            if(directionCooldown <= 0) // Time to tilt
            {
                direction = Math.random() >= 0.5;
                setDirectionCooldown();
            }

            if(jumpCooldown <= 0) // Time to jump
            {
                setJumpCooldown();
                setVelocity(new Vector((direction ? 1 : -1) * (Math.random() * 0.5 + 1), Math.random() * 3 + 5));
            }
        }

        super.update(input);
    }

    @Override
    public void draw(Input input, Output output)
    {
        String direction = this.direction ? "right" : "left";
        String sprite = "frog." + direction + (colliding ? "" : ".leap");
        output.drawSprite(getSprite(sprite), getBox());
    }

    private void setJumpCooldown()
    {
        jumpCooldown = Math.random() * 3 + 2;
    }

    private void setDirectionCooldown()
    {
        directionCooldown = Math.random() * 5 + 1;
    }

    @Override
    public int getPriority()
    {
        return Priority.FIREBALL;
    }

    @Override
    public void interact(Actor other)
    {
        if(other.isSolid())
        {
            Vector delta = other.getBox().getCollision(getBox());
            if(delta != null)
            {
                setPosition(getPosition().add(delta));
                if(delta.getX() != 0.0)
                {
                    setVelocity(new Vector(0.0, getVelocity().getY()));
                }
                if(delta.getY() != 0.0)
                {
                    setVelocity(new Vector(getVelocity().getX(), 0.0));
                    colliding = true;
                }
            }
        }
    }

    @Override
    public void preUpdate()
    {
        colliding = false;
    }
}
