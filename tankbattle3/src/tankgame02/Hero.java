package tankgame02;

import java.util.Vector;

public class Hero extends Tank {
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y, int direction) {
        super(x, y, direction);
    }

    public void shotEnemy() {
        if (shots.size() >= 5) {
            return;
        }

        switch (getDirection()) {
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);//创建子弹
                shot.setSpeed(5);//设置我方子弹的速度
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                shot.setSpeed(5);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                shot.setSpeed(5);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                shot.setSpeed(5);
                break;
        }
        shots.add(shot);
        new Thread(shot).start();//启动子弹线程
    }
}
