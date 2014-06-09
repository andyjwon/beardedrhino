package org.absolutetech.beardedrhino.util;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;

import org.mozilla.javascript.tools.shell.Global;
import org.mozilla.javascript.tools.shell.Main;

public class JavaScriptRunner {

    private Integer lineNumber = 1;

    private Context cx = ContextFactory.getGlobal().enter();

    private Global scope = new Global(cx);

    public void initializeEnvJsAndJQuery() {
        cx.setOptimizationLevel(-1);
        cx.setLanguageVersion(Context.VERSION_1_5);
        Main.processFile(cx, scope, "../src/main/javascript/vendor/env.rhino.1.2.js");
        Main.processFile(cx, scope, "../src/main/javascript/vendor/jquery-1.11.1.js");
    }

    public String executeJavaScriptLine(String javaScriptLine) {
        try {
            Object obj2 = cx.evaluateString(scope, "var ver = $.fn.jquery; ver;", "versioncheck", lineNumber++, null);
            System.out.println("jQuery version: " + obj2.toString());
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
