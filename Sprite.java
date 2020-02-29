import blayzeTechUtils.env.*;
import blayzeTechUtils.math.*;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite extends TPPolygonEntity {
	
	private BufferedImage img;

	private int xOffset = 0, yOffset = 0;
	private int columns = 1, rows = 1;
	private int frameWidth, frameHeight;
	private int renderWidth, renderHeight;
	private float fps = 24;

	private long lastTime = System.currentTimeMillis();
	private int interval = 42;
	private int totalFrames = 1; // Storing this for speed reasons.
	private boolean centered = true;

	public int frame = 0;
	public boolean play = true;
	public boolean playOnce = false;

	public boolean markedForDeath = false;

	public Sprite(double x, double y, String path, int columns, int rows, float fps)
	{
		super(x,y);

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

		PolygonEntities.addRectangleTo(this, renderWidth, renderHeight, true);
	}
	public Sprite(double x, double y, String path)
	{
		this(x, y, path, 1, 1, (float)24);
	}
	public void center()
	{
		center(true);
	}
	public void setRenderDims(int w, int h)
	{
		this.renderWidth = w;
		this.renderHeight = h;
		center(centered);
	}
	public void center(boolean c)
	{
		this.clearPoints();
		PolygonEntities.addRectangleTo(this, renderWidth, renderHeight, c);
		centered = c;
	}

	
	@Override
	public void draw(Graphics2D g)
	{
		int x = frame%columns;
		int y = frame/columns;
		Point renderStart = this.getGlobalPoint(0);
		Point renderEnd = this.getGlobalPoint(2);
		g.drawImage(img, (int)renderStart.getX(), (int)renderStart.getY(),
				 (int)renderEnd.getX(), (int)renderEnd.getY(), 
				 x*frameWidth, y*frameHeight, (x+1)*frameWidth, (y+1)*frameHeight, null);

		super.draw(g);
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
