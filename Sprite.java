import blayzeTechUtils.env.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite extends AbstractEntity {
	
	private AbstractEntity physicsBox;
	private BufferedImage img;

	private int xOffset = 0, yOffset = 0;

	public Sprite(String path, AbstractEntity physBox)
	{
		super(0,0);
		physicsBox = physBox;

		img = null;
		try
		{
		    img = ImageIO.read(new File(path));
		}
		catch (IOException e)
		{
		    System.out.println("Error Loading file \""+path+"\":");
		    System.out.println(e);
		}
		center(true);
	}
	public void center(boolean c)
	{
		if(c)
		{
			xOffset = img.getWidth()/2;
			yOffset = img.getHeight()/2;
		}else{
			xOffset = 0;
			yOffset = 0;
		}
	}

	
	@Override
	public void draw(Graphics2D g)
	{
		g.drawImage(img, (int)physicsBox.getX() - xOffset, (int)physicsBox.getY() - yOffset, null);
		physicsBox.draw(g);
	}
	@Override
	public DistancedHit hitScan(double x1, double y1, double x2, double y2)
	{
		return physicsBox.hitScan(x1, y1, x2, y2);
		//return new DistancedHit(false, 0,0,0);
	}

	@Override
	public void setX(double x)
	{
		physicsBox.setX(x);
	}
	@Override
	public void setY(double y)
	{
		physicsBox.setY(y);
	}
	@Override
	public double getX()
	{
		return physicsBox.getX();
	}
	@Override
	public double getY()
	{
		return physicsBox.getY();
	}
}
