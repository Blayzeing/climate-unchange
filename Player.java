import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.CircleBoundedEntity;
import blayzeTechUtils.math.NVector;
import blayzeTechUtils.math.StaticPoint;
import java.util.*;

public class Player extends Sprite {

	public static final double SPEED = 7;
	public double xVel, yVel;
	
	public GameEnvironment env;

	public Player(double x, double y, GameEnvironment env, PolygonEntity worldPlane)
	{
		super("images/test.png", new TPPolygonEntity(400,400, worldPlane));
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
		ArrayList<AbstractEntity> collidedObjects = env.getEntitiesAt(this,this);
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
