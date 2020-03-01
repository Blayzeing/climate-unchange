import blayzeTechUtils.env.*;
import java.util.*;
import java.awt.Color;

public class GameEnvironment extends Environment {

	public PolygonEntity worldPlane = new PolygonEntity(0,0);
	public Color bgColor = Color.WHITE;

	private int levelLength;
	public double levelDuration;
	public HealthBar healthBar;

	public GameEnvironment(int levelLength, double levelDuration)
	{
		super();
		this.levelLength = levelLength;
		this.levelDuration = levelDuration;
	}
	public GameEnvironment(int levelLength)
	{
		this(levelLength, 30000);
	}

	public void addWorldPlaneSprite(Sprite s)
	{
		this.entities.add(0, s);
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
				if(s instanceof KillableSprite &&  s.getGlobalPoint(0).getY() > 720)
				{
					if(!((KillableSprite)s).isBoss)
						this.healthBar.changeHealth(0.01);
					else
						this.healthBar.changeHealth(0.15);
					killable.add(e);
				}
				s.update(System.currentTimeMillis());
			}
		}
		for(AbstractEntity e : killable)
		{
			KillableSprite s = (KillableSprite)e;
			if(s.markedForDeath)
				if(!s.isBoss)
					this.healthBar.changeHealth(-0.02);
				else
					this.healthBar.changeHealth(-0.15);
			this.entities.remove(e);
		}
		if (worldPlane.getY() < levelLength)
			worldPlane.setY(worldPlane.getY()+3);
	}
}
