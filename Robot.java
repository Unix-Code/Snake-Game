
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author David
 */
public class Robot extends Movable {
    private Direction direction;
    private ArrayList<Point> coords;
    private int coordsIndex;
    public Robot(int x, int y, int w, int h, Handler handler) {
        super(x, y, w, h, 5, ID.Robot, handler);
        coords = new ArrayList<Point>();
        coords.add(new Point(this.getX(), this.getY()));
        coordsIndex = 0;
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

        coords.add(new Point(this.x, this.y));
        coordsIndex++;

        // x = Game.clamp(x, 0, Game.width - w);
        // y = Game.clamp(y, 0, Game.height - h);

        if (Game.hitWall(x, 0, Game.width - w) || Game.hitWall(y, 0, Game.height - h) || this.hitItSelf()) {
            handler.removeObject(this);
            Game.gameOver = true;
        }
        
        if (this.cleaned()) {
            coordsIndex--;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);

        for (int i = coordsIndex; i < coords.size(); i++) {
            if (i == coords.size() -1) {
                g.fillRect(x, y, w, h);
            }
            else {
                g.fillRect((int)coords.get(coords.size() - 1 - 5*(i - coordsIndex + 1)).getX(), (int)coords.get(coords.size() - 1 - 5*(i - coordsIndex + 1)).getY(), w, h);
            }
        }
    }

    public boolean cleaned() {
        boolean scored = false;

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
                            scored = true;
                        }
                    }
                }  
            }
        }

        return scored;
    }
    
    public boolean hitItSelf() {
        boolean hit = false;
        for (int i = coordsIndex; i < coords.size(); i++) {
            if (coordsIndex < coords.size() - 3) {
                if (Math.abs(coords.get(coords.size() - 1).getX() - (int)coords.get(coords.size() - 1 - 5*(i - coordsIndex + 1)).getX()) <= 5 && Math.abs(coords.get(coords.size() - 1).getY() - (int)coords.get(coords.size() - 1 - 5*(i - coordsIndex + 1)).getY()) <= 5) {
                    hit = true;
                }
            }
        }
        return hit;
    }
}