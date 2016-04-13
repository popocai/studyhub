/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package org.pat.freemarker;

import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class Test {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

        configuration.setClassLoaderForTemplateLoading(Test.class.getClassLoader(), "");
        // configuration.setClassForTemplateLoading(Test.class, "");

        // configuration
        // .setDirectoryForTemplateLoading(new File("c:/Drive_Work/Workspaces/studyhub/Test/src/main/resources/"));
        // Template t = configuration.getTemplate("testObj.flt");

        // Template t2 = configuration.getTemplate("test.flt", Locale.SIMPLIFIED_CHINESE);

        Map<String, Object> root = new HashMap<>();

        // List<TestObject> tests = new ArrayList<>();
        // TestObject obj = new TestObject();
        // obj.setAge(10);
        // obj.setName("Young");
        // tests.add(obj);
        // obj = new TestObject();
        // obj.setAge(20);
        // obj.setName("Old");
        //
        // tests.add(obj);

        root.put("priceA", 32);
        root.put("priceB", 22);

        // t.process(tests, new OutputStreamWriter(System.out));
        // t2.process(root, new OutputStreamWriter(System.out));
        //
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        String template = "<#if priceA < priceB> Pythons are cheaper than elephants today. <#else> Pythons are not          cheaper than elephants today.</#if> ";
        String template2 = "<#if priceA < priceB> Pythons are cheaper than elephants today. <#else> AAAAAAAAAAAAA</#if> ";

        String template_zh_CN = "<#if priceA < priceB> Pythons are cheaper than elephants today. <#else> 好贵</#if> ";
        stringTemplateLoader.putTemplate("test", template);
        stringTemplateLoader.putTemplate("test_zh_CN", template_zh_CN);

        configuration.setTemplateLoader(stringTemplateLoader);
        Template t3 = configuration.getTemplate("test");
        Template t4 = configuration.getTemplate("test", Locale.SIMPLIFIED_CHINESE);
        t3.process(root, new OutputStreamWriter(System.out));
        System.out.println();
        t4.process(root, new OutputStreamWriter(System.out));
        System.out.println();

        stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("test", template2);
        configuration.setTemplateLoader(stringTemplateLoader);
        t3 = configuration.getTemplate("test");
        t3.process(root, new OutputStreamWriter(System.out));
        System.out.println();
        t4 = configuration.getTemplate("test", Locale.SIMPLIFIED_CHINESE);
        t4.process(root, new OutputStreamWriter(System.out));

    }
}
