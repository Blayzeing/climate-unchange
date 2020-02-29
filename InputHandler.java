import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener
{
    private Player player;

    public InputHandler(Player p)
    {
        player = p;
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {}

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
            player.xVel = Math.max(0, player.xVel);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
            player.xVel = Math.min(0, player.xVel);
	else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
	    player.yVel = Math.max(0, player.yVel);
	else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
	    player.yVel = Math.min(0, player.yVel);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
            player.xVel = -Player.SPEED;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
	    player.xVel = Player.SPEED;
        else if (e.getKeyCode() == KeyEvent.VK_SPACE)
	    player.action();
        else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
            player.yVel = -Player.SPEED;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
            player.yVel = Player.SPEED;
        else if (e.getKeyCode() == KeyEvent.VK_Q)
            System.exit(0);
    }
}

