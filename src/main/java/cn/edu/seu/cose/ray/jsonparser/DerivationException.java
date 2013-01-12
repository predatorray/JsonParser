/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonparser;

import cn.edu.seu.cose.ray.jsonlexer.Token;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class DerivationException extends Exception {

    private Gramma gramma;
    private String tokenName;

    public DerivationException(DerivationException cause) {
        super(cause);
    }

    public DerivationException(Gramma gramma, String tokenName) {
        super(
            new StringBuilder().append("Token ").append(tokenName)
                .append(" is not capable for ")
                .append("Gramma ").append(gramma.getName())
                .toString()
        );
        this.gramma = gramma;
        this.tokenName = tokenName;
    }

    public Gramma getGramma() {
        return gramma;
    }

    public String getTokenName() {
        return tokenName;
    }
}
