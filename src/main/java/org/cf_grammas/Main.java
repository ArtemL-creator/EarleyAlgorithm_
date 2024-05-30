package org.cf_grammas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        File file = new File("src/main/resources/cs_grammar.txt");

        try {
            Scanner fileScanner = new Scanner(file);

            int n = fileScanner.nextInt();
            ArrayList<Rule> grammar = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] s = fileScanner.next().split("->");
                if (s.length == 1) {
                    grammar.add(new Rule(s[0], ""));
                } else {
                    grammar.add(new Rule(s[0], s[1]));
                }
            }

            String word = fileScanner.next();
            Earley algo = new Earley(grammar, word);

            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            System.out.println("Грамматика должна быть записана в файл" + "\u001B[32m" + "[\"src/main/resources/cs_grammar.txt\"]" + "\u001B[0m");
            System.out.println("Правила записи следующие:");
            System.out.println("Строка 1: Вводится количество правил грамматики "  + "\u001B[32m" + "n" + "\u001B[0m");
            System.out.println("Строки 2...(n+1): Вводятся правила");
            System.out.println("Строка n+2: Вводится слово");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            System.out.println(algo.earley());

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

/*        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Rule> grammar = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] s = scanner.next().split("->");
            grammar.add(new Rule(s[0], s[1]));
        }

        String word = scanner.next();
        Earley algo = new Earley(grammar, word);
        System.out.println(algo.earley());*/
    }
}
