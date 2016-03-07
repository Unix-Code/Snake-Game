
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author David
 */
public class KeyCheck extends KeyAdapter {
    private Handler handler;

    private int xp, xm, yp, ym; //xm stands for x minus and xp stands for x plus etc.

    public KeyCheck(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            // Obj tempObject = handler.objects.get(i);

            if (handler.objects.get(i).getId() == ID.Robot) {
                //key events for player
                Robot tempRobot = (Robot) handler.objects.get(i);
                switch(key) {
                    case KeyEvent.VK_W :
                    if (tempRobot.getDirection() != Direction.Down) tempRobot.setDirection(Direction.Up);
                    break;
                    
                    case KeyEvent.VK_A : 
                    if (tempRobot.getDirection() != Direction.Right)tempRobot.setDirection(Direction.Left);
                    break;
                    
                    case KeyEvent.VK_S :
                    if (tempRobot.getDirection() != Direction.Up)tempRobot.setDirection(Direction.Down);
                    break;
                    
                    case KeyEvent.VK_D :
                    if (tempRobot.getDirection() != Direction.Left)tempRobot.setDirection(Direction.Right);
                    break;
                    
                    case KeyEvent.VK_UP :
                    tempRobot.setDirection(Direction.Up);
                    break;
                    
                    case KeyEvent.VK_LEFT :
                    tempRobot.setDirection(Direction.Left);
                    break;
                    
                    case KeyEvent.VK_DOWN :
                    tempRobot.setDirection(Direction.Down);
                    break;
                    
                    case KeyEvent.VK_RIGHT :
                    tempRobot.setDirection(Direction.Right);
                    break;
                }
            }
        }

    }
    /*
    public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();

    for (int i = 0; i < handler.objects.size(); i++) {
    Obj tempObject = handler.objects.get(i);

    if (tempObject.getId() == ID.Robot) {
    //key events for player
    Robot tempRobot = (Robot) tempObject;
    switch(key) {
    case KeyEvent.VK_W :
    // tempMovable.setVelY(-5);
    ym = 0;
    break;
    case KeyEvent.VK_A :
    // tempMovable.setVelX(-5);
    xm = 0;
    break;
    case KeyEvent.VK_S :
    // tempMovable.setVelY(5);
    yp = 0;
    break;
    case KeyEvent.VK_D :
    // tempMovable.setVelX(5);
    xp = 0;
    break;
    case KeyEvent.VK_UP :
    // tempMovable.setVelX(5);
    ym = 0;
    break;
    case KeyEvent.VK_LEFT :
    // tempMovable.setVelX(5);
    xm = 0;
    break;
    case KeyEvent.VK_DOWN :
    // tempMovable.setVelX(5);
    yp = 0;
    break;
    case KeyEvent.VK_RIGHT :
    // tempMovable.setVelX(5);
    xp = 0;
    break;
    }

    tempRobot.setVel(xp - xm);
    tempRobot.setVel(yp - ym);
    }
    }
    } */
}
