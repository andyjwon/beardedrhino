package org.absolutetech.beardedrhino.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JavaScriptRunnerTest {

    JavaScriptRunner jsRunner;

    @Before
    public void setup() {
        jsRunner = JavaScriptRunner.createTestClass();
    }

    @After
    public void teardown() {
        jsRunner.exitContext();
    }

    @Test
    public void jQueryInitializes() {
        String jQueryVersionQuery = "var ver = $.fn.jquery; ver;";

        String actualJQueryVersion = jsRunner.executeJavaScriptLine(jQueryVersionQuery);
        String expectedJQueryVersion = "1.11.1";

        assertThat(actualJQueryVersion, is(expectedJQueryVersion));
    }
}
