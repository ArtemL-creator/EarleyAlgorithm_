package org.cf_grammas;

import java.util.Objects;

public class Situation {
    private Rule rule;
    private int index;
    private int divider;

    public Situation(Rule rule, int index, int divider) {
        this.rule = rule;
        this.index = index;
        this.divider = divider;
    }

    public Rule getRule(){
        return rule;
    }

    public int getIndex(){
        return index;
    }

    public int getDivider(){
        return divider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Situation situation = (Situation) o;
        return index == situation.index &&
                divider == situation.divider &&
                rule.equals(situation.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rule, index, divider);
    }
}
