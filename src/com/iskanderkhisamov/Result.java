package com.iskanderkhisamov;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Result extends JFrame {

    private JPanel resultPanel;
    private JLabel rightAnswersLabel;
    private JLabel countLabel;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel answer1;
    private JLabel answer2;
    private JLabel answer3;
    private JLabel answer4;
    private JLabel answer5;
    private JLabel rightAnswer1;
    private JLabel rightAnswer2;
    private JLabel rightAnswer3;
    private JLabel rightAnswer4;
    private JLabel rightAnswer5;
    private JLabel nameOfStudent;
    private JButton ratingButton;

    public Result(String name, int count, String[][] answers, ArrayList<String> studentAnswers, ArrayList<String> rightAnswers) {
        setTitle("Тест - Результаты");
        setContentPane(resultPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        countLabel.setText(String.valueOf(count));
        System.out.println("Количество правильных ответов: " + count);
        nameOfStudent.setText(name);
        System.out.println("Фамилия: " + name);
        answer1.setText(studentAnswers.get(0));
        answer2.setText(studentAnswers.get(1));
        answer3.setText(studentAnswers.get(2));
        answer4.setText(studentAnswers.get(3));
        answer5.setText(studentAnswers.get(4));
        for (String element : rightAnswers) {
            for (String answer : answers[0]) {
                if (element.equals(answer)) {
                    rightAnswer1.setText(element);
                }
            }
        }
        for (String element : rightAnswers) {
            for (String answer : answers[1]) {
                if (element.equals(answer)) {
                    rightAnswer2.setText(element);
                }
            }
        }
        for (String element : rightAnswers) {
            for (String answer : answers[2]) {
                if (element.equals(answer)) {
                    rightAnswer3.setText(element);
                }
            }
        }
        for (String element : rightAnswers) {
            for (String answer : answers[3]) {
                if (element.equals(answer)) {
                    rightAnswer4.setText(element);
                }
            }
        }
        for (String element : rightAnswers) {
            for (String answer : answers[4]) {
                if (element.equals(answer)) {
                    rightAnswer5.setText(element);
                }
            }
        }
        try(FileWriter writer = new FileWriter("src/com/iskanderkhisamov/result.txt", true))
        {
            LocalDateTime ldt = LocalDateTime.now().plusDays(0);
            DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formatter = formmat1.format(ldt);
            System.out.println("Дата: " + formatter);
            String text = name + " / ";
            text += count + " / ";
            text += formatter;
            writer.write(text);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        ratingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                new Rating().setVisible(true);
            }
        });
    }
}
