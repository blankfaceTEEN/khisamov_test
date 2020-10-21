package com.iskanderkhisamov;

import java.util.ArrayList;

public class Ticket {
    final int count = 5;
    String ticketName;
    ArrayList<String> ticketQuestions = new ArrayList<>();
    String[][] ticketAnswers = new String[count][4];
    ArrayList<String> ticketRightAnswers = new ArrayList<>();

    public Ticket(ArrayList<String> questions, String[][] answers, ArrayList<String> rightAnswers, String name) {
        System.out.println("!!!Инициализация БИЛЕТА!!!\n");
        int i = 0;
        while(ticketQuestions.size() < 5) {
            int number = (int) (Math.random() * 19);
            String question = questions.get(number);
            boolean isThere = false;
            for (String atom : ticketQuestions) {
                if (atom.equals(question))
                    isThere = true;
            }
            if (isThere) {
                continue;
            } else {
                ticketQuestions.add(question);
                ticketAnswers[i] = answers[number];
                i++;
                System.out.println(question);
                System.out.println(answers[number][0] + "\n");
            }
        }
        ticketRightAnswers = rightAnswers;
        ticketName = name;
    }
}
