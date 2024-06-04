package org.cf_grammars;

import java.util.Objects;

public class Rule {
    private String left;
    private String right;

    public Rule(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft(){
        return left;
    }

    public String getRight(){
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return left.equals(rule.left) && right.equals(rule.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
