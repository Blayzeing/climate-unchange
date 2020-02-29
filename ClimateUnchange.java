import blayzeTechUtils.graphics.SimpleDisplay;
import blayzeTechUtils.env.*;
import blayzeTechUtils.env.nonpolyshapes.*;
import java.awt.Graphics2D;
import java.awt.Color;


public class ClimateUnchange extends SimpleDisplay{

	private Player player;
	private Graphics2D g;
	private Environment currentEnvironment;

	public ClimateUnchange()
	{
		super(1280, 720, "Climate Unchange", true, true);
		g = this.getGraphics2D();

		player = new Player(400,400);

		Environment acidRain = new Environment();
		acidRain.entities.add(player);
		acidRain.entities.add(new Sprite("images/test.png", new CircleBoundedEntity(200,200, 100)));

		this.addKeyListener(new InputHandler(player));
		currentEnvironment = acidRain;
	}

	public void update()
	{
		player.update();
	}
	public void render()
	{
		g.setColor(Color.BLACK);
		this.fill(Color.WHITE);
		currentEnvironment.draw(g);
		//System.out.println(g);
		//System.out.println(currentEnvironment);
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
