package tankgame01;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * 新增功能，记录玩家成绩，记录敌人坦克信息，继续游戏
 */
public class TankApp extends JFrame {
    MyPanel mp = null;
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        TankApp tankApp = new TankApp();
    }

    public TankApp() {
        System.out.println("请输入选择 1: 新游戏 2: 继续上局");
        String key = scanner.next();
        mp = new MyPanel(key);
        new Thread(mp).start();//启动线程
        this.addKeyListener(mp);//监听键盘
        this.add(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1300, 950);
        this.setVisible(true);

        //在JFrame 中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });

    }
}
