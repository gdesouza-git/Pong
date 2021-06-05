import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class GameLib {
    public static int WIDTH = 480;
    public static int HEIGHT = 700;
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_RIGHT = 1;
    public static final int ALIGN_CENTER = 2;
    public static final int KEY_UP = 0;
    public static final int KEY_DOWN = 1;
    public static final int KEY_LEFT = 2;
    public static final int KEY_RIGHT = 3;
    public static final int KEY_CONTROL = 4;
    public static final int KEY_ESCAPE = 5;
    public static final int KEY_A = 6;
    public static final int KEY_Z = 7;
    public static final int KEY_K = 8;
    public static final int KEY_M = 9;
    public static final int KEY_SPACE = 10;
    private static MyFrame frame = null;
    private static Graphics g = null;
    private static MyKeyAdapter keyboard = null;
    private static BufferedImage buffer = null;
    private static boolean safe_mode = false;

    public GameLib() {
    }

    public static void initGraphics(String var0, int var1, int var2) {
        WIDTH = var1;
        HEIGHT = var2;
        frame = new MyFrame(var0);
        frame.setDefaultCloseOperation(3);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);
        keyboard = new MyKeyAdapter();
        frame.addKeyListener(keyboard);
        frame.requestFocus();
        frame.createBufferStrategy(2);
        g = frame.getBufferStrategy().getDrawGraphics();
        System.out.println(frame.getBufferStrategy());
    }

    public static void initGraphics() {
        initGraphics("Projeto COO", WIDTH, HEIGHT);
    }

    public static void initGraphics_SAFE_MODE(String var0, int var1, int var2) {
        WIDTH = var1;
        HEIGHT = var2;
        frame = new MyFrame(var0);
        frame.setDefaultCloseOperation(3);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setVisible(true);
        keyboard = new MyKeyAdapter();
        frame.addKeyListener(keyboard);
        frame.requestFocus();
        frame.createBufferStrategy(1);
        safe_mode = true;
        buffer = new BufferedImage(var1, var2, 1);
        g = buffer.createGraphics();
        System.out.println(frame.getBufferStrategy());
    }

    public static void setColor(Color var0) {
        g.setColor(var0);
    }

    public static void drawLine(double var0, double var2, double var4, double var6) {
        g.drawLine((int)Math.round(var0), (int)Math.round(var2), (int)Math.round(var4), (int)Math.round(var6));
    }

    public static void drawCircle(double var0, double var2, double var4) {
        int var6 = (int)Math.round(var0 - var4);
        int var7 = (int)Math.round(var2 - var4);
        int var8 = (int)Math.round(2.0D * var4);
        int var9 = (int)Math.round(2.0D * var4);
        g.drawOval(var6, var7, var8, var9);
    }

    public static void drawDiamond(double var0, double var2, double var4) {
        int var6 = (int)Math.round(var0);
        int var7 = (int)Math.round(var2 - var4);
        int var8 = (int)Math.round(var0 + var4);
        int var9 = (int)Math.round(var2);
        int var10 = (int)Math.round(var0);
        int var11 = (int)Math.round(var2 + var4);
        int var12 = (int)Math.round(var0 - var4);
        int var13 = (int)Math.round(var2);
        drawLine((double)var6, (double)var7, (double)var8, (double)var9);
        drawLine((double)var8, (double)var9, (double)var10, (double)var11);
        drawLine((double)var10, (double)var11, (double)var12, (double)var13);
        drawLine((double)var12, (double)var13, (double)var6, (double)var7);
    }

    public static void drawPlayer(double var0, double var2, double var4) {
        drawLine(var0 - var4, var2 + var4, var0, var2 - var4);
        drawLine(var0 + var4, var2 + var4, var0, var2 - var4);
        drawLine(var0 - var4, var2 + var4, var0, var2 + var4 * 0.5D);
        drawLine(var0 + var4, var2 + var4, var0, var2 + var4 * 0.5D);
    }

    public static void drawExplosion(double var0, double var2, double var4) {
        byte var6 = 5;
        int var7 = (int)(255.0D - Math.pow(var4, (double)var6) * 255.0D);
        int var8 = (int)(128.0D - Math.pow(var4, (double)var6) * 128.0D);
        byte var9 = 0;
        setColor(new Color(var7, var8, var9));
        drawCircle(var0, var2, var4 * var4 * 40.0D);
        drawCircle(var0, var2, var4 * var4 * 40.0D + 1.0D);
    }

    public static void fillRect(double var0, double var2, double var4, double var6) {
        int var8 = (int)Math.round(var0 - var4 / 2.0D);
        int var9 = (int)Math.round(var2 - var6 / 2.0D);
        g.fillRect(var8, var9, (int)Math.round(var4), (int)Math.round(var6));
    }

    public static void drawText(String var0, double var1, int var3) {
        int var4 = 0;
        g.setFont(new Font("Monospaced", 0, 28));
        FontMetrics var6 = g.getFontMetrics();
        int var5 = var6.stringWidth(var0);
        if (var3 == 0) {
            var4 = 40;
        } else if (var3 == 1) {
            var4 = WIDTH - var5 - 40;
        } else if (var3 == 2) {
            var4 = WIDTH / 2 - var5 / 2;
        }

        g.drawString(var0, var4, (int)Math.round(var1));
    }

    public static void display() {
        if (safe_mode) {
            frame.getBufferStrategy().getDrawGraphics().drawImage(buffer, 0, 0, (ImageObserver)null);
            frame.getBufferStrategy().show();
            Toolkit.getDefaultToolkit().sync();
        } else {
            g.dispose();
            frame.getBufferStrategy().show();
            Toolkit.getDefaultToolkit().sync();
            g = frame.getBufferStrategy().getDrawGraphics();
        }

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, frame.getWidth() - 1, frame.getHeight() - 1);
        g.setColor(Color.WHITE);
    }

    public static boolean isKeyPressed(int var0) {
        return keyboard.isKeyPressed(var0);
    }

    public static void debugKeys() {
        keyboard.debug();
    }
}
