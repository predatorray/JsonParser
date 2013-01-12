/*
 * Copyright (C) 2013 rAy <predator.ray@gmail.com>
 */
package cn.edu.seu.cose.ray.jsonparser;

import cn.edu.seu.cose.ray.jsonlexer.JsonLexer;
import cn.edu.seu.cose.ray.jsonlexer.JsonLexerApp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author rAy <predator.ray@gmail.com>
 */
public class JsonParserApp {

    public static void main(String[] args) throws FileNotFoundException,
            IOException {
        boolean givenArgs = (args.length > 0);
        String filePath = givenArgs
                ? args[0]
                : JsonLexerApp.class.getClassLoader()
                    .getResource("cn/edu/seu/cose/ray/jsonparser/sample.json")
                    .getFile();
        String text = readTextFromFile(filePath);
        JsonLexer lexer = new JsonLexer(text);
        JsonParser parser = new JsonParser(lexer);
        printDerivations(parser);
    }

    private static String readTextFromFile(String filePath)
            throws FileNotFoundException, IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File is not found from path: " + filePath);
        }

        BufferedReader in = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        while (true) {
            String str = in.readLine();
            if (str == null) {
                break;
            }
            builder.append(str);
            builder.append("\n");
        }
        return builder.toString();
    }

    private static void printDerivations(JsonParser parser) {
        while (parser.hasNext()) {
            Derivation der = parser.next();
            System.out.println(der);
        }
    }
}
