package org.cf_grammars;

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


            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            System.out.println("Грамматика должна быть записана в файл" + "\u001B[32m" + "[\"src/main/resources/cs_grammar.txt\"]" + "\u001B[0m");
            System.out.println("Правила записи следующие:");
            System.out.println("Строка 1: Вводится количество правил грамматики " + "\u001B[32m" + "n" + "\u001B[0m");
            System.out.println("Строки 2...(n+1): Вводятся правила");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            System.out.println("Грамматика: ");
            for (Rule g : grammar) {
                System.out.println(g.getLeft() + "->" + g.getRight());
            }

            System.out.println("Введите слово: ");
            Scanner scanner = new Scanner(System.in);
            String word = scanner.next();
            EarleyAlgorithm algEar = new EarleyAlgorithm(grammar, word);

            System.out.println(algEar.earley());

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

    }
}
