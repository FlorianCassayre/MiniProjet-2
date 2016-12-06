package platform.game.util;

/**
 * Represents an interpolation method
 */
public enum InterpolationType
{
    /**
     * Linear interpolation
     */
    LINEAR
            {
                @Override
                public double interpolate(double value)
                {
                    return value;
                }
            },

    /**
     * Smooth interpolation using the third degree polynomial x^2(-2x+3) (-2x^3+3x^2)
     */
    CUBIC
            {
                @Override
                public double interpolate(double value)
                {
                    return value * value * (-2 * value + 3);
                }
            };

    /**
     * Calculates the specified interpolation
     * @param value a value between 0 and 1
     * @return a value between 0 and 1
     */
    public abstract double interpolate(double value);
}
