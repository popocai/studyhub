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

public class AdditionalVehicleStatus implements Serializable {

    private static final long          serialVersionUID = 1449646923587119002L;


    private DrivingSafetyStatus        drivingSafetyStatus;


    private ClimateStatus              climateStatus;

    private DrivingBehaviourStatus     drivingBehaviourStatus;

    public DrivingSafetyStatus getDrivingSafetyStatus() {
        return drivingSafetyStatus;
    }

    public void setDrivingSafetyStatus(DrivingSafetyStatus drivingSafetyStatus) {
        this.drivingSafetyStatus = drivingSafetyStatus;
    }


    public ClimateStatus getClimateStatus() {
        return climateStatus;
    }

    public void setClimateStatus(ClimateStatus climateStatus) {
        this.climateStatus = climateStatus;
    }

    public DrivingBehaviourStatus getDrivingBehaviourStatus() {
        return drivingBehaviourStatus;
    }

    public void setDrivingBehaviourStatus(DrivingBehaviourStatus drivingBehaviourStatus) {
        this.drivingBehaviourStatus = drivingBehaviourStatus;
    }
}
