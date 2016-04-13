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

public class ClimateStatus implements Serializable {

    private static final long serialVersionUID = 1449646923619135959L;

    private Integer           winStatusDriver;

    private Integer           winStatusPassenger;

    private Integer           winStatusDriverRear;

    private Integer           winStatusPassengerRear;

    private Integer           sunroofOpenStatus;

    private Integer           interiorTemp;

    private Integer           exteriorTemp;

    public Integer getWinStatusDriver() {
        return winStatusDriver;
    }

    public void setWinStatusDriver(Integer winStatusDriver) {
        this.winStatusDriver = winStatusDriver;
    }

    public Integer getWinStatusPassenger() {
        return winStatusPassenger;
    }

    public void setWinStatusPassenger(Integer winStatusPassenger) {
        this.winStatusPassenger = winStatusPassenger;
    }

    public Integer getWinStatusDriverRear() {
        return winStatusDriverRear;
    }

    public void setWinStatusDriverRear(Integer winStatusDriverRear) {
        this.winStatusDriverRear = winStatusDriverRear;
    }

    public Integer getWinStatusPassengerRear() {
        return winStatusPassengerRear;
    }

    public void setWinStatusPassengerRear(Integer winStatusPassengerRear) {
        this.winStatusPassengerRear = winStatusPassengerRear;
    }

    public Integer getSunroofOpenStatus() {
        return sunroofOpenStatus;
    }

    public void setSunroofOpenStatus(Integer sunroofOpenStatus) {
        this.sunroofOpenStatus = sunroofOpenStatus;
    }

    public Integer getInteriorTemp() {
        return interiorTemp;
    }

    public void setInteriorTemp(Integer interiorTemp) {
        this.interiorTemp = interiorTemp;
    }

    public Integer getExteriorTemp() {
        return exteriorTemp;
    }

    public void setExteriorTemp(Integer exteriorTemp) {
        this.exteriorTemp = exteriorTemp;
    }

}
