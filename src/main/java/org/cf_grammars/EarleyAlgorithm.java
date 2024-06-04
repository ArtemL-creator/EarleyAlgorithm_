package org.cf_grammars;

import java.util.*;

public class EarleyAlgorithm {
    private String word;
    private List<Rule> G;
    private Set<Situation>[] D;

    public EarleyAlgorithm(List<Rule> grammar, String word) {
        this.word = word;
        this.G = grammar;
        this.D = new HashSet[word.length() + 1];
        for (int i = 0; i <= word.length(); i++) {
            this.D[i] = new HashSet<>();
        }
    }

    public boolean earley() {
        D[0].add(new Situation(0, new Rule("S1", "S"), 0));
        for (int j = 0; j <= word.length(); j++) {
            scanningStage(j);
            int previousSize, currentSize;
            do {
                previousSize = D[j].size();
                completionStage(j);
                predictionStage(j);
                currentSize = D[j].size();
            } while (previousSize != currentSize);
        }

        if (D[word.length()].contains(new Situation(1, new Rule("S1", "S"), 0))) {
            return true;
        } else {
            return false;
        }
    }

    public void scanningStage(int j) {
        if (j == 0) {
            return;
        }
        for (Situation situation : D[j - 1]) {
                if (situation.getRule().getRight().charAt(situation.getSeparatingCharacter()) == word.charAt(j - 1)) {
                    D[j].add(new Situation(situation.getSeparatingCharacter() + 1, situation.getRule(), situation.getNumberOfSet()));
                }
        }
    }

    public void completionStage(int j) {
        List<Situation> situationsToAdd = new ArrayList<>();
        for (Situation situation : D[j]) {
            if (situation.getSeparatingCharacter() != situation.getRule().getRight().length()) {
                continue;
            }
            for (Situation secondSituation : D[situation.getNumberOfSet()]) {
                    if (situation.getRule().getLeft().equals(String.valueOf(secondSituation.getRule().getRight().charAt(secondSituation.getSeparatingCharacter())))) {
                        Situation s = new Situation(secondSituation.getSeparatingCharacter() + 1, secondSituation.getRule(), secondSituation.getNumberOfSet());
                        situationsToAdd.add(s);
                    }
            }
        }
        for (Situation s : situationsToAdd) {
            D[j].add(s);
        }
    }

    public void predictionStage(int j) {
        List<Situation> situationsToAdd = new ArrayList<>();
        for (Situation situation : D[j]) {
            if (situation.getSeparatingCharacter() < situation.getRule().getRight().length()) {
                for (Rule rule : G) {
                        if (rule.getLeft().equals(String.valueOf(situation.getRule().getRight().charAt(situation.getSeparatingCharacter())))) {
                            situationsToAdd.add(new Situation(0, rule, j));
                        }
                }
            }
        }
        for (Situation s : situationsToAdd) {
            D[j].add(s);
        }
    }
}
