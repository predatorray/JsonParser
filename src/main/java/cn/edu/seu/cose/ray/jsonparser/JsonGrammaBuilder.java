/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonparser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class JsonGrammaBuilder {

    public JsonGrammaBuilder() {

    }

    public Gramma buildGramma() {
        // build grammas - the rows of LL(1) table
        Gramma object = new Gramma("Object");
        Gramma pairs = new Gramma("Pairs");
        Gramma a = new Gramma("A");
        Gramma pair = new Gramma("Pair");
        Gramma value = new Gramma("Value");
        Gramma array = new Gramma("Array");
        Gramma csv = new Gramma("Csv");
        Gramma b = new Gramma("B");

        // build available token names - the columns of LL(1) table
        String colon = "COLON";
        String comma = "COMMA";
        String leftBrace = "LEFT_BRACE";
        String leftSquareBracket = "LEFT_SQUARE_BRACKET";
        String number = "NUMBER";
        String rightBrace = "RIGHT_BRACE";
        String rightSquareBracket = "RIGHT_SQUARE_BRACKET";
        String string = "STRING";

        // build derivations. For more details, view the report
        // the % stands for a reserved gramma symbol. For instance,
        // % in {%} stands for Pairs in Object->{Pairs}
        // _ below in the comments stands for the end of the gramma
        Derivation d1 = new Derivation(getGrammaList(pairs), "{%}"); // Object -> {Pairs}
        Derivation d2 = new Derivation(getGrammaList(pair, a), "%%");  // Pairs -> Pair A
        Derivation d3 = new Derivation(getGrammaList(), ""); // A -> _
        Derivation d4 = new Derivation(getGrammaList(pairs), ",%"); // A -> ,Pairs
        Derivation d5 = new Derivation(getGrammaList(value), "string:%"); // Pairs -> string:Value
        Derivation d6 = new Derivation(getGrammaList(object), "%"); // Value -> Object
        Derivation d7 = new Derivation(getGrammaList(), "string"); // Value -> string
        Derivation d8 = new Derivation(getGrammaList(), "number"); // value -> number
        Derivation d9 = new Derivation(getGrammaList(array), "%"); // value -> Array
        Derivation d10 = new Derivation(getGrammaList(csv), "[%]"); // Array -> [Csv]
        Derivation d11 = new Derivation(getGrammaList(b), "value%"); // Csv -> Value B
        Derivation d12 = new Derivation(getGrammaList(csv), ",%"); // B -> ,Csv
        Derivation d13 = new Derivation(getGrammaList(), ""); // B -> _

        // add the derivations to the grammas
        object.addDerivation(leftBrace, d1);
        pairs.addDerivation(string, d2);
        a.addDerivation(rightBrace, d3);
        a.addDerivation(comma, d4);
        pair.addDerivation(string, d5);
        value.addDerivation(leftBrace, d6);
        value.addDerivation(string, d7);
        value.addDerivation(number, d8);
        value.addDerivation(leftSquareBracket, d9);
        array.addDerivation(leftSquareBracket, d10);
        csv.addDerivation(leftBrace, d11);
        csv.addDerivation(string, d11);
        csv.addDerivation(number, d11);
        csv.addDerivation(leftSquareBracket, d11);
        b.addDerivation(comma, d12);
        b.addDerivation(rightSquareBracket, d13);

        // return the start gramma object
        return object;
    }

    private static List<Gramma> getGrammaList(Gramma ...gs) {
        List<Gramma> list = new LinkedList<Gramma>();
        list.addAll(Arrays.asList(gs));
        return list;
    }
}
