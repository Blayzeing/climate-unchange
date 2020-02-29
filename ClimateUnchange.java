import blayzeTechUtils.graphics.SimpleDisplay;
import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.*;
import java.awt.Graphics2D;
import java.awt.Color;
import blayzeTechUtils.math.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;


public class ClimateUnchange extends SimpleDisplay{

	private Player player;
	private Graphics2D g;

	private long lastTime = System.currentTimeMillis();
	private GameEnvironment[] envs;
	private int currentEnvironment = 0;
	private Sprite timeZap = new Sprite(0,0,"images/time-zap.png", 2, 11, (float)27);

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private int levelLength = 900;

	//private PolygonEntity worldPlane = new PolygonEntity(0,0);
	public ClimateUnchange()
	{
		// Setup
		super(WIDTH, HEIGHT, "Climate Unchange", true, true);
		g = this.getGraphics2D();
		timeZap.center(false);
		timeZap.setRenderDims(WIDTH,HEIGHT);
		timeZap.playOnce = true;

		
		// acidRain
		GameEnvironment acidRain = new GameEnvironment(levelLength);
		acidRain.bgColor = Color.GREEN;
//		acidRain.addWorldPlaneSprite(new KillableSprite(200,200,"images/test2.png", 2, 1, 2));

		// load acidRain sprites
		BufferedImage[] rain = new BufferedImage[3];
		for (int i = 1; i < rain.length; i++) rain[i-1] = Sprite.loadImage("assets/acidrain" + i + ".png");
		BufferedImage[] tree = new BufferedImage[2];
		for (int i = 1; i < tree.length; i++) tree[i-1] = Sprite.loadImage("assets/tree" + i + ".png");
		BufferedImage[] grass = new BufferedImage[5];
		for (int i = 1; i < grass.length; i++) grass[i-1] = Sprite.loadImage("assets/grass" + i + ".png");
		
		// add in trees
		for (int i = 200; i < levelLength+HEIGHT/3; i += 50)
		{
			acidRain.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-tree[0].getWidth()), -i+HEIGHT, tree[0]));
		}

		player = new Player(400,400, acidRain);
		acidRain.addWorldPlaneSprite(player);
		
		acidRain.entities.add(timeZap);


		// Ozone
		GameEnvironment ozone = new GameEnvironment(levelLength);
		ozone.bgColor = new Color(180,100,100);
		ozone.addWorldPlaneSprite(player);
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
		this.fill(envs[currentEnvironment].bgColor);
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

	private static int randInt(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}
