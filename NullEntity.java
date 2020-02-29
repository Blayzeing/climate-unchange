import blayzeTechUtils.env.*;
import java.awt.Graphics2D;

public class NullEntity extends AbstractEntity {
	public NullEntity()
	{
		this(0,0);
	}
	public NullEntity(double x, double y)
	{
		super(x,y);
	}
	@Override
	public void draw(Graphics2D g){};
	@Override
	public DistancedHit hitScan(double x1, double y1, double x2, double y2)
	{
		return new DistancedHit(false, 0, 0, 0);
	}
}
