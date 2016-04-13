/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package geotools;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;

public class Quickstart {
    public static void main(String[] args) throws Exception {
        // CRS.decode("EPSG:4326");
        // CRSAuthorityFactory factory = CRS.getAuthorityFactory(true);
        // CoordinateReferenceSystem crs = factory.createCoordinateReferenceSystem("EPSG:4326");

        String EPSG4326 = "GEOGCS[\"WGS 84\",DATUM[\"WGS_1984\",SPHEROID[\"WGS 84\",6378137,298.257223563,AUTHORITY[\"EPSG\",\"7030\"]],AUTHORITY[\"EPSG\",\"6326\"]],PRIMEM[\"Greenwich\",0,AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\",0.01745329251994328,AUTHORITY[\"EPSG\",\"9122\"]],AUTHORITY[\"EPSG\",\"4326\"]]";
        CoordinateReferenceSystem worldCRS = CRS.parseWKT(EPSG4326);


        System.out.println(worldCRS);
        // DefaultGeographicCRS.WGS84;

        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

        WKTReader reader = new WKTReader(geometryFactory);
        Point point = (Point) reader.read("POINT (30 10)");

        reader = new WKTReader(geometryFactory);
        Polygon polygon = (Polygon) reader.read("POLYGON((20 10, 30 0, 40 10, 30 20, 20 10))");

        System.out.println(point);
        System.out.println(polygon);

        System.out.println(polygon.contains(point));
        System.out.println(point.within(polygon));

    }
}
