
import java.awt.Rectangle;

/**
 *
 * @author David
 */
public class Robot extends Movable {
    private Direction direction;
    
    public Robot(int x, int y, int w, int h, Handler handler) {
        super(x, y, w, h, 5, ID.Robot, handler);
        direction = Direction.Nothing;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void tick() {
        x += (direction.horizontal) ? direction.move * vel : 0;
        y += (direction.vertical) ? direction.move * vel : 0;
        
        // x = Game.clamp(x, 0, Game.width - w);
        // y = Game.clamp(y, 0, Game.height - h);
        
        if (Game.hitWall(x, 0, Game.width - w) || Game.hitWall(y, 0, Game.height - h)) {
            handler.removeObject(this);
            Game.gameOver = true;
        }
        
        this.cleaned();
    }

    public void cleaned() {
        for (int i = 0; i < handler.objects.size(); i++) {
            Stationary tempItem = null;
            if (handler.objects.get(i).getId() == ID.Item) {
                tempItem = (Stationary) handler.objects.get(i);
                if (this.getBounds().intersects(tempItem.getBounds())) {
                    handler.removeObject(tempItem);
                    handler.addObject(new Stationary((int)(Math.random()* (Game.width - 33)), (int)(Math.random() * (Game.height- 33)), 16, 16, handler));
                    for (int j = 0; j < handler.objects.size(); j++) {
                        Obj tempObj = handler.objects.get(j);
                        if (tempObj.getId() == ID.HUD) {
                            HUD tempHUD = (HUD) tempObj;
                            tempHUD.setScore(tempHUD.getScore() + 5);
                        }
                    }
                }  
            }
        }
    }
    
}