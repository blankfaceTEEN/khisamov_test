package com.iskanderkhisamov;

import java.io.*;
import java.util.ArrayList;

public class Controller {
    final static byte COUNT_QUESTIONS = 20;
    final static byte COUNT_ANSWERS = COUNT_QUESTIONS * 4;
    static ArrayList<String> questions = new ArrayList<>();
    static ArrayList<String> firstAnswers = new ArrayList<>();
    static String[][] answers = new String[COUNT_QUESTIONS][4];
    static ArrayList<String> rightAnswers = new ArrayList<>();

    public static Ticket init(String name) {
        System.out.println("!!!Инициализация!!!\n");
        System.out.println("Фамилия: " + name + "\n");
        System.out.print("Вопросы: ");
        try {
            File file = new File("src/com/iskanderkhisamov/questions.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                questions.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(questions + "\n");
        System.out.print("Ответы: [");
        try {
            File file = new File("src/com/iskanderkhisamov/answers.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                firstAnswers.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < firstAnswers.size(); i++) {
            byte j = 0;
            System.out.print("[");
            for (String atom : firstAnswers.get(i).split(" / ")) {
                answers[i][j] = atom;
                if (j != 3)
                    System.out.print(answers[i][j] + ", ");
                else
                    System.out.print(answers[i][j]);
                j++;
            }
            System.out.print("], ");
        }
        System.out.println("]\n");
        System.out.print("Правильные ответы: [");
        for(int i = 0; i < answers.length; i++) {
            rightAnswers.add(answers[i][0]);
            if (i != answers.length - 1)
                System.out.print(rightAnswers.get(i) + ", ");
            else
                System.out.print(rightAnswers.get(i));
        }
        System.out.println("]\n");

        Ticket ticket = new Ticket(questions, answers, rightAnswers, name);

        return ticket;
    }
}
