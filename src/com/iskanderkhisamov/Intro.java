package com.iskanderkhisamov;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Intro extends JFrame {
    private JTextField textField1;
    private JPanel rootPanel;
    private JButton readyButton;

    public Intro() {
        setTitle("Тест - Начало");
        setContentPane(rootPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 100);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                Ticket ticket = Controller.init(textField1.getText());
                new Window(ticket).setVisible(true);
            }
        });
    }
}
