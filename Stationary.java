
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author David
 */
public class Stationary extends Obj {
    Handler handler;

    public Stationary(int x, int y, int w, int h, Handler handler) {
        super(x, y, w, h);
        this.handler = handler;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }

    public void tick() {
        // this.detectCollision();
    }

    public void render(Graphics g) {
        // g.setColor(Color.GRAY);
        // g.fillRoundRect(x, y, w, h, 5, 5);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.draw(this.getBounds());
    }
}