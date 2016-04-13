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

import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;

public class GeometryWSG84 {

    // 这里是以OGC WKT形式定义的是World Mercator投影，网页地图一般使用该投影
    private static final String       strWKTMercator = "PROJCS[\"World_Mercator\"," + "GEOGCS[\"GCS_WGS_1984\","
            + "DATUM[\"WGS_1984\"," + "SPHEROID[\"WGS_1984\",6378137,298.257223563]]," + "PRIMEM[\"Greenwich\",0],"
            + "UNIT[\"Degree\",0.017453292519943295]]," + "PROJECTION[\"Mercator_1SP\"],"
            + "PARAMETER[\"False_Easting\",0]," + "PARAMETER[\"False_Northing\",0],"
            + "PARAMETER[\"Central_Meridian\",0]," + "PARAMETER[\"latitude_of_origin\",0]," + "UNIT[\"Meter\",1]]";

    private CoordinateReferenceSystem mercatroCRS;

    private MathTransform             transform;

    private GeometryFactory           geoFactory     = new GeometryFactory();

    public GeometryWSG84() {
        try {
            mercatroCRS = CRS.parseWKT(strWKTMercator);
            transform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, mercatroCRS);
        } catch (FactoryException e) {
            e.printStackTrace();
        }
    }

    public Point createPoint(double lon, double lat) throws Exception {
        // 传入原始的经纬度坐标
        Coordinate sourceCoord = new Coordinate(lon, lat);


        // JTSFactoryFinder.getGeometryFactory();
        Point sourcePoint = geoFactory.createPoint(sourceCoord);

        // 做投影转换，将WCG84坐标转换成世界墨卡托投影转
        Point targetPoint = (Point) JTS.transform(sourcePoint, transform);
        return targetPoint;
    }

    public Polygon createPolygonByWKT(double[] lons, double[] lats) throws Exception {
        WKTReader reader = new WKTReader(geoFactory);

        if(lons.length != lats.length){
            return null;
        }

        StringBuffer sb = new StringBuffer("POLYGON((");
        for (int i = 0; i < lons.length; i++) {
            sb.append(lons[i]);
            sb.append(" ");
            sb.append(lats[i]);

            if (i != (lons.length - 1)) {
                sb.append(",");
            }
        }
        sb.append("))");
        System.out.println(sb);

        Polygon polygon = (Polygon) reader.read(sb.toString());

        polygon = (Polygon) JTS.transform(polygon, transform);

        return polygon;
    }



    public void distance() throws Exception {
        Point p1 = createPoint(116, 88);
        Point p2 = createPoint(117, 88);
        Point p3 = createPoint(116, 23);
        Point p4 = createPoint(117, 23);

        System.out.println(p1.distance(p2));
        System.out.println(p3.distance(p4));

    }

    public static void main(String[] args) throws Exception {
        GeometryWSG84 gr = new GeometryWSG84();
        // System.out.println(gr.equalsGeo());
        // System.out.println(gr.disjointGeo());
        // System.out.println(gr.intersectsGeo());
        gr.distance();

        double[] lons = new double[] { 113, 114, 113.5, 113 };
        double[] lats = new double[] { 23.1, 24, 25, 23.1 };

        Polygon polygon = gr.createPolygonByWKT(lons, lats);

        Point pointIn = gr.createPoint(113.1, 23.2);

        Point pointOut = gr.createPoint(114.1, 23.2);

        System.out.println(polygon);

        System.out.println(polygon.contains(pointIn));
        System.out.println(polygon.contains(pointOut));

    }

}
