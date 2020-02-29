import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.CircleBoundedEntity;
import blayzeTechUtils.math.NVector;
import java.util.*;

public class KillableSprite extends Sprite implements Actionable {
	public KillableSprite(String path, AbstractEntity physBox, int columns, int rows, float fps)
	{
		super(path, physBox, columns, rows, fps);
	}

	@Override
	public void action(Environment env)
	{
		this.markedForDeath = true;
	}
}
