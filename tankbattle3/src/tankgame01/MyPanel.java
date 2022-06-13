package tankgame01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

@SuppressWarnings("all")
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    Hero hero = null;
    //定义敌人坦克，放入到Vector
    Vector<Enemy> enemies = new Vector<>();
    int enemiesSize = 3;
    //定义一个Vector ,用于存放炸弹,当子弹击中坦克时，加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();
    //定义一个存放Node 对象的Vector, 用于恢复敌人坦克的坐标和方向
    Vector<Node> nodes = new Vector<>();
    //定义三张炸弹图片，用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String key) {
        nodes = Recorder.getNodesAndEnemyTankRec();
        //将MyPanel对象的 enemies 设置给 Recorder 的 enemies
        Recorder.setEnemies(enemies);
        hero = new Hero(200, 200, 0);//初始化我们的坦克
        hero.setSpeed(10);//设置我们坦克的速度

        //创建敌人坦克 key=1 开始新游戏 key=2 继续游戏
        switch (key) {
            case "1":
                for (int i = 0; i < enemiesSize; i++) {
                    Enemy enemy = new Enemy((i + 1) * 100, 0, 2);//初始化敌人坦克
                    enemy.setEnemies(enemies);//把敌人坦克Vector集合，传给enemy，用于判断是否重叠
                    new Thread(enemy).start();//启动敌人坦克线程
                    enemies.add(enemy);//将敌人坦克放入 Vector 集合
                }
                break;
            case "2":
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                    Enemy enemy = new Enemy(node.getX(),node.getY(),node.getDirect());//初始化敌人坦克
                    enemy.setEnemies(enemies);//把敌人坦克Vector集合，传给enemy，用于判断是否重叠
                    new Thread(enemy).start();//启动敌人坦克线程
                    enemies.add(enemy);//将敌人坦克放入 Vector 集合
                }
                break;
            default:
                System.out.println("你的输入有误...");
        }


        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));
    }

    //编写方法，显示我方击毁敌方坦克的信息
    public void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累积击毁敌方坦克",1020,30);
        drawTank(1020,60,0,g,1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getDestructionNum() + "",1080,100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, 1000, 750);
        showInfo(g);//显示我方击毁敌方坦克的信息

        if (hero.isLive) {
            drawTank(hero.getX(), hero.getY(), hero.getDirection(), g, 0);//绘制我们坦克
        }
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.isLive) {//再画敌人坦克前先判断 isLive是否为true，不是就把该坦克从集合移除
                drawTank(enemy.getX(), enemy.getY(), enemy.getDirection(), g, 1);//绘制敌方坦克
                for (int j = 0; j < enemy.shots.size(); j++) {//遍历每一个敌方坦克的子弹集合
                    Shot shot = enemy.shots.get(j);
                    if (shot.isLive) {//如果子弹isLive 为true，绘制子弹，否则将子弹移除集合
                        g.setColor(Color.YELLOW);
                        g.fill3DRect(shot.x, shot.y, 3, 3, false);//绘制子弹
                    } else {
                        enemy.shots.remove(shot);
                    }
                }
            } else {
                enemies.remove(i);
            }
        }
        //如果bombs 集合中有对象，就画出
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown();
            if (bomb.life == 0) {
                bombs.remove(i);
            }
        }
        for (int i = 0; i < hero.shots.size(); i++) {//绘制我们的子弹
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive) {//如果子弹不等于null并且子弹存活，就画出来，否则就从 Vector中移除
                g.setColor(Color.CYAN);
                g.fill3DRect(shot.x, shot.y, 3, 3, false);//绘制子弹
            } else {
                hero.shots.remove(i);
            }
        }
        /**
         * 绘制单颗子弹
         if (hero.shot != null && hero.shot.isLive) {
         g.fill3DRect(hero.shot.x, hero.shot.y, 3, 3, false);
         }
         */
    }

    /**
     * @param x         要绘制坦克的横坐标
     * @param y         要绘制坦克的纵坐标
     * @param direction 要绘制坦克的方向 0(上) 1(右) 2(下) 3(左)
     * @param g         画笔
     * @param type      坦克类型 0(我方) 1(敌方)
     */
    public void drawTank(int x, int y, int direction, Graphics g, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direction) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);//左轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//右轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//中间盖子
                g.drawOval(x + 10, y + 20, 20, 20);//中间圆圈
                g.drawLine(x + 20, y + 30, x + 20, y);//炮管
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.drawOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);//左轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//右轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//中间盖子
                g.drawOval(x + 10, y + 20, 20, 20);//中间圆圈
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//炮管
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.drawOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
        }
    }

    /**
     * @param s    单颗子弹，自己发出或敌方发出的
     * @param tank 自己坦克或敌方坦克
     */
    public void hitTank(Shot s, Tank tank) {
        switch (tank.getDirection()) {
            case 0:
            case 2:
                //判断子弹是否在要击中坦克范围之内，如果是，子弹和待击中坦克isLive置为false
                if (s.x > tank.getX() && s.x < tank.getX() + 40 && s.y > tank.getY() && s.y < tank.getY() + 60) {
                    s.isLive = false;
                    tank.isLive = false;
                    if (tank instanceof Enemy) {//如果我们子弹击中敌人坦克，击毁敌人坦克数加一
                        Recorder.addDestructionNum();
                    }
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());//创建一个爆炸类
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (s.x > tank.getX() && s.x < tank.getX() + 60 && s.y > tank.getY() && s.y < tank.getY() + 40) {
                    s.isLive = false;
                    tank.isLive = false;
                    if (tank instanceof Enemy) {
                        Recorder.addDestructionNum();
                    }
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    //取出hero子弹集合中的每一颗子弹，遍历敌人坦克,调用hitTank方法
    public void hitEnemyTank() {
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);
                hitTank(shot, enemy);
            }
        }
    }

    public void hitHero() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            for (int j = 0; j < enemy.shots.size(); j++) {
                Shot shot = enemy.shots.get(j);
                hitTank(shot, hero);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {//监听键盘按下情况
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirection(0);
            if (hero.getY() > 0) {
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirection(1);
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirection(2);
            if (hero.getY() + 60 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirection(3);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_J) {
            /**
             * 发射单颗子弹
             if (hero.shot == null || hero.shot.isLive == false) {
             hero.shotEnemy();
             }
             */
            hero.shotEnemy();
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hitHero();
            hitEnemyTank();
            this.repaint();
        }
    }
}
