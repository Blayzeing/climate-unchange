import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.CircleBoundedEntity;
import blayzeTechUtils.math.NVector;
import java.util.*;

public class KillableSprite extends Sprite implements Actionable {
	public KillableSprite(double x, double y, String path, int columns, int rows, float fps)
	{
		super(x, y, path, columns, rows, fps);
	}

	@Override
	public void action(GameEnvironment env)
	{
		this.markedForDeath = true;
	}
}
