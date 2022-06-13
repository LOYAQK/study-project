package tankgame01;

@SuppressWarnings("all")
public class Shot implements Runnable {
    int x;
    int y;
    int direction;
    boolean isLive = true;
    private int speed = 1;

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        while (true) {
            switch (direction) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            try {
                Thread.sleep(50);//子弹线程休眠50ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子弹 x=" + x + " y=" + y);
            if (!isLive || !(x > 0 && x < 1000 && y > 0 && y < 750)) {
                isLive = false;
                System.out.println("子弹退出线程...");
                break;
            }
        }
    }
}
