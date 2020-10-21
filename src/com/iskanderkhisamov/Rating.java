package com.iskanderkhisamov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Rating extends JFrame {

    private JPanel panel1;
    private String[][] statistic;
    private ArrayList<String> buffer = new ArrayList<>();

    public Rating() {
        setTitle("Тест - Рейтинг");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);

        String[] columnNames = {
                "Фамилия",
                "Количество верных ответов",
                "Дата"
        };

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
        JTable table = new JTable(statistic, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JButton diagButton = new JButton("Лепестковая диаграмма");
        JButton meanButton = new JButton("Средний балл");
        JButton dispButton = new JButton("Дисперсия");
        JButton gistButton = new JButton("Гистограмма распределения");
        //getContentPane().add(scrollPane);
        //getContentPane().add(button);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(diagButton);
        buttonPanel.add(meanButton);
        buttonPanel.add(dispButton);
        buttonPanel.add(gistButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        diagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new NewJFrame().setVisible(true);
            }
        });
        meanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Mean(statistic).setVisible(true);
            }
        });
        dispButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Variance(statistic).setVisible(true);
            }
        });
        gistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Histogram(statistic).setVisible(true);
            }
        });
    }
}
