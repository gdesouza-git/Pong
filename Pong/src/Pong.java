import java.awt.Color;

public class Pong {
    public static final int FIELD_WIDTH = 800;
    public static final int FIELD_HEIGHT = 600;
    public static final String PLAYER1 = "Player 1";
    public static final String PLAYER2 = "Player 2";
    public static final String TOP = "Top";
    public static final String BOTTOM = "Bottom";
    public static final String LEFT = "Left";
    public static final String RIGHT = "Right";

    public Pong() {
    }

    private static void busyWait(long var0) {
        while(System.currentTimeMillis() < var0) {
            Thread.yield();
        }
    }

    private static Wall[] initWalls() {
        Wall[] var0 = new Wall[]{new Wall(10.0D, 350.0D, 20.0D, 500.0D, Color.WHITE, "Left"), new Wall(790.0D, 350.0D, 20.0D, 500.0D, Color.WHITE, "Right"), new Wall(400.0D, 110.0D, 800.0D, 20.0D, Color.WHITE, "Top"), new Wall(400.0D, 590.0D, 800.0D, 20.0D, Color.WHITE, "Bottom")};
        return var0;
    }

    private static Player[] initPlayers() {
        Player[] var0 = new Player[2];
        double[] var1 = new double[]{120.0D, 580.0D};
        var0[0] = new Player(80.0D, 300.0D, 20.0D, 100.0D, Color.GREEN, "Player 1", var1, 0.5D);
        var0[1] = new Player(720.0D, 300.0D, 20.0D, 100.0D, Color.BLUE, "Player 2", var1, 0.5D);
        return var0;
    }

    private static Score[] initScores() {
        Score[] var0 = new Score[]{new Score("Player 1"), new Score("Player 2")};
        return var0;
    }

    public static void main(String[] var0) {
        long var1 = var0.length > 0 ? Long.parseLong(var0[0]) : 5L;
        boolean var3 = var0.length > 1 ? Boolean.parseBoolean(var0[1]) : false;
        boolean var4 = true;
        boolean var5 = false;
        System.out.println("safe_mode = " + var3);
        if (var3) {
            GameLib.initGraphics_SAFE_MODE("Pong!", 800, 600);
        } else {
            GameLib.initGraphics("Pong!", 800, 600);
        }

        Ball var6 = new Ball(400.0D, 300.0D, 20.0D, 20.0D, Color.YELLOW, 0.4D);
        Wall[] var7 = initWalls();
        Player[] var8 = initPlayers();
        Score[] var9 = initScores();

        long var12;
        for(long var10 = System.currentTimeMillis(); var4; var10 = var12) {
            var12 = System.currentTimeMillis();
            if (GameLib.isKeyPressed(10)) {
                var5 = true;
            }

            Player[] var14;
            int var15;
            int var16;
            Player var17;
            Wall[] var18;
            Wall var20;
            if (!var5) {
                if (System.currentTimeMillis() / 500L % 2L == 0L) {
                    GameLib.setColor(Color.YELLOW);
                    GameLib.drawText("Pressione <ESPAÇO> para começar", 480.0D, 2);
                }

                GameLib.setColor(Color.GREEN);
                GameLib.drawText("Teclas A/Z: move o jogador da esquerda", 520.0D, 2);
                GameLib.setColor(Color.BLUE);
                GameLib.drawText("Telas K/M: move o jogador da direita", 560.0D, 2);
            } else {
                var6.update(var12 - var10);
                if (GameLib.isKeyPressed(6)) {
                    var8[0].moveUp(var12 - var10);
                }

                if (GameLib.isKeyPressed(7)) {
                    var8[0].moveDown(var12 - var10);
                }

                if (GameLib.isKeyPressed(8)) {
                    var8[1].moveUp(var12 - var10);
                }

                if (GameLib.isKeyPressed(9)) {
                    var8[1].moveDown(var12 - var10);
                }

                var14 = var8;
                var15 = var8.length;

                for(var16 = 0; var16 < var15; ++var16) {
                    var17 = var14[var16];
                    if (var6.checkCollision(var17)) {
                        var6.onPlayerCollision(var17.getId());
                        break;
                    }
                }

                var18 = var7;
                var15 = var7.length;

                for(var16 = 0; var16 < var15; ++var16) {
                    var20 = var18[var16];
                    if (var6.checkCollision(var20)) {
                        if (var20.getId().equals("Left")) {
                            var9[1].inc();
                        }

                        if (var20.getId().equals("Right")) {
                            var9[0].inc();
                        }

                        var6.onWallCollision(var20.getId());
                        break;
                    }
                }
            }

            GameLib.setColor(Color.YELLOW);
            GameLib.drawText("Pong!", 70.0D, 2);
            var6.draw();
            var18 = var7;
            var15 = var7.length;

            for(var16 = 0; var16 < var15; ++var16) {
                var20 = var18[var16];
                var20.draw();
            }

            var14 = var8;
            var15 = var8.length;

            for(var16 = 0; var16 < var15; ++var16) {
                var17 = var14[var16];
                var17.draw();
            }

            Score[] var19 = var9;
            var15 = var9.length;

            for(var16 = 0; var16 < var15; ++var16) {
                Score var21 = var19[var16];
                var21.draw();
            }

            GameLib.display();
            busyWait(var12 + var1);
        }

    }
}
