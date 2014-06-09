package org.absolutetech.beardedrhino.util;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;

import org.mozilla.javascript.tools.shell.Global;
import org.mozilla.javascript.tools.shell.Main;

public class JavaScriptRunner {

    // Minimal optimization for runtime performance
    private static final Integer RHINO_CONTEXT_OPTIMIZATION_LEVEL = 1;
    private static final Integer RHINO_LANGUAGE_VERSION = Context.VERSION_1_5;

    private static final String ENV_JS_FILEPATH = "../src/main/resources/javascript/vendor/env.rhino.1.2.js";
    private static final String JQUERY_FILEPATH = "../src/main/resources/javascript/vendor/jquery-1.11.1.js";

    private Integer lineNumber;
    private Context cx;
    private Global scope;

    public JavaScriptRunner() {
        lineNumber = 1;

        cx = ContextFactory.getGlobal().enterContext();
        scope = new Global(cx);

        cx.setOptimizationLevel(RHINO_CONTEXT_OPTIMIZATION_LEVEL);
        cx.setLanguageVersion(RHINO_LANGUAGE_VERSION);
    }

    public void initializeEnvJsAndJQuery() {
        initializeJavaScriptFile(ENV_JS_FILEPATH);
        initializeJavaScriptFile(JQUERY_FILEPATH);
    }

    public String executeJavaScriptLine(String javaScriptLine) {
        try {
            Object jQueryVersion = cx.evaluateString(scope, "var ver = $.fn.jquery; ver;", "versioncheck", lineNumber++, null);
            System.out.println("jQuery version: " + jQueryVersion.toString());
            Object obj = cx.evaluateString(scope, javaScriptLine, "RunLine", lineNumber++, null);
            System.out.println("Object: " + obj.toString());
            return obj.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Boolean exitContext() {
        if (Context.getCurrentContext() == null) {
            return false;
        }

        Context.exit();
        return true;
    }

    private void initializeJavaScriptFile(String path) {
        Main.processFile(cx, scope, path);
    }

}
