/*------------------------------------------------------------------------------
* COPYRIGHT Ericsson 2015
*
* The copyright to the computer program(s) herein is the property of
* Ericsson Inc. The programs may be used and/or copied only with written
* permission from Ericsson Inc. or in accordance with the terms and
* conditions stipulated in the agreement/contract under which the
* program(s) have been supplied.
*----------------------------------------------------------------------------*/
package org.pat.freemarker.entity;

import java.io.Serializable;

public class DrivingBehaviourStatus implements Serializable {

    private static final long serialVersionUID = 1449646923622143944L;

    private Boolean           brakePedalDepressed;

    private Integer           engineSpeed;

    private Integer           transimissionGearPostion;

    private Integer           cruiseControlStatus;

    public Boolean getBrakePedalDepressed() {
        return brakePedalDepressed;
    }

    public void setBrakePedalDepressed(Boolean brakePedalDepressed) {
        this.brakePedalDepressed = brakePedalDepressed;
    }

    public Integer getEngineSpeed() {
        return engineSpeed;
    }

    public void setEngineSpeed(Integer engineSpeed) {
        this.engineSpeed = engineSpeed;
    }

    public Integer getTransimissionGearPostion() {
        return transimissionGearPostion;
    }

    public void setTransimissionGearPostion(Integer transimissionGearPostion) {
        this.transimissionGearPostion = transimissionGearPostion;
    }

    public Integer getCruiseControlStatus() {
        return cruiseControlStatus;
    }

    public void setCruiseControlStatus(Integer cruiseControlStatus) {
        this.cruiseControlStatus = cruiseControlStatus;
    }

}
