package tankgame01;

import java.util.Vector;

@SuppressWarnings("all")
public class Enemy extends Tank implements Runnable {
    Vector<Shot> shots = new Vector<>();
    //增加成员，EnemyTank 可以得到敌人坦克的Vector
    Vector<Enemy> enemies = new Vector<>();

    public Enemy(int x, int y, int direction) {
        super(x, y, direction);
    }

    //这里提供一个方法，可以将MyPanel 的成员 Vector<EnemyTank> enemyTanks = new Vector<>();
    //设置到 EnemyTank 的成员 enemyTanks
    public void setEnemies(Vector<Enemy> enemies) {
        this.enemies = enemies;
    }

    //编写方法，判断当前的这个敌人坦克，是否和 enemyTanks 中的其他坦克发生的重叠或者碰撞
    public boolean isTouchEnemyTank() {

        //判断当前敌人坦克(this) 方向
        switch (this.getDirection()) {
            case 0://当前this敌人坦克向上移动
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemies.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    Enemy enemy = enemies.get(i);
                    //不和自己比较
                    if (enemy != this) {
                        //如果敌人坦克是上/下 敌人坦克覆盖范围 x[enemy.getX(),enemy.getX() + 40]
                        //                               y[enemy.getY(),enemy.getY() + 60)]
                        if (enemy.getDirection() == 0 || enemy.getDirection() == 2) {
                            //当前坦克 左上角坐标 [this.getX(),this.getY()]
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 60) {
                                return true;
                            }
                            //当前坦克 右上角坐标 [this.getX()+40,this.getY()]
                            if (this.getX() + 40 >= enemy.getX()
                                    && this.getX() + 40 <= enemy.getX() + 40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是左/右 敌人坦克覆盖范围 x[enemy.getX(),enemy.getX() + 60]
                        //                               y[enemy.getY(),enemy.getY() + 40)]
                        if (enemy.getDirection() == 1 || enemy.getDirection() == 3) {
                            //当前坦克 左上角坐标 [this.getX(),this.getY()]
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 40) {
                                return true;
                            }
                            //当前坦克 右上角坐标 [this.getX()+40,this.getY()]
                            if (this.getX() + 40 >= enemy.getX()
                                    && this.getX() + 40 <= enemy.getX() + 60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1://右
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemies.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    Enemy enemy = enemies.get(i);
                    //不和自己比较
                    if (enemy != this) {
                        //如果敌人坦克是上/下 敌人坦克覆盖范围 x[enemy.getX(),enemy.getX() + 40]
                        //                               y[enemy.getY(),enemy.getY() + 60)]
                        if (enemy.getDirection() == 0 || enemy.getDirection() == 2) {
                            //当前坦克 右上角坐标 [this.getX()+60,this.getY()]
                            if (this.getX() + 60 >= enemy.getX()
                                    && this.getX() + 60 <= enemy.getX() + 40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 60) {
                                return true;
                            }
                            //当前坦克 右下角坐标 [this.getX()+60,this.getY()+40]
                            if (this.getX() + 60 >= enemy.getX()
                                    && this.getX() + 60 <= enemy.getX() + 40
                                    && this.getY() + 40 >= enemy.getY()
                                    && this.getY() + 40 <= enemy.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是左/右 敌人坦克覆盖范围 x[enemy.getX(),enemy.getX() + 60]
                        //                               y[enemy.getY(),enemy.getY() + 40)]
                        if (enemy.getDirection() == 1 || enemy.getDirection() == 3) {
                            //当前坦克 右上角坐标 [this.getX()+60,this.getY()]
                            if (this.getX() + 60 >= enemy.getX()
                                    && this.getX() + 60 <= enemy.getX() + 60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 40) {
                                return true;
                            }
                            //当前坦克 右下角坐标 [this.getX()+60,this.getY()+40]
                            if (this.getX() + 60 >= enemy.getX()
                                    && this.getX() + 60 <= enemy.getX() + 60
                                    && this.getY() + 40 >= enemy.getY()
                                    && this.getY() + 40 <= enemy.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemies.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    Enemy enemy = enemies.get(i);
                    //不和自己比较
                    if (enemy != this) {
                        //如果敌人坦克是上/下 敌人坦克覆盖范围 x[enemy.getX(),enemy.getX() + 40]
                        //                               y[enemy.getY(),enemy.getY() + 60)]
                        if (enemy.getDirection() == 0 || enemy.getDirection() == 2) {
                            //当前坦克 左下角坐标 [this.getX(),this.getY()+60]
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 40
                                    && this.getY() + 60 >= enemy.getY()
                                    && this.getY() + 60 <= enemy.getY() + 60) {
                                return true;
                            }
                            //当前坦克 右下角坐标 [this.getX()+40,this.getY()+60]
                            if (this.getX() + 40 >= enemy.getX()
                                    && this.getX() + 40 <= enemy.getX() + 40
                                    && this.getY() + 60 >= enemy.getY()
                                    && this.getY() + 60 <= enemy.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是左/右 敌人坦克覆盖范围 x[enemy.getX(),enemy.getX() + 60]
                        //                               y[enemy.getY(),enemy.getY() + 40)]
                        if (enemy.getDirection() == 1 || enemy.getDirection() == 3) {
                            //当前坦克 左下角坐标 [this.getX(),this.getY()+60]
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 60
                                    && this.getY() + 60 >= enemy.getY()
                                    && this.getY() + 60 <= enemy.getY() + 40) {
                                return true;
                            }
                            //当前坦克 右下角坐标 [this.getX()+40,this.getY()+60]
                            if (this.getX() + 40 >= enemy.getX()
                                    && this.getX() + 40 <= enemy.getX() + 60
                                    && this.getY() + 60 >= enemy.getY()
                                    && this.getY() + 60 <= enemy.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemies.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    Enemy enemy = enemies.get(i);
                    //不和自己比较
                    if (enemy != this) {
                        //如果敌人坦克是上/下 敌人坦克覆盖范围 x[enemy.getX(),enemy.getX() + 40]
                        //                               y[enemy.getY(),enemy.getY() + 60)]
                        if (enemy.getDirection() == 0 || enemy.getDirection() == 2) {
                            //当前坦克 左上角坐标 [this.getX(),this.getY()]
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 40
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 60) {
                                return true;
                            }
                            //当前坦克 左下角坐标 [this.getX(),this.getY()+40]
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 40
                                    && this.getY() + 40 >= enemy.getY()
                                    && this.getY() + 40 <= enemy.getY() + 60) {
                                return true;
                            }
                        }
                        //如果敌人坦克是左/右 敌人坦克覆盖范围 x[enemy.getX(),enemy.getX() + 60]
                        //                               y[enemy.getY(),enemy.getY() + 40)]
                        if (enemy.getDirection() == 1 || enemy.getDirection() == 3) {
                            //当前坦克 左上角坐标 [this.getX(),this.getY()]
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 60
                                    && this.getY() >= enemy.getY()
                                    && this.getY() <= enemy.getY() + 40) {
                                return true;
                            }
                            //当前坦克 左下角坐标 [this.getX(),this.getY()+40]
                            if (this.getX() >= enemy.getX()
                                    && this.getX() <= enemy.getX() + 60
                                    && this.getY() + 40 >= enemy.getY()
                                    && this.getY() + 40 <= enemy.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            //这里我们判断如果shots size() <5, 创建一颗子弹，放入到shots集合，并启动
            Shot shot = null;
            if (isLive && shots.size() < 5) {
                switch (getDirection()) {
                    case 0:
                        shot = new Shot(getX() + 20, getY(), 0);//创建子弹
                        shot.setSpeed(2);//设置敌方子弹的速度
                        break;
                    case 1:
                        shot = new Shot(getX() + 60, getY() + 20, 1);
                        shot.setSpeed(2);
                        break;
                    case 2:
                        shot = new Shot(getX() + 20, getY() + 60, 2);
                        shot.setSpeed(2);
                        break;
                    case 3:
                        shot = new Shot(getX(), getY() + 20, 3);
                        shot.setSpeed(2);
                        break;
                }
                shots.add(shot);
                new Thread(shot).start();
            }

            //根据坦克的方向来继续激动
            int step = 60;
            switch (getDirection()) {
                case 0:
                    for (int i = 0; i < step; i++) {
                        if (getY() > 0 && !isTouchEnemyTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < step; i++) {
                        if (getX() + 80 < 1000 && !isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < step; i++) {
                        if (getY() + 100 < 750 && !isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < step; i++) {
                        if (getX() > 0 && !isTouchEnemyTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            setDirection((int) (Math.random() * 4));
            if (!isLive) {
                break;//退出线程
            }
        }
    }
}
