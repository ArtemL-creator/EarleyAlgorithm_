package org.cf_grammas;

import java.util.*;

public class Earley {
    private String word;
    private List<Rule> grammar;
    private Set<Situation>[] D;

    public Earley(List<Rule> grammar, String word) {
        this.word = word;
        this.grammar = grammar;
        this.D = new HashSet[word.length() + 1];
        for (int i = 0; i <= word.length(); i++) {
            this.D[i] = new HashSet<>();
        }
        this.D[0].add(new Situation(new Rule("S1", "S"), 0, 0));
    }

    public void scan(int j) {
        if (j == 0) {
            return;
        }
        for (Situation situation : D[j - 1]) {
            try {
                if (situation.getRule().getSecond().charAt(situation.getDivider()) == word.charAt(j - 1)) {
                    D[j].add(new Situation(situation.getRule(), situation.getIndex(), situation.getDivider() + 1));
                }
            } catch (Exception e) {
                // Handle the exception
            }
        }
    }

    public void predict(int j) {
        List<Situation> situationsToAdd = new ArrayList<>();
        for (Situation situation : D[j]) {
            if (situation.getDivider() < situation.getRule().getSecond().length()) {
                for (Rule rule : grammar) {
                    try {
                        if (rule.getFirst().equals(String.valueOf(situation.getRule().getSecond().charAt(situation.getDivider())))) {
                            situationsToAdd.add(new Situation(rule, j, 0));
                        }
                    } catch (Exception e) {
                        // Handle the exception
                    }
                }
            }
        }
        for (Situation s : situationsToAdd) {
            D[j].add(s);
        }
    }

    public void complete(int j) {
        List<Situation> situationsToAdd = new ArrayList<>();
        for (Situation situation : D[j]) {
            if (situation.getDivider() != situation.getRule().getSecond().length()) {
                continue;
            }
            for (Situation secondSituation : D[situation.getIndex()]) {
                try {
                    if (situation.getRule().getFirst().equals(String.valueOf(secondSituation.getRule().getSecond().charAt(secondSituation.getDivider())))) {
                        Situation s = new Situation(secondSituation.getRule(), secondSituation.getIndex(), secondSituation.getDivider() + 1);
                        situationsToAdd.add(s);
                    }
                } catch (Exception e) {
                    // Handle the exception
                }
            }
        }
        for (Situation s : situationsToAdd) {
            D[j].add(s);
        }
    }

    public String earley() {
        for (int i = 0; i <= word.length(); i++) {
            scan(i);
            int oldLength = D[i].size();
            predict(i);
            complete(i);
            int newLength = D[i].size();
            while (newLength != oldLength) {
                oldLength = newLength;
                predict(i);
                complete(i);
                newLength = D[i].size();
            }
        }

        if (D[word.length()].contains(new Situation(new Rule("S1", "S"), 0, 1))) {
            return "YES";
        } else {
            return "NO";
        }
    }
}
