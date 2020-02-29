import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.CircleBoundedEntity;
import blayzeTechUtils.math.NVector;
import java.util.*;

public class InteractableSprite extends Sprite implements Actionable {
	public InteractableSprite(String path, AbstractEntity physBox, int columns, int rows, float fps)
	{
		super(path, physBox, columns, rows, fps);
	}

	@Override
	public void action(Environment env)
	{
		System.out.println("I'm a tree");
	}
}
