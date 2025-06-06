import java.awt.Color;
import java.awt.Graphics;

public class Enemie {
    public int width, height;
    public double x, y;

    public Enemie(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 5;
    }

    public void tick() {
        x += (Game.ball.x - x - width / 2) * 0.05;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, width, height);
    }
}
