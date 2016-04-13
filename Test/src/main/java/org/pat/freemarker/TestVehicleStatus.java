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

import org.pat.freemarker.entity.AdditionalVehicleStatus;
import org.pat.freemarker.entity.DrivingSafetyStatus;
import org.pat.freemarker.entity.VehicleStatus;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TestVehicleStatus {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

        configuration.setClassLoaderForTemplateLoading(Test.class.getClassLoader(), "messageTemplate");

        Template t = configuration.getTemplate("STN.flt");

        VehicleStatus vehicleStatus = new VehicleStatus();
        vehicleStatus.setAdditionalVehicleStatus(new AdditionalVehicleStatus());

        vehicleStatus.getAdditionalVehicleStatus().setDrivingSafetyStatus(new DrivingSafetyStatus());
        vehicleStatus.getAdditionalVehicleStatus().getDrivingSafetyStatus().setDoorLockStatusDriver(1);
        vehicleStatus.getAdditionalVehicleStatus().getDrivingSafetyStatus().setDoorOpenStatusDriver(1);

        t.process(vehicleStatus, new OutputStreamWriter(System.out));
    }
}
