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

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestObject {
    private String name;

    private int    age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

        configuration.setClassLoaderForTemplateLoading(Test.class.getClassLoader(), "");

        Template t = configuration.getTemplate("testObj.flt");

        TestObject obj = new TestObject();
        obj.setAge(10);
        obj.setName("Young");

        t.process(obj, new OutputStreamWriter(System.out));

    }
}
