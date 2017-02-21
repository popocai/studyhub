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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FileReader {
    public static List<Field> ReadFile(String Path) {
        BufferedReader reader = null;
        List<Field> fields = new ArrayList<>();
        try {
            InputStream is = FileReader.class.getClassLoader().getResourceAsStream(Path);

            InputStreamReader inputStreamReader = new InputStreamReader(is);
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                Field field = new Field();
                String[] fs = tempString.split(",");
                field.setVds_field(fs[0]);
                field.setVehicle_model_code(fs[1]);
                field.setCsp_signal_value(fs[2]);
                field.setCan_signal(fs[3]);
                field.setCan_signal_value(fs[4]);
                field.setCsp_value(fs[5]);

                fields.add(field);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fields;
    }

    public static void main(String[] args) {
        List<Field> fields = ReadFile("CSP_Singal.csv");

        System.out.println(fields);
    }
}
