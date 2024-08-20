package io.sridhar.graph;


import io.sridhar.graph.component.Component;

import java.nio.file.Path;

/**
 * Assessment Main Class
 * @author Sridhar.Ramasamy
 * @version 0.0.1
 */

public class Main {
    public static void main(String[] args) {

        System.out.println("Argument count: " + args.length);
        if(args.length != 1) {
            throw new RuntimeException("Invalid argument count, Need exactly one json file input to read from");
        }
        Component component = new Component(Path.of(args[0]).toUri());
        component.processData();
    }
}