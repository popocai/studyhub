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

public class DrivingSafetyStatus implements Serializable {

    private static final long serialVersionUID = 1449646923599153622L;


    private Integer           vehicleAlarm;

    private Integer           doorOpenStatusDriver;

    private Integer           doorOpenStatusPassenger;

    private Integer           doorOpenStatusDriverRear;

    private Integer           doorOpenStatusPassengerRear;

    private Integer           doorLockStatusDriver;

    private Integer           doorLockStatusPassenger;

    private Integer           doorLockStatusDriverRear;

    private Integer           doorLockStatusPassengerRear;

    private Integer           trunkOpenStatus;

    private Integer           engineHoodOpenStatus;

    private Integer           centralLockingStatus;

    private Boolean           seatBeltStatusDriver;

    private Boolean           seatBeltStatusPassenger;

    private Boolean           seatBeltStatusDriverRear;

    private Boolean           seatBeltStatusPassengerRear;

    private Integer           handBrakeStatus;

    private Integer           electricParkBrakeStatus;

    public Integer getVehicleAlarm() {
        return vehicleAlarm;
    }

    public void setVehicleAlarm(Integer vehicleAlarm) {
        this.vehicleAlarm = vehicleAlarm;
    }

    public Integer getDoorOpenStatusDriver() {
        return doorOpenStatusDriver;
    }

    public void setDoorOpenStatusDriver(Integer doorOpenStatusDriver) {
        this.doorOpenStatusDriver = doorOpenStatusDriver;
    }

    public Integer getDoorOpenStatusPassenger() {
        return doorOpenStatusPassenger;
    }

    public void setDoorOpenStatusPassenger(Integer doorOpenStatusPassenger) {
        this.doorOpenStatusPassenger = doorOpenStatusPassenger;
    }

    public Integer getDoorOpenStatusDriverRear() {
        return doorOpenStatusDriverRear;
    }

    public void setDoorOpenStatusDriverRear(Integer doorOpenStatusDriverRear) {
        this.doorOpenStatusDriverRear = doorOpenStatusDriverRear;
    }

    public Integer getDoorOpenStatusPassengerRear() {
        return doorOpenStatusPassengerRear;
    }

    public void setDoorOpenStatusPassengerRear(Integer doorOpenStatusPassengerRear) {
        this.doorOpenStatusPassengerRear = doorOpenStatusPassengerRear;
    }

    public Integer getDoorLockStatusDriver() {
        return doorLockStatusDriver;
    }

    public void setDoorLockStatusDriver(Integer doorLockStatusDriver) {
        this.doorLockStatusDriver = doorLockStatusDriver;
    }

    public Integer getDoorLockStatusPassenger() {
        return doorLockStatusPassenger;
    }

    public void setDoorLockStatusPassenger(Integer doorLockStatusPassenger) {
        this.doorLockStatusPassenger = doorLockStatusPassenger;
    }

    public Integer getDoorLockStatusDriverRear() {
        return doorLockStatusDriverRear;
    }

    public void setDoorLockStatusDriverRear(Integer doorLockStatusDriverRear) {
        this.doorLockStatusDriverRear = doorLockStatusDriverRear;
    }

    public Integer getDoorLockStatusPassengerRear() {
        return doorLockStatusPassengerRear;
    }

    public void setDoorLockStatusPassengerRear(Integer doorLockStatusPassengerRear) {
        this.doorLockStatusPassengerRear = doorLockStatusPassengerRear;
    }

    public Integer getTrunkOpenStatus() {
        return trunkOpenStatus;
    }

    public void setTrunkOpenStatus(Integer trunkOpenStatus) {
        this.trunkOpenStatus = trunkOpenStatus;
    }

    public Integer getEngineHoodOpenStatus() {
        return engineHoodOpenStatus;
    }

    public void setEngineHoodOpenStatus(Integer engineHoodOpenStatus) {
        this.engineHoodOpenStatus = engineHoodOpenStatus;
    }

    public Integer getCentralLockingStatus() {
        return centralLockingStatus;
    }

    public void setCentralLockingStatus(Integer centralLockingStatus) {
        this.centralLockingStatus = centralLockingStatus;
    }

    public Boolean getSeatBeltStatusDriver() {
        return seatBeltStatusDriver;
    }

    public void setSeatBeltStatusDriver(Boolean seatBeltStatusDriver) {
        this.seatBeltStatusDriver = seatBeltStatusDriver;
    }

    public Boolean getSeatBeltStatusPassenger() {
        return seatBeltStatusPassenger;
    }

    public void setSeatBeltStatusPassenger(Boolean seatBeltStatusPassenger) {
        this.seatBeltStatusPassenger = seatBeltStatusPassenger;
    }

    public Boolean getSeatBeltStatusDriverRear() {
        return seatBeltStatusDriverRear;
    }

    public void setSeatBeltStatusDriverRear(Boolean seatBeltStatusDriverRear) {
        this.seatBeltStatusDriverRear = seatBeltStatusDriverRear;
    }

    public Boolean getSeatBeltStatusPassengerRear() {
        return seatBeltStatusPassengerRear;
    }

    public void setSeatBeltStatusPassengerRear(Boolean seatBeltStatusPassengerRear) {
        this.seatBeltStatusPassengerRear = seatBeltStatusPassengerRear;
    }

    public Integer getHandBrakeStatus() {
        return handBrakeStatus;
    }

    public void setHandBrakeStatus(Integer handBrakeStatus) {
        this.handBrakeStatus = handBrakeStatus;
    }

    public Integer getElectricParkBrakeStatus() {
        return electricParkBrakeStatus;
    }

    public void setElectricParkBrakeStatus(Integer electricParkBrakeStatus) {
        this.electricParkBrakeStatus = electricParkBrakeStatus;
    }
}
