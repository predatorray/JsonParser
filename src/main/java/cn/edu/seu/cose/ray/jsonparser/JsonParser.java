/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonparser;

import cn.edu.seu.cose.ray.jsonlexer.JsonLexer;
import cn.edu.seu.cose.ray.jsonlexer.Token;
import cn.edu.seu.cose.ray.jsonlexer.token.NullToken;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class JsonParser implements Iterator<Derivation> {

    private JsonLexer lexer;
    private Gramma startWith;
    private Queue<Gramma> grammaQueue;

    public JsonParser(JsonLexer lexer) {
        grammaQueue = new LinkedList<Gramma>();
        JsonGrammaBuilder builder = new JsonGrammaBuilder();
        startWith = builder.buildGramma();
        grammaQueue.add(startWith);
        this.lexer = lexer;
    }

    public boolean hasNext() {
        return lexer.hasNext() || !grammaQueue.isEmpty();
    }

    public Derivation next() {
        Gramma g = grammaQueue.poll();
        Token token = getNextValidToken();
        try {
            Derivation der = g.getDerivation(token.getName());
            for (Gramma subG : der.getSubGrammas()) {
                grammaQueue.add(subG);
            }
            String[] tokenStrs = der.getTokenStrings();
            return der;
        } catch (DerivationException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Token getNextValidToken() {
        Token token = lexer.next();
        while (token instanceof NullToken && lexer.hasNext()) {
            token = lexer.next();
        }
        return token;
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
