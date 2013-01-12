/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonparser;

import java.util.List;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class Derivation {

    private List<Gramma> subGrammas;
    private String format;

    public Derivation(List<Gramma> subGrammas, String format) {
        this.subGrammas = subGrammas;
        this.format = format;
    }

    public List<Gramma> getSubGrammas() {
        return subGrammas;
    }

    public String[] getTokenStrings() {
        return format.split("%");
    }

    @Override
    public String toString() {
        String[] strs = format.split("%");
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < strs.length; ++index) {
            boolean last = (index == (strs.length - 1));
            builder.append(strs[index]);
            if (last) {
                continue;
            }
            builder.append(subGrammas.get(index).getName());
        }
        return builder.toString();
    }
}
