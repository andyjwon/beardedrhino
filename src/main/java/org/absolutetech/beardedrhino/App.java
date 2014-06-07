package org.absolutetech.beardedrhino;

import org.absolutetech.beardedrhino.util.JavaScriptRunner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        JavaScriptRunner jsRunner = new JavaScriptRunner();
        String js = "var s = 'Hello, Rhino!'; s;";
        jsRunner.executeJavaScriptLine(js);
        System.out.println( "Hello World!" );
    }
}
