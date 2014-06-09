package org.absolutetech.beardedrhino;

import org.absolutetech.beardedrhino.util.JavaScriptRunner;

/**
 * Hello world... in JavaScript!
 */
public class App {
    public static void main( String[] args ) {
        JavaScriptRunner jsRunner = JavaScriptRunner.createScraper();
        String jsHelloWorld = "var s = 'Hello, Rhino!'; s;";

        // Load sample HTML
        jsRunner.executeJavaScriptLine("window.location = '../src/main/resources/html/sample.html';");

        // Get from sample HTML
        jsRunner.executeJavaScriptLine("var samp = $('#sample').text(); samp;");

        String helloWorldResult = jsRunner.executeJavaScriptLine(jsHelloWorld);
        System.out.println( helloWorldResult );
        jsRunner.exitContext();
    }
}
