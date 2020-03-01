import blayzeTechUtils.graphics.SimpleDisplay;
import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.*;
import java.awt.Graphics2D;
import java.awt.Color;
import blayzeTechUtils.math.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;


public class ClimateUnchange extends SimpleDisplay {

	private Player player;
	private Graphics2D g;

	private long lastTime = System.currentTimeMillis();
	private GameEnvironment[] envs;
	private int currentEnvironment = 0;
	private Sprite timeZap = new Sprite(0,0,"images/time-zap.png", 2, 11, (float)27);
	private Sprite healthBar = new Sprite(0,0,"assets/thermometer1.png");

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
		healthBar.center(false);
		healthBar.setX(1190);

		player = new Player(400,400, null);

		// title level
		GameEnvironment titleLevel = new GameEnvironment(0, 8000);
//		titleLevel.bgColor = new Color(0, 0, 0);
		titleLevel.addWorldPlaneSprite(new Sprite(WIDTH/2, HEIGHT/2, "assets/titlescreen.png", 1, 2, 0.18f));


		
		// acidRain
		GameEnvironment acidRain = new GameEnvironment(levelLength);
		acidRain.bgColor = new Color(0, 200, 0);
		acidRain.addWorldPlaneSprite(new KillableSprite(WIDTH/2,0,"assets/acidrainflavour1.png"));
		acidRain.addWorldPlaneSprite(player);

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
		acidRain.entities.add(healthBar);


		// Ozone
		GameEnvironment ozone = new GameEnvironment(levelLength);
		ozone.bgColor = new Color(180,40,40);
		ozone.addWorldPlaneSprite(new KillableSprite(WIDTH/2,0,"assets/ozonerainflavour1.png"));
		ozone.addWorldPlaneSprite(player);
		ozone.entities.add(timeZap);
		ozone.entities.add(healthBar);

		// load Ozone sprites
		BufferedImage[] cfc = new BufferedImage[3];
		for (int i = 0; i < cfc.length; i++) cfc[i] = Sprite.loadImage("assets/cfc" + (i+1) + ".png");
		BufferedImage refridgerator = Sprite.loadImage("assets/refridgerator1.png");
		BufferedImage ozonesprite = Sprite.loadImage("assets/ozone1.png");
		BufferedImage tile = Sprite.loadImage("assets/tile1.png");
		BufferedImage[] utensil = new BufferedImage[2];
		for (int i = 0; i < utensil.length; i++) utensil[i] = Sprite.loadImage("assets/utensil" + (i+1) + ".png");

		// add in cfcs
		for (int i = 500; i < levelLength*2; i += 100)
		{
			KillableSprite drop = new KillableSprite(randInt(0, WIDTH-cfc[0].getWidth()), -i+HEIGHT, cfc[randInt(0,cfc.length)]);
			drop.yMovement = 2;
			ozone.addWorldPlaneSprite(drop);
		}
		// add in BOSS
		KillableSprite ozoneBoss = new KillableSprite(WIDTH/2, -levelLength+HEIGHT/2, "assets/currys1.png");
		ozoneBoss.health = 200;
		ozone.addWorldPlaneSprite(ozoneBoss);
		// add in refridgerators
		for (int i = 200; i < levelLength+HEIGHT/3; i += 80)
		{
			ozone.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-refridgerator.getWidth()), -i+HEIGHT, refridgerator));
		}
		// add in ozone
		for (int i = 200; i < levelLength+HEIGHT/3; i += 300)
		{
			ozone.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-ozonesprite.getWidth()), -i+HEIGHT, ozonesprite));
		}
		// add in utensils
		for (int i = 0; i < levelLength+HEIGHT; i += 30)
		{
			ozone.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-utensil[0].getWidth()), -i+HEIGHT, utensil[randInt(0,utensil.length)]));
		}
		// add in rocks
		for (int i = 0; i < levelLength+HEIGHT/3; i += 400)
		{
			ozone.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-rock[0].getWidth()), -i+HEIGHT, rock[randInt(0,rock.length)]));
		}
		// add in tile
		for (int i = 0; i < levelLength+HEIGHT+42; i += 41)
		{
			ozone.addWorldPlaneSprite(new Sprite(WIDTH/2, -i+HEIGHT, tile));
		}



		// SeaLevel
		GameEnvironment seaLevel = new GameEnvironment(levelLength);
		seaLevel.bgColor = new Color(0,10,200);
		seaLevel.addWorldPlaneSprite(new KillableSprite(WIDTH/2,0,"assets/sealevelflavour1.png"));
		seaLevel.addWorldPlaneSprite(player);
		seaLevel.entities.add(timeZap);

		BufferedImage[] turtle = new BufferedImage[2];
		for (int i = 0; i < turtle.length; i++) turtle[i] = Sprite.loadImage("assets/turtle" + (i+1) + ".png");
		BufferedImage[] icecube = new BufferedImage[2];
		for (int i = 0; i < icecube.length; i++) icecube[i] = Sprite.loadImage("assets/icecube" + (i+1) + ".png");
		BufferedImage[] wave = new BufferedImage[2];
		for (int i = 0; i < wave.length; i++) wave[i] = Sprite.loadImage("assets/wave" + (i+1) + ".png");

		// add in waves
		for (int i = 500; i < levelLength*2.2; i += 300)
		{
			int dir = randInt(0,wave.length);
			int pos = 0;
			int xmov = 0;
			if (dir == 0)
			{
				pos = randInt(WIDTH-wave[0].getWidth()-300, WIDTH-wave[0].getWidth()) + 220 + (int)((double)(i)*0.1);
				xmov = -1;
			}
			else
			{
				pos = randInt(0, 300-wave[0].getWidth()) - 220 - (int)((double)(i)*0.1);
				xmov = 1;
			}
			KillableSprite awave = new KillableSprite(pos+200, -i+HEIGHT, wave[dir]);
			awave.yMovement = 2;
			awave.xMovement = xmov;
			seaLevel.addWorldPlaneSprite(awave);
		}
		// add in BOSS
		KillableSprite seaBoss = new KillableSprite(WIDTH/2, -levelLength+HEIGHT/2, "assets/oilymcoilface1.png");
		seaBoss.health = 200;
		seaLevel.addWorldPlaneSprite(seaBoss);
		// add in turtles
		for (int i = 200; i < levelLength+HEIGHT/3; i += 190)
		{
			seaLevel.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-turtle[0].getWidth()), -i+HEIGHT, turtle[randInt(0,turtle.length)]));
		}
		// add in icecubes
		for (int i = 0; i < levelLength+HEIGHT/3; i += 310)
		{
			if (randInt(0,2) == 0)
			{
				seaLevel.addWorldPlaneSprite(new Sprite(randInt(0, 300), -i+HEIGHT, icecube[randInt(0,icecube.length)]));
			}
			else
			{
				seaLevel.addWorldPlaneSprite(new Sprite(randInt(WIDTH-icecube[0].getWidth()-300, WIDTH-icecube[0].getWidth()), -i+HEIGHT, icecube[randInt(0,icecube.length)]));
			}
		}
		// add in rocks
		for (int i = 0; i < levelLength+HEIGHT/3; i += 410)
		{
			seaLevel.addWorldPlaneSprite(new Sprite(randInt(200, WIDTH-rock[0].getWidth())-100, -i+HEIGHT, rock[randInt(0,rock.length)]));
		}
		// the beach
		seaLevel.addWorldPlaneSprite(new Sprite(WIDTH/2, -1000, "assets/beach1.png"));




		// extreme weather
		GameEnvironment extremeW = new GameEnvironment(levelLength*2);
		extremeW.bgColor = new Color(0, 100, 0);
		extremeW.scrollingSpeed = 6;
		KillableSprite xfs = new KillableSprite(WIDTH/2,0,"assets/extremeflavour1.png");
		xfs.yMovement = -3;
		extremeW.addWorldPlaneSprite(xfs);
		extremeW.addWorldPlaneSprite(player);

		BufferedImage[] rain2 = new BufferedImage[3];
		for (int i = 0; i < rain2.length; i++) rain2[i] = Sprite.loadImage("assets/rain" + (i+1) + ".png");
		BufferedImage[] lightning = new BufferedImage[3];
		for (int i = 0; i < lightning.length; i++) lightning[i] = Sprite.loadImage("assets/lightning" + (i+1) + ".png");
		BufferedImage[] tornado = new BufferedImage[2];
		for (int i = 0; i < tornado.length; i++) tornado[i] = Sprite.loadImage("assets/tornado" + (i+1) + ".png");

		// add in rain
		for (int i = 500; i < levelLength*2; i += 120)
		{
			extremeW.addWorldPlaneSprite(new KillableSprite(randInt(0, WIDTH-rain2[0].getWidth()), -i+HEIGHT, rain2[randInt(0,rain2.length)]));
		}
		// add in lightning
		for (int i = 533; i < levelLength*2; i += 130)
		{
			extremeW.addWorldPlaneSprite(new KillableSprite(randInt(0, WIDTH-lightning[0].getWidth()), -i+HEIGHT, lightning[randInt(0,lightning.length)]));
		}
		// add in tornado
		for (int i = 566; i < levelLength*2; i += 170)
		{
			extremeW.addWorldPlaneSprite(new KillableSprite(randInt(0, WIDTH-tornado[0].getWidth()), -i+HEIGHT, tornado[randInt(0,tornado.length)]));
		}
		// add in BOSS
		KillableSprite extremeBoss = new KillableSprite(WIDTH/2, -levelLength*2+HEIGHT/2, "assets/june20071.png");
		extremeBoss.health = 1000;
		extremeW.addWorldPlaneSprite(extremeBoss);
		// add in trees
		for (int i = 200; i < levelLength*2+HEIGHT; i += 100)
		{
			extremeW.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-tree[0].getWidth()), -i+HEIGHT, tree[randInt(0,tree.length)]));
		}
		// add in grass
		for (int i = 0; i < levelLength*2+HEIGHT; i += 40)
		{
			extremeW.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-grass[0].getWidth()), -i+HEIGHT, grass[randInt(0,grass.length)]));
		}
		// add in rocks
		for (int i = 0; i < levelLength*2+HEIGHT; i += 320)
		{
			extremeW.addWorldPlaneSprite(new Sprite(randInt(0, WIDTH-rock[0].getWidth()), -i+HEIGHT, rock[randInt(0,rock.length)]));
		}





		this.addKeyListener(new InputHandler(player));
		envs = new GameEnvironment[]{extremeW, titleLevel, acidRain, ozone, seaLevel};
		currentEnvironment = 0;

		player.env = extremeW;
		player.setParent(player.env.worldPlane);
	}

	public void update()
	{
		long currentTime = System.currentTimeMillis();
		if(currentTime - lastTime > envs[currentEnvironment].levelDuration)
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
				//Thread.sleep(1000/15);
				Thread.sleep(15);
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
