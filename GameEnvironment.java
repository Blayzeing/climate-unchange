import blayzeTechUtils.env.*;
import java.util.*;
import java.awt.Color;

public class GameEnvironment extends Environment {

	public PolygonEntity worldPlane = new PolygonEntity(0,0);
	public Color bgColor = Color.WHITE;

	public void addWorldPlaneSprite(Sprite s)
	{
		this.entities.add(s);
		s.setParent(worldPlane);
	}
	public void updateElements()
	{
		ArrayList<AbstractEntity> killable = new ArrayList<AbstractEntity>();
		for(AbstractEntity e : this.entities)
		{
			if(e instanceof Sprite)
			{
				Sprite s = (Sprite)e;
				if(s.markedForDeath)
					killable.add(e);
				s.update(System.currentTimeMillis());
			}
		}
		for(AbstractEntity e : killable)
			this.entities.remove(e);
		worldPlane.setY(worldPlane.getY()+2);
	}
}
