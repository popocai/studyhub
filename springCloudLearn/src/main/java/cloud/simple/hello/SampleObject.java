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

import com.alibaba.fastjson.annotation.JSONField;

public class SampleObject {
    @JSONField(format = "yyyyMMdd HH:mm")
    private Date birthday;

    private int    id;

    private String name;

    public Date getBirthday() {
        return birthday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
