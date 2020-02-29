import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.CircleBoundedEntity;
import blayzeTechUtils.math.NVector;
import blayzeTechUtils.math.Point;
import blayzeTechUtils.math.StaticPoint;
import java.util.*;

public class Player extends Sprite {

	public static final Point ORIGIN = new Point(0,0);
	public static final double SPEED = 7;
	public static final StaticPoint SPAWN_POINT = new StaticPoint(640,400);
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
		//Point newPos = new Point(this.getX() + xVel, this.getY() + yVel);
		this.setX(this.getX() + xVel);
		this.setY(this.getY() + yVel);

		Point topLeft = this.getGlobalPoint(0);
		Point bottomRight = this.getGlobalPoint(2);
		double dx = Math.min(topLeft.getX(), 0) + Math.max(0, bottomRight.getX() - 1280);
		double dy = Math.min(topLeft.getY(), 0) + Math.max(0, bottomRight.getY() - 720);
		this.setX(this.getX() - dx);
		this.setY(this.getY() - dy);
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
