package com.iskanderkhisamov;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Variance extends JFrame {

    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel varianceLabel;
    private int count = 0;

    public Variance(String[][] stat) {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Тест - Дисперсия");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(200, 200);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        varianceLabel.setText(String.valueOf(getVariance(stat)));
        setVisible(true);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        });
    }

    private void onOK() {
        setVisible(false);
    }

    double getVariance(String[][] stat) {
        double mean = getMean(stat);
        double temp = 0;
        for(String[] item : stat) {
            temp += (Integer.parseInt(item[1])-mean)*(Integer.parseInt(item[1])-mean);
            count++;
        }
        return temp/(count-1);
    }

    double getMean(String[][] stat) {
        double sum = 0.0;
        for(String[] item : stat) {
            sum += Integer.parseInt(item[1]);
            count++;
        }
        return sum/count;
    }
}
