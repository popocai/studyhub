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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestObjectInMap {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

        configuration.setClassLoaderForTemplateLoading(Test.class.getClassLoader(), "");

        Template t = configuration.getTemplate("testObjList.flt");

        Map<String, Object> root = new HashMap<>();
        // Data
        List<TestObject> objs = new ArrayList<>();

        TestObject obj = new TestObject();
        obj.setAge(10);
        obj.setName("Young");
        objs.add(obj);

        obj = new TestObject();
        obj.setAge(20);
        obj.setName("Old");
        objs.add(obj);

        Map<String, List<Double>> constraint = new HashMap<>();

        List<Double> doubles = new ArrayList<>();
        doubles.add(12D);
        doubles.add(20D);

        constraint.put("age", doubles);

        root.put("list", objs);
        root.put("constraint", constraint);

        t.process(root, new OutputStreamWriter(System.out));

    }
}
