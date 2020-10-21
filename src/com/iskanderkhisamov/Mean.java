package com.iskanderkhisamov;

import javax.swing.*;
import java.awt.event.*;

public class Mean extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel meanText;
    private double sum = 0, count = 0;

    public Mean(String[][] stat) {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Тест - Средний балл");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(200, 200);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        for(String[] item : stat) {
            sum += Integer.parseInt(item[1]);
            count++;
        }
        meanText.setText(String.valueOf(sum/count));
        setVisible(true);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                dispose();
            }
        });
    }

    private void onOK() {
        setVisible(false);
        dispose();
    }

    private void onCancel() {
        setVisible(false);
        dispose();
    }
}
