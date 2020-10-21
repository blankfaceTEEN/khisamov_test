package com.iskanderkhisamov;

import javax.swing.*;
import javax.swing.plaf.synth.ColorType;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Histogram extends JFrame {

    private JPanel panel;
    private int maxNum = 5;
    private ArrayList<Integer> data = new ArrayList<>();
    Color[] col;

    public Histogram(String[][] stat) {
        for(String[] item : stat) {
            data.add(Integer.parseInt(item[1]));
        }
        col = new Color[data.size()];
        for(int i = 0; i < col.length; i++) {
            col[i] = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)); //генерируем цвет
        }
        panel = new Painter();
        JButton OKButton = new JButton("OK");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(OKButton);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        setTitle("Тест - Гистограмма");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        });
    }

    class Painter extends JPanel {


        @Override
        public void paint(Graphics g) {

            int poloska_w = getWidth() / data.size();

            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            for (int i = 0; i < data.size(); i++) {
                int poloska_h = data.get(i) * getHeight() / maxNum;
                g.setColor(col[i]);
                g.drawRect(i * poloska_w, getHeight() - poloska_h, poloska_w, poloska_h);
                g.fillRect(i * poloska_w, getHeight() - poloska_h, poloska_w, poloska_h);
            }
        }

    }
}
