import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.CircleBoundedEntity;
import blayzeTechUtils.math.NVector;
import blayzeTechUtils.math.Point;
import blayzeTechUtils.math.StaticPoint;
import java.util.*;

public class Player extends Sprite {

	public static final Point ORIGIN = new Point(0,0);
	public static final double SPEED = 7;
	public double xVel, yVel;
	
	public GameEnvironment env;

	public Player(double x, double y, GameEnvironment env)
	{
		super(x,y, "images/test.png");
		xVel = 0;
		yVel = 0;
		this.env = env;
	}

	@Override
	public void update(long time)
	{
		super.update(time);
		this.setX(this.getX() + xVel);
		this.setY(this.getY() + yVel);
	}

	public void action()
	{
		System.out.println("ACTION:");
		ArrayList<AbstractEntity> collidedObjects = env.getEntitiesAt(this.projectToWorld(ORIGIN),this);
		System.out.println(collidedObjects);
		if(collidedObjects == null)
			return;
		for(AbstractEntity obj : collidedObjects)
		{
			if(obj instanceof Actionable)
			{
				((Actionable)obj).action(env);
			}
		}
	}
}
