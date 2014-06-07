package org.absolutetech.beardedrhino.util;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Scriptable;

public class JavaScriptRunner {

    private Integer lineNumber = 1;

    public String executeJavaScriptLine(String javaScriptLine) {
        Context cx = ContextFactory.getGlobal().enter();
        try {
            Scriptable scope = cx.initStandardObjects();
            Object obj = cx.evaluateString(scope, javaScriptLine, "RunLine", lineNumber++, null);
            System.out.println("Object: " + obj.toString());
            return obj.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Context.exit();
        }

        return null;
    }

}
