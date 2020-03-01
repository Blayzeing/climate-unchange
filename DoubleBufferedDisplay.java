import java.awt.*;
import java.awt.image.BufferedImage;
import blayzeTechUtils.graphics.SimpleDisplay;

public class DoubleBufferedDisplay extends SimpleDisplay {

	private BufferedImage doubleBuffer;
	private Graphics mainBufferG;

	public DoubleBufferedDisplay(int w, int h, String title, boolean closeFlag, boolean resizeFlag)
	{
		super(w,h, title, closeFlag, resizeFlag);
		doubleBuffer = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		mainBufferG = canvas.getImageReference().getGraphics();
	}

	@Override
	public BufferedImage getBufferedImageReference()
	{
		return doubleBuffer;
	}
	@Override
	public Graphics2D getGraphics2D()
	{
		return (Graphics2D)getBufferedImageReference().createGraphics();
	}
	public void copyBuffer()
	{
		mainBufferG.drawImage(doubleBuffer, 0, 0, null);
	}
}
