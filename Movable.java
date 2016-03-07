 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author David
 */
public class Movable extends Obj {
    protected double vel;
    protected Handler handler;
    protected Collision move;
    
    public Movable() {
        super();
        vel = 1.0;
        move = new Collision();
    }
    
    public Movable(int x, int y, int w, int h, double velX, double velY, Handler handler) {
        super(x, y, w, h, ID.Robot);
        this.vel = vel;
        this.handler = handler;
        move = new Collision();
    }
    
    //only to be used by Character through super()
    public Movable(int x, int y, int w, int h, double vel, ID id, Handler handler) {
        super(x, y, w, h, id);
        this.vel = vel;
        this.handler  = handler;
        move = new Collision();
    }
    
    public void tick() {
        // move = detectSurface();
        x += vel;
        y += vel;
        
        x = Game.clamp(x, 0, Game.width - w);
        y = Game.clamp(y, 0, Game.height - h);
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, w, h);
    }

    public double getVel() {
        return vel;
    }
    
    public void setVel(double vel) {
        this.vel = vel;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, w, h);
    }
    /*
    private Collision detectSurface() {
        // double[] move = {this.velX, this.velY};
        Collision move = new Collision(this.velX, this.velY);
        for (int i = 0; i < handler.objects.size(); i++) {
            Stationary tempStationary = null;
            
            if (handler.objects.get(i).getId() == ID.Item) tempStationary = (Stationary) handler.objects.get(i);
            if (tempStationary != null) {
                
                Rectangle nextCharX = new Rectangle((int)(this.x + this.velX), this.y, this.w, this.h);
                Rectangle nextCharY = new Rectangle(this.x, (int)(this.y + this.velY), this.w, this.h);

                if (nextCharX.intersects(tempStationary.getBounds())) {
                    Rectangle intersection = nextCharX.intersection(tempStationary.getBounds());
                    
                    move.moveX = (this.velX > 0) ? this.velX - intersection.getWidth() : this.velX + intersection.getWidth();
                    move.hitX = true;
                }
                
                if (nextCharY.intersects(tempStationary.getBounds())) {
                    Rectangle intersection = nextCharY.intersection(tempStationary.getBounds());
                    
                    move.moveY = (this.velY > 0) ? this.velY - intersection.getHeight() : this.velY + intersection.getHeight();
                    move.hitY = true;
                }
            }
        }
        return move;
    }
    */
}
