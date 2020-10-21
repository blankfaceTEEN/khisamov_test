package com.iskanderkhisamov;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

class PFields { //класс, описывающий поле
    int rx, ry; //координаты квадрата
    int znach; //значение
    Color col; //цвет
}

class Fields { //поля
    private String[][] statistic;
    private ArrayList<String> buffer = new ArrayList<>();
    static boolean visib = false; //флаг, если true то разрешена отрисовка диаграммы
    static PFields[] a;
    static PFields[] b;
    static int count = 0, countb = 0;

    public Fields() {
        try {
            File file = new File("src/com/iskanderkhisamov/result.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                buffer.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        statistic = new String[buffer.size()][3];
        for (int i = 0; i < buffer.size(); i++) {
            byte j = 0;
            for (String atom : buffer.get(i).split(" / ")) {
                statistic[i][j] = atom;
                j++;
            }
        }
        a = new PFields[statistic.length];
        b = new PFields[statistic.length];
        for(int i = 0; i < statistic.length; i++) {
            a[i] = new PFields();
            a[i].znach = Integer.parseInt(statistic[i][1]);
        }
        count = statistic.length;
    }
}

class TurnPoint { //класс, поворачивающий точку относительно центра диаграммы
    int px, py, npx, npy; //старые и новые координаты точки
    float alpha; //угол поворота

    void Point(int px, int py) {  //записывает точку
        this.px = px;
        this.py = py;
    }

    void Turn(float alpha) { //поворачивает точку на угол альфа
        npx = (int) ((px - 200) * Math.cos(Math.toRadians(alpha))) + py;
        npy = (int) (-(px - 200) * Math.sin(Math.toRadians(alpha))) + py;
    }

}

class MCanvas extends Canvas { //расширяем класс Canvas
    public MCanvas() {
        super();
    }

    @Override
    public void paint(Graphics g) { //Перегружаем метод Paint
        Graphics2D g2 = (Graphics2D) g;  //включаем 2д графику
        g2.setStroke(new BasicStroke(2.0f)); //устанавливаем размер линии
        g.setColor(Color.white);
        g.fillRect(10, 10, 400, 400); //рисуем фон
        if (Fields.visib) { //если разрешена отрисовка диграммы
            int alpha;
            if (Fields.count == 0) alpha = 360;
            else
                alpha = 360 / Fields.count; //считаем угол, который соответствует одному полю
            int a = alpha;
            float sum = 0, max = -1;
            for (int i = 0; i < Fields.count; i++) { //считаем сумму значений, и находим максимальное из них
                sum += Fields.a[i].znach;
                max = Math.max(max, Fields.a[i].znach);
            }
            TurnPoint p = new TurnPoint();
            int nnpx = 0, npx = 0, npy = 0;
            int nnpy = 0, fpx = 0, fpy = 0;
            for (int i = 0; i < Fields.count; i++) { //идем по всем полям
                int px = 200 + (int) (150 * Fields.a[i].znach / max); //точка нашего отрезка на нулевом градусе
                int py = 200;
                nnpx = npx;
                nnpy = npy;
                p.Point(px, py);  //записываем точку
                p.Turn(alpha); //поворачиваем на угол
                npx = p.npx; //сохранем значения
                npy = p.npy;
                if (fpx == 0 && fpy == 0) { //если это первое поле, то сохраняем координаты
                    fpx = p.npx;
                    fpy = p.npy;
                }
                if (nnpx != 0 && nnpy != 0 && Fields.count > 2)  //если поле не первое, и полей больше 2, тогда соединяем их отрезком
                    g.drawLine(nnpx, nnpy, p.npx, p.npy);
                Color c;
                c = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)); //генерируем цвет
                if (Fields.a[i].col == null)  //если рисуем впервые, то сохраняем цвет
                    Fields.a[i].col = c;
                g.setColor(Fields.a[i].col);
                g.drawLine(200, 200, p.npx, p.npy); //рисуем отрезок из центра до точки
                p.Point(px, py - 2); //координата квадрата, на 0 градусе
                p.Turn(alpha);  //поворачиваем на угол
                Fields.a[i].rx = p.npx; //сохраняем координаты квадрата
                Fields.a[i].ry = p.npy;
                g.setColor(Color.BLACK);
                p.Point(px, py); //точка на 0 градусе
                p.Turn(alpha); //поворачиваем ее на угол
                if ((alpha >= 0 && alpha < 90) || (alpha > 270 && alpha <= 360))
                    p.npx += 15; //здесь вычисляем координаты надписи
                if (alpha > 90 && alpha < 270) p.npx -= 30;
                if (alpha > 0 && alpha < 180) p.npy -= 15;
                if (alpha > 180 && alpha < 360) p.npy += 15;

                g.drawString(Integer.toString(Math.round(100*Fields.a[i].znach/sum))+'%', p.npx, p.npy);//выводим проценты
                alpha += a; //увеличиваем угол

            }
            if (Fields.count > 2)
                g.drawLine(fpx, fpy, npx, npy); //если полей больше чем 2, тогда соединяем первое и последнее отрезком

            for (int i = 0; i < Fields.count; i++) {  //идем по полям и отрисовываем квадраты
                g.setColor(Fields.a[i].col);
                g.fillRect(Fields.a[i].rx, Fields.a[i].ry, 5, 5);
            }

            g.setColor(Color.black); //рисуем квадрат в центре диаграммы
            g.fillRect(200 - 2, 200 - 2, 5, 5);
        }
    }
}

class NewJFrame extends javax.swing.JFrame {
    private java.awt.Canvas canvas1;
    public NewJFrame() {
        new Fields();
        initComponents();
        this.setSize(440, 460);
        this.canvas1 = new MCanvas();
        this.canvas1.setBounds(0, 0, 500, 500);
        this.add(this.canvas1);
        Fields.visib = true; //разрешаем отрисовку диаграммы
        canvas1.repaint(); //рисуем
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        canvas1 = new java.awt.Canvas();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Тест - Лепестковая процентная диаграмма");
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGap(18, 18, 18)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGap(165, 165, 165))))
                                                )))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE))
                                .addGap(18, 18, 18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGap(45, 45, 45)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGap(18, 18, 18)
                                .addGap(18, 18, 18)
                                .addGap(3, 3, 3)
                                .addGap(10, 10, 10)
                                .addGap(42, 42, 42)
                                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }
}
