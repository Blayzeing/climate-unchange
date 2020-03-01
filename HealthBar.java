import blayzeTechUtils.env.*;
import blayzeTechUtils.math.Point;
import java.util.*;

import java.awt.Graphics2D;
import java.awt.Color;
public class HealthBar extends TPPolygonEntity {
	private static final Color THERMOMETER_RED = new Color(140,0,0);
	private static final double maxLen = -550;
	public double health = 0.5;

	public HealthBar(PolygonEntity parent)
	{
		super(59,652);
		this.setParent(parent);
		PolygonEntities.addRectangleTo(this, 10, maxLen*health, false);
	}

	public void changeHealth(double n)
	{
		health = Math.max((health + n), 0);
		this.clearPoints();
		PolygonEntities.addRectangleTo(this, 10, maxLen*health, false);
	}

	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(THERMOMETER_RED);
		//super.draw(g);

		Point last = getGlobalPoint(vertices.size()-1);
		ArrayList<Point> points = getAllGlobalPoints();
		int[] xs = new int[4];
		int[] ys = new int[4];
		for(int i = 0; i<4; i++)
		{
			xs[i] = (int)points.get(i).getX();
			ys[i] = (int)points.get(i).getY();
		}
		g.fillPolygon(xs, ys, 4);
	}
}
