import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.CircleBoundedEntity;
import blayzeTechUtils.math.NVector;
import java.util.*;
import java.awt.image.BufferedImage;

public class KillableSprite extends Sprite implements Actionable {

	public double yMovement = 0;
	public int health = 1;

	public KillableSprite(double x, double y, String path, int columns, int rows, float fps)
	{
		super(x, y, path, columns, rows, fps);
	}
	public KillableSprite(double x, double y, String path)
	{
		super(x, y, path);
	}
	public KillableSprite(double x, double y, BufferedImage img, int columns, int rows, float fps)
	{
		super(x, y, img, columns, rows, fps);
	}
	public KillableSprite(double x, double y, BufferedImage img)
	{
		super(x, y, img);
	}

	@Override
	public void action(GameEnvironment env)
	{
		health -= 10;
		if (health <= 0)
			this.markedForDeath = true;
	}

	@Override
	public void update(long time)
	{
		this.setY(this.getY() + yMovement);
		super.update(time);
	}
}
