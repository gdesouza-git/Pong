import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class MyKeyAdapter extends KeyAdapter {
    private int[] codes = new int[]{38, 40, 37, 39, 17, 27, 65, 90, 75, 77, 32};
    private boolean[] keyStates = null;
    private long[] releaseTimeStamps = null;

    public MyKeyAdapter() {
        this.keyStates = new boolean[this.codes.length];
        this.releaseTimeStamps = new long[this.codes.length];
    }

    public int getIndexFromKeyCode(int var1) {
        for(int var2 = 0; var2 < this.codes.length; ++var2) {
            if (this.codes[var2] == var1) {
                return var2;
            }
        }

        return -1;
    }

    public void keyPressed(KeyEvent var1) {
        int var2 = this.getIndexFromKeyCode(var1.getKeyCode());
        if (var2 >= 0) {
            this.keyStates[var2] = true;
        }

    }

    public void keyReleased(KeyEvent var1) {
        int var2 = this.getIndexFromKeyCode(var1.getKeyCode());
        if (var2 >= 0) {
            this.keyStates[var2] = false;
            this.releaseTimeStamps[var2] = System.currentTimeMillis();
        }

    }

    public boolean isKeyPressed(int var1) {
        boolean var2 = this.keyStates[var1];
        long var3 = this.releaseTimeStamps[var1];
        return var2 || System.currentTimeMillis() - var3 <= 5L;
    }

    public void debug() {
        System.out.print("Key states = {");

        for(int var1 = 0; var1 < this.codes.length; ++var1) {
            System.out.print(" " + this.keyStates[var1] + (var1 < this.codes.length - 1 ? "," : ""));
        }

        System.out.println(" }");
    }
}
