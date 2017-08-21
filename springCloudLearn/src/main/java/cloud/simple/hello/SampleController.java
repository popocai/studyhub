/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package cloud.simple.hello;


import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @ResponseBody
    @RequestMapping(value = "/")
    public String home() {
        return "HelloWorld1!";
    }

    @RequestMapping(value = "/h2")
    public String home2() {
        return "Hello World2!";
    }

    @RequestMapping(value = "/obj")
    public SampleObject json() {
        SampleObject object = new SampleObject();
        object.setId(1);
        object.setName("tes111111t222");
        object.setBirthday(new Date());
        return object;
    }


}