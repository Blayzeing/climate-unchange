import blayzeTechUtils.env.nonpolyshapes.CircleBoundedEntity;
import blayzeTechUtils.math.NVector;

public class Player extends Sprite {

	public static final double SPEED = 7;
	public double xVel, yVel;

	public Player(double x, double y)
	{
		super("images/test.png", new CircleBoundedEntity(x, y, 10));
		xVel = 0;
		yVel = 0;
	}

	public void update()
	{
		this.setX(this.getX() + xVel);
		this.setY(this.getY() + yVel);
	}
}
