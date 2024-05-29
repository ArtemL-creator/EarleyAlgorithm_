package org.cf_grammas;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Rule> grammar = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] s = scanner.next().split("->");
            grammar.add(new Rule(s[0], s[1]));
        }

        String word = scanner.next();
        Earley algo = new Earley(grammar, word);
        System.out.println(algo.earley());
    }
}
