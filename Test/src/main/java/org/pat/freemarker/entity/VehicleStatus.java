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

public class VehicleStatus implements Serializable {

    private static final long       serialVersionUID = 1449646923572152887L;

    private AdditionalVehicleStatus additionalVehicleStatus;


    public AdditionalVehicleStatus getAdditionalVehicleStatus() {
        return additionalVehicleStatus;
    }

    public void setAdditionalVehicleStatus(AdditionalVehicleStatus additionalVehicleStatus) {
        this.additionalVehicleStatus = additionalVehicleStatus;
    }

}
