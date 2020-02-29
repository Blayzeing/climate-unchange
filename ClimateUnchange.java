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
	private Sprite timeZap = new Sprite(0,0,"images/time-zap.png", 2, 11, (float)27);


	//private PolygonEntity worldPlane = new PolygonEntity(0,0);
	public ClimateUnchange()
	{
		super(1280, 720, "Climate Unchange", true, true);
		g = this.getGraphics2D();

		// acidRain
		GameEnvironment acidRain = new GameEnvironment();
		acidRain.addWorldPlaneSprite(new KillableSprite(200,200,"images/test2.png", 2, 1, 2));
		GameEnvironment ozone = new GameEnvironment();

		player = new Player(400,400, acidRain);
		acidRain.addWorldPlaneSprite(player);
		
		timeZap.center(false);
		timeZap.setRenderDims(1280,720);
		timeZap.playOnce = true;
		acidRain.entities.add(timeZap);


		// Ozone
		ozone.entities.add(player);
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
