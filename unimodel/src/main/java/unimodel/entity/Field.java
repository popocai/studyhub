/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package unimodel.entity;

public class Field {
    private String vds_field, vehicle_model_code, csp_signal_value, can_signal, can_signal_value, csp_value;

    public String getVds_field() {
        return vds_field;
    }

    public void setVds_field(String vds_field) {
        this.vds_field = vds_field;
    }

    public String getVehicle_model_code() {
        return vehicle_model_code;
    }

    public void setVehicle_model_code(String vehicle_model_code) {
        this.vehicle_model_code = vehicle_model_code;
    }

    public String getCsp_signal_value() {
        return csp_signal_value;
    }

    public void setCsp_signal_value(String csp_signal_value) {
        this.csp_signal_value = csp_signal_value;
    }

    public String getCan_signal() {
        return can_signal;
    }

    public void setCan_signal(String can_signal) {
        this.can_signal = can_signal;
    }

    public String getCan_signal_value() {
        return can_signal_value;
    }

    public void setCan_signal_value(String can_signal_value) {
        this.can_signal_value = can_signal_value;
    }

    public String getCsp_value() {
        return csp_value;
    }

    public void setCsp_value(String csp_value) {
        this.csp_value = csp_value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Field [vds_field=");
        builder.append(vds_field);
        builder.append(", vehicle_model_code=");
        builder.append(vehicle_model_code);
        builder.append(", csp_signal_value=");
        builder.append(csp_signal_value);
        builder.append(", can_signal=");
        builder.append(can_signal);
        builder.append(", can_signal_value=");
        builder.append(can_signal_value);
        builder.append(", csp_value=");
        builder.append(csp_value);
        builder.append("]");
        return builder.toString();
    }

}
