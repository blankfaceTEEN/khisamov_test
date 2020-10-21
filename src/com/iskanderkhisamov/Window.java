package com.iskanderkhisamov;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Window extends JFrame  {
    private JPanel windowPanel;
    private JRadioButton Answer1RButton;
    private JRadioButton Answer4RButton;
    private JRadioButton Answer3RButton;
    private JRadioButton Answer2RButton;
    private JButton NextButton;
    private JLabel Question;
    private int numberOfQuestion = 0;
    private int countOfRightAnswers = 0;
    private ArrayList<String> studentAnswers = new ArrayList<>();


    public Window(Ticket ticket) {
        System.out.println("!!! ТЕСТ !!!\n");
        setTitle("Тест");
        setContentPane(windowPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        updateWindow(ticket.ticketQuestions, ticket.ticketAnswers, ticket.ticketRightAnswers);
        NextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Answer1RButton.isSelected()) {
                    studentAnswers.add(Answer1RButton.getText());
                    for(String element : ticket.ticketRightAnswers) {
                        if (element.equals(Answer1RButton.getText())) {
                            countOfRightAnswers++;
                        }
                    }
                } else if (Answer2RButton.isSelected()) {
                    studentAnswers.add(Answer2RButton.getText());
                    for(String element : ticket.ticketRightAnswers) {
                        if (element.equals(Answer2RButton.getText())) {
                            countOfRightAnswers++;
                        }
                    }
                } else if (Answer3RButton.isSelected()) {
                    studentAnswers.add(Answer3RButton.getText());
                    for(String element : ticket.ticketRightAnswers) {
                        if (element.equals(Answer3RButton.getText())) {
                            countOfRightAnswers++;
                        }
                    }
                } else if (Answer4RButton.isSelected()) {
                    studentAnswers.add(Answer4RButton.getText());
                    for(String element : ticket.ticketRightAnswers) {
                        if (element.equals(Answer4RButton.getText())) {
                            countOfRightAnswers++;
                        }
                    }
                }
                if (numberOfQuestion < 5) {
                    System.out.println(numberOfQuestion + ") " + studentAnswers.get(studentAnswers.size() - 1));
                    updateWindow(ticket.ticketQuestions, ticket.ticketAnswers, ticket.ticketRightAnswers);
                } else {
                    System.out.println(numberOfQuestion + ") " + studentAnswers.get(studentAnswers.size() - 1));
                    setVisible(false);
                    System.out.println("\n!!! РЕЗУЛЬТАТЫ !!!\n");
                    new Result(ticket.ticketName, countOfRightAnswers, ticket.ticketAnswers, studentAnswers, ticket.ticketRightAnswers).setVisible(true);
                }
            }
        });
    }

    private void updateWindow(ArrayList<String> questions, String[][] answers, ArrayList<String> rightAnswers) {
        Question.setText(questions.get(numberOfQuestion));
        List<String> randomAnswers = Arrays.asList(answers[numberOfQuestion]);
        Collections.shuffle(randomAnswers);
        Answer1RButton.setText(randomAnswers.get(0));
        Answer2RButton.setText(randomAnswers.get(1));
        Answer3RButton.setText(randomAnswers.get(2));
        Answer4RButton.setText(randomAnswers.get(3));
        numberOfQuestion++;
    }
}
