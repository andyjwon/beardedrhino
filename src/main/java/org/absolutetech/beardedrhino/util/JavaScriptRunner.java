package org.absolutetech.beardedrhino.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.tools.shell.Global;

public class JavaScriptRunner {

    // Run in interpretive mode to avoid 64kb inputStream limit
    private static final Integer RHINO_CONTEXT_OPTIMIZATION_LEVEL = -1;
    private static final Integer RHINO_LANGUAGE_VERSION = Context.VERSION_1_5;

    private static final String JAVASCRIPT_SOURCE_PATH = "../src/main/resources/javascript/vendor/";

    private static final String ENV_JS_FILE = "env.rhino.1.2.js";
    private static final String JQUERY_FILE = "jquery-1.11.1.min.js";

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

    // TODO: Refactor this out
    public static JavaScriptRunner createTestClass() {
        JavaScriptRunner jsRunner = new JavaScriptRunner();
        jsRunner.initializeJavaScriptFile("", JavaScriptRunner.class.getResource("/javascript/vendor/" + ENV_JS_FILE).getFile());
        jsRunner.initializeJavaScriptFile("", JavaScriptRunner.class.getResource("/javascript/vendor/" + JQUERY_FILE).getFile());
        return jsRunner;
    }

    public static JavaScriptRunner createScraper() {
        JavaScriptRunner jsRunner = new JavaScriptRunner();
        jsRunner.initializeEnvJs();
        jsRunner.initializeJQuery();
        return jsRunner;
    }

    public void initializeJQuery() {
        initializeJavaScriptFile(JAVASCRIPT_SOURCE_PATH, JQUERY_FILE);
    }

    public void initializeEnvJs() {
        initializeJavaScriptFile(JAVASCRIPT_SOURCE_PATH, ENV_JS_FILE);
    }

    public String executeJavaScriptLine(String javaScriptLine) {
        try {
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

    private void initializeJavaScriptFile(String path, String fileName) {
        try {
            FileInputStream fs = new FileInputStream(path + fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            cx.evaluateReader(scope, br, fileName, lineNumber++, null);
            br.close();
        } catch (IOException e) {
            System.out.println("something went wrong Jack...");
            e.printStackTrace();
        }
    }

}
