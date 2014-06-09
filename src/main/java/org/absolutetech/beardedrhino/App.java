package org.absolutetech.beardedrhino;

import org.absolutetech.beardedrhino.util.JavaScriptRunner;

/**
 * Hello world... in JavaScript!
 */
public class App
{
    public static void main( String[] args )
    {
        JavaScriptRunner jsRunner = new JavaScriptRunner();
        String jsJQueryTest = "var ver = $.fn.jquery; ver;";
        String jsHelloWorld = "var s = 'Hello, Rhino!'; s;";

        jsRunner.initializeEnvJsAndJQuery();

        jsRunner.executeJavaScriptLine(jsJQueryTest);
        String helloWorldResult = jsRunner.executeJavaScriptLine(jsHelloWorld);
        System.out.println( helloWorldResult );
        jsRunner.exitContext();
    }
}
