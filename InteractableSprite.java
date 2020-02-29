import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.CircleBoundedEntity;
import blayzeTechUtils.math.NVector;
import java.util.*;

public class InteractableSprite extends Sprite implements Actionable {
	public InteractableSprite(double x, double y, String path, int columns, int rows, float fps)
	{
		super(x, y, path, columns, rows, fps);
	}

	@Override
	public void action(GameEnvironment env)
	{
		System.out.println("I'm a tree");
	}
}
