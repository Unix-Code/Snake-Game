
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Date;

/**
 *
 * @author David
 */
public class Game extends Canvas implements Runnable {

    public static final int width = 640, height = 480;
    public static final int FPS = 60;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    
    protected static boolean gameOver;

    public Game() {
        gameOver = false;
        handler = new Handler();
        this.addKeyListener(new KeyCheck(handler));
        new Window(width, height, "Jocular Fibula", this);
        // handler.addObject(new Background(0, 0));
        handler.addObject(new Robot(50, 15, 16, 16, handler));
        handler.addObject(new Stationary((int)(Math.random()* (Game.width - 33)), (int)(Math.random() * (Game.height - 33)), 16, 16, handler));
        handler.addObject(new HUD());
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        long nextRepaintDue = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println("FPS: " + frames);
                frames = 0;
            }
            
            if (nextRepaintDue > (new Date()).getTime()) {
                // too soon to repaint, wait...
                try {
                    Thread.currentThread().sleep(nextRepaintDue - (new Date()).getTime());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            nextRepaintDue = (new Date()).getTime() + 1000 / FPS;
            
        }

        stop();
    }

    public static void main(String[] args) {
        new Game();
    }

    private void tick() {
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        handler.render(g);
        
        if (gameOver){
            g.setColor(Color.red);
            g.drawString("Game Over", Game.width/2, Game.height/2);
        }
        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        return (var >= max) ? var = max : ((var <= min) ? var = min : var);
    }

    public static double rebound(double var, int check, int min, int max) {
        return (check >= max || check <= min) ? var * -1.0 : var;
    }

    public static double reboundDouble(double var, double check, double min, double max) {
        return (check >= max || check <= min) ? var * -1.0 : var;
    }
    
    public static boolean hitWall(int check, int min, int max) {
        return (check >= max || check <= min);
    }
}
