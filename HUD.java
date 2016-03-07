import java.awt.Graphics;
import java.awt.Color;
public class HUD extends Obj {
    private int score;
    
    public HUD() {
        x = 15;
        y = 15;
        w = 90;
        h = 30;
        this.score = 0;
        id = ID.HUD;
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(x, y, w, h);
        g.drawString("Score: " + score, x + 10, y + 20); 
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
}