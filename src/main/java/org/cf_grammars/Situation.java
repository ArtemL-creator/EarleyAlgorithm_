package org.cf_grammars;

import java.util.Objects;

public class Situation {
    private int separatingCharacter;
    private Rule rule;
    private int numberOfSet;

    public Situation(int separatingCharacter, Rule rule, int numberOfSet) {
        this.separatingCharacter = separatingCharacter;
        this.rule = rule;
        this.numberOfSet = numberOfSet;
    }

    public int getSeparatingCharacter(){
        return separatingCharacter;
    }

    public Rule getRule(){
        return rule;
    }

    public int getNumberOfSet(){
        return numberOfSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Situation situation = (Situation) o;
        return rule.equals(situation.rule) && separatingCharacter == situation.separatingCharacter && numberOfSet == situation.numberOfSet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rule, numberOfSet, separatingCharacter);
    }
}
