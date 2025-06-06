import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
    public int width, height, angle;
    public double x, y, dx, dy, speed ;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.angle = new Random().nextInt(120 - 45) + 45 + 1;
        this.dx = Math.cos(Math.toRadians(angle));
        this.dy = Math.sin(Math.toRadians(angle));
        this.speed = 2;
        this.width = 4;
        this.height = 4;
    }

    public void tick() {
        if (x + (dx * speed) > Game.WIDTH || x + (dx * speed) < 0) {
            dx *= -1; 
        }

        if (y > Game.HEIGHT) {
            System.out.println("Ponint for the enemy!");
            new Game();
            return;
        } else if (y < 0) {
            System.out.println("Point for the player!");
            new Game();
            return;
        } 

        Rectangle bounds = new Rectangle((int) (x + (dx * speed)), (int) (y + (dy * speed)), width, height);
        Rectangle playerBounds = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
        Rectangle enemieBounds = new Rectangle((int) Game.enemie.x, (int) Game.enemie.y, Game.enemie.width, Game.enemie.height);

        if (bounds.intersects(playerBounds)) {
            angle = new Random().nextInt(120 - 45) + 45 + 1;
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if (dy > 0) {
                dy *= -1; 
            }
        } else if (bounds.intersects(enemieBounds)) {
            angle = new Random().nextInt(120 - 45) + 45 + 1;
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if (dy < 0) {
                dy *= -1; 
            }  
        }

        x += dx * speed;
        y += dy * speed;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int) x, (int) y, width, height);
    }
}
