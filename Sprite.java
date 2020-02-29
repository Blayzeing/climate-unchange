import blayzeTechUtils.env.*;
import blayzeTechUtils.math.*;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite extends AbstractEntity {
	
	protected AbstractEntity physicsBox;
	private BufferedImage img;

	private int xOffset = 0, yOffset = 0;
	private int columns = 1, rows = 1;
	private int frameWidth, frameHeight;
	private int renderWidth, renderHeight;
	private float fps = 24;

	private long lastTime = System.currentTimeMillis();
	private int interval = 42;
	private int totalFrames = 1; // Storing this for speed reasons.

	public int frame = 0;
	public boolean play = true;
	public boolean playOnce = false;

	public boolean markedForDeath = false;

	public Sprite(String path, AbstractEntity physBox, int columns, int rows, float fps)
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

		this.columns = columns;
		this.rows = rows;
		this.fps = fps;
		this.frameWidth = img.getWidth()/columns;
		this.frameHeight = img.getHeight()/rows;
		this.interval = (int)(1000/fps);
		this.totalFrames = columns*rows;
		this.renderWidth = frameWidth;
		this.renderHeight = frameHeight;
		center(true);
	}
	public Sprite(String path, AbstractEntity physBox)
	{
		this(path, physBox, 1, 1, 24);
	}
	public void center()
	{
		center(true);
	}
	public void setRenderDims(int w, int h)
	{
		this.renderWidth = w;
		this.renderHeight = h;
	}
	public void center(boolean c)
	{
		if(c)
		{
			xOffset = renderWidth/2;
			yOffset = renderHeight/2;
		}else{
			xOffset = 0;
			yOffset = 0;
		}
	}

	
	@Override
	public void draw(Graphics2D g)
	{
		if(rows == 1 && columns == 1)
			g.drawImage(img, (int)physicsBox.getX() - xOffset, (int)physicsBox.getY() - yOffset, null);
		else{
			int x = frame%columns;
			int y = frame/columns;
			g.drawImage(img, (int)physicsBox.getX() - xOffset, (int)physicsBox.getY() - yOffset, 
					 (int)physicsBox.getX() + renderWidth - xOffset, (int)physicsBox.getY() + renderHeight - yOffset,
				         x*frameWidth, y*frameHeight, (x+1)*frameWidth, (y+1)*frameHeight, null);
		}
		physicsBox.draw(g);
	}
	@Override
	public DistancedHit hitScan(double x1, double y1, double x2, double y2)
	{
		return physicsBox.hitScan(x1, y1, x2, y2);
	}
	@Override
	public boolean contains(double x, double y)
	{
		return physicsBox.contains(x,y);
	}
	@Override
	public boolean contains(StaticPoint p)
	{
		return physicsBox.contains(p);
	}
	@Override
	public boolean contains(StaticPoint[] ps)
	{
		return physicsBox.contains(ps);
	}
	@Override
	public boolean contains(ArrayList<StaticPoint> ps)
	{
		return physicsBox.contains(ps);
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

	public void update(long time)
	{
		if(play)
		{
			if(time - lastTime > interval)
			{
				frame = (frame+1)%totalFrames;
				if(playOnce && frame == totalFrames-1)
					play = false;
				lastTime = time;
			}
		}else{
			lastTime = time;
		}
	}
}
