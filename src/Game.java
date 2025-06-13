import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
    public static int WIDTH = 160;
    public static int HEIGHT = 120;
    public static int SCALE = 3;
    
    public static Player player;
    public static Enemie enemie;
    public static Ball ball;

    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.addKeyListener(this);

        player = new Player((WIDTH / 2) - 20, HEIGHT - 5);
        enemie = new Enemie((WIDTH / 2) - 20, 0);
        ball = new Ball((WIDTH / 2) - 2, HEIGHT / 2);
    }

    public static void main(String[] args) {
        Game game = new Game();

        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(game).start();
    }

    public void tick() {
        player.tick();
        enemie.tick();
        ball.tick();
    }
    
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = layer.getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        player.render(g);
        enemie.render(g);
        ball.render(g);

        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

        bs.show();
    }

    @Override
    public void run() {
        while (true) {
            requestFocus();
            tick();
            render();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = true;
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
