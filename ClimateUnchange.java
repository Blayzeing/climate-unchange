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
	private int levelLength = 2500;

	//private PolygonEntity worldPlane = new PolygonEntity(0,0);
	public ClimateUnchange()
	{
		// Setup
		super(WIDTH, HEIGHT, "Climate Unchange", true, true);
		g = this.getGraphics2D();
		timeZap.center(false);
		timeZap.setRenderDims(WIDTH,HEIGHT);
		timeZap.playOnce = true;

		player = new Player(400,400, null);
		
		// acidRain
		GameEnvironment acidRain = new GameEnvironment(levelLength);
		acidRain.bgColor = new Color(0, 200, 0);
		acidRain.addWorldPlaneSprite(player);
//		acidRain.addWorldPlaneSprite(new KillableSprite(200,200,"images/test2.png", 2, 1, 2));

		// load acidRain sprites
		BufferedImage[] rain = new BufferedImage[3];
		for (int i = 0; i < rain.length; i++) rain[i] = Sprite.loadImage("assets/acidrain" + (i+1) + ".png");
		BufferedImage[] tree = new BufferedImage[2];
		for (int i = 0; i < tree.length; i++) tree[i] = Sprite.loadImage("assets/tree" + (i+1) + ".png");
		BufferedImage[] grass = new BufferedImage[5];
		for (int i = 0; i < grass.length; i++) grass[i] = Sprite.loadImage("assets/grass" + (i+1) + ".png");
		BufferedImage[] rock = new BufferedImage[2];
		for (int i = 0; i < rock.length; i++) rock[i] = Sprite.loadImage("assets/rock" + (i+1) + ".png");

		// add in acid rain
		for (int i = 500; i < levelLength*2.22; i += 100)
		{
			KillableSprite drop = new KillableSprite(randInt(0, WIDTH-rain[0].getWidth()), -i+HEIGHT, rain[randInt(0,rain.length)]);
			drop.yMovement = 2;
			acidRain.addWorldPlaneSprite(drop);
		}
		// add in BOSS
		KillableSprite acidBoss = new KillableSprite(WIDTH/2, -levelLength+HEIGHT/2, "assets/coalplant1.png");
		acidBoss.health = 200;
		acidRain.addWorldPlaneSprite(acidBoss);
		// add in trees
		for (int i = 200; i < levelLength+HEIGHT/3; i += 50)
		{
			acidRain.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-tree[0].getWidth()), -i+HEIGHT, tree[randInt(0,tree.length)]));
		}
		// add in grass
		for (int i = 0; i < levelLength+HEIGHT; i += 10)
		{
			acidRain.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-grass[0].getWidth()), -i+HEIGHT, grass[randInt(0,grass.length)]));
		}
		// add in rocks
		for (int i = 0; i < levelLength+HEIGHT/3; i += 200)
		{
			acidRain.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-rock[0].getWidth()), -i+HEIGHT, rock[randInt(0,rock.length)]));
		}

		
		acidRain.entities.add(timeZap);


		// Ozone
		GameEnvironment ozone = new GameEnvironment(levelLength);
		ozone.bgColor = new Color(180,100,100);
		ozone.entities.add(player);
		ozone.entities.add(timeZap);

		this.addKeyListener(new InputHandler(player));
		envs = new GameEnvironment[]{acidRain, ozone};
		currentEnvironment = 0;

		player.env = acidRain;
		player.setParent(player.env.worldPlane);
	}

	public void update()
	{
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastTime > 30000)
		{
			currentEnvironment = (currentEnvironment+1)%envs.length;
			player.env = envs[currentEnvironment];
			player.setParent(player.env.worldPlane);
			player.setY(Player.SPAWN_POINT.getY());
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
		return ThreadLocalRandom.current().nextInt(min, max);
	}
}
