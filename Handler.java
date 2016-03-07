
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Handler {
    ArrayList<Obj> objects = new ArrayList();
    
    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            Obj tempObject = objects.get(i);
            tempObject.tick();
            
        }
        // System.out.println("Size: " + objects.size()); // Diagnostic
    }
    
    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            Obj tempObject = objects.get(i);
            tempObject.render(g);
        }
    }
    
    public void addObject(Obj object) {
        objects.add(object);
    }
    
    public void removeObject(Obj object) {
        objects.remove(object);
    }
}

