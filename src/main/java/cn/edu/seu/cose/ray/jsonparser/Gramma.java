/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonparser;

import cn.edu.seu.cose.ray.jsonlexer.Token;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class Gramma {

    private String name;
    private Map<String, Derivation> derivations;

    public Gramma(String name) {
        this.name = name;
        derivations = new HashMap<String, Derivation>();
    }

    public String getName() {
        return name;
    }

    public void addDerivation(String tokenName, Derivation derivation) {
        derivations.put(tokenName, derivation);
    }

    public Derivation getDerivation(String tokenName) throws DerivationException {
        if (!derivations.containsKey(tokenName)) {
            throw new DerivationException(this, tokenName);
        }
        return derivations.get(tokenName);
    }
}
