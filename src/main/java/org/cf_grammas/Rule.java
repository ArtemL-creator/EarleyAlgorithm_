package org.cf_grammas;

import java.util.Objects;

public class Rule {
    private String first;
    private String second;

    public Rule(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst(){
        return first;
    }

    public String getSecond(){
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return first.equals(rule.first) && second.equals(rule.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
