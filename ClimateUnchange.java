import blayzeTechUtils.graphics.SimpleDisplay;
import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.*;
import java.awt.Graphics2D;
import java.awt.Color;
import blayzeTechUtils.math.*;


public class ClimateUnchange extends SimpleDisplay{

	private Player player;
	private Graphics2D g;

	private long lastTime = System.currentTimeMillis();
	private GameEnvironment[] envs;
	private int currentEnvironment = 0;
	private Sprite timeZap = new Sprite("images/time-zap.png", new NullEntity(), 2, 11, (float)27);

	private StaticPoint[] triangle = new StaticPoint[]{new StaticPoint(0,0), new StaticPoint(50,100), new StaticPoint(100,0)};


	private PolygonEntity worldPlane = new PolygonEntity(0,0);
	public ClimateUnchange()
	{
		super(1280, 720, "Climate Unchange", true, true);
		g = this.getGraphics2D();

		GameEnvironment acidRain = new GameEnvironment();
		GameEnvironment ozone = new GameEnvironment();

		player = new Player(400,400, acidRain);
		timeZap.center(false);
		timeZap.setRenderDims(1280,720);
		timeZap.playOnce = true;

		acidRain.entities.add(player);
		acidRain.entities.add(new KillableSprite("images/test2.png",
					new TPPolygonEntity(400,400,triangle, worldPane), 2, 1, 7));


		ozone.entities.add(player);
		acidRain.entities.add(timeZap);
		ozone.entities.add(timeZap);

		this.addKeyListener(new InputHandler(player));
		envs = new GameEnvironment[]{acidRain, ozone};
		currentEnvironment = 0;

		player.env = acidRain;
	}

	public void update()
	{
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastTime > 10000)
		{
			currentEnvironment = (currentEnvironment+1)%envs.length;
			player.env = envs[currentEnvironment];
			lastTime = currentTime;
			timeZap.frame = 0;
			timeZap.play = true;
		}
		envs[currentEnvironment].updateElements();
	}
	public void render()
	{
		g.setColor(Color.BLACK);
		this.fill(Color.WHITE);
		envs[currentEnvironment].draw(g);
		this.repaint();
	}

	public static void main(String[] args) 
	{
		ClimateUnchange cu = new ClimateUnchange();
		try{
			while(true)
			{
				cu.update();
				cu.render();
				Thread.sleep(10);
			}
		}catch(InterruptedException e){
			System.out.println("ERROR:");
			System.out.println(e);
		}
	}
}
