import blayzeTechUtils.env.*;

public class GameEnvironment extends Environment {

	public double yPosition = 0.0;

	public void updateElements()
	{
		for(AbstractEntity e : this.entities)
		{
			if(e instanceof Sprite)
			{
				Sprite s = (Sprite)e;
				if(s.markedForDeath)
					this.entities.remove(e);
				s.update(System.currentTimeMillis());
			}
		}
	}
}
