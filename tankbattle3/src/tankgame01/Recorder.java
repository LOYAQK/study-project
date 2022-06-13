package tankgame01;

import java.io.*;
import java.util.Vector;

/**
 * 该类用于记录相关信息.和文件交互
 */
@SuppressWarnings("all")
public class Recorder {
    //定义变量，记录我方击毁敌人坦克数
    private static int destructionNum = 0;
    //定义IO对象, 准备写数据到文件中
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "e:\\myRecord.txt";//文件保存位置
    //定义Vector ,指向 MyPanel 对象的 敌人坦克Vector
    private static Vector<Enemy> enemies = null;
    private static Vector<Node> nodes = new Vector<>();



    public static void setEnemies(Vector<Enemy> enemies) {
        Recorder.enemies = enemies;
    }

    public static int getDestructionNum() {
        return destructionNum;
    }

    public static void setDestructionNum(int destructionNum) {
        Recorder.destructionNum = destructionNum;
    }

    //击毁一辆敌人坦克 记录数就加一
    public static void addDestructionNum() {
        Recorder.destructionNum++;
    }

    //当游戏退出时，将destructionNum 保存到 recordFile
    public static void keepRecord() {

        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(destructionNum + "\r\n");
            //遍历敌人坦克的Vector ,保存敌人坦克 坐标和方向
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                if (enemy.isLive) {
                    //保存该enemy信息
                    String record = enemy.getX() + " " + enemy.getY() + " " + enemy.getDirection();
                    //写入到文件
                    bw.write(record+ "\r\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //增加一个方法，用于读取recordFile, 恢复相关信息
    public static Vector<Node> getNodesAndEnemyTankRec() {

        try {
            br = new BufferedReader(new FileReader(recordFile));
            destructionNum = Integer.parseInt(br.readLine());
            //循环读取文件，生成node集合
            String line = "";//255 300 2
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]),
                        Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }
}
