package org.ai.order.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;

/**
 * Created by hua.ai on 2016/4/17.
 */
public class MustacheTest {

    public static void main(String args[]) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache layout = mf.compile("mustache/hello.mustache");
        StringWriter sw = new StringWriter();
        layout.execute(sw, Collections.singletonMap("message", "Ai Hua")).flush();
        Mustache hello = mf.compile("mustache/layout.mustache");
        hello.execute(new PrintWriter(System.out), Collections.singletonMap("content", sw.getBuffer().toString())).flush();
    }

}
