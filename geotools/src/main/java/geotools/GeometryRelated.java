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
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class GeometryRelated {

    private GeometryFactory geometryFactory = null;

    private MathTransform   transform       = null;

    public GeometryRelated() {
        try {
            // String EPSG4326 = "GEOGCS[\"WGS 84\",DATUM[\"WGS_1984\",SPHEROID[\"WGS
            // 84\",6378137,298.257223563,AUTHORITY[\"EPSG\",\"7030\"]],AUTHORITY[\"EPSG\",\"6326\"]],PRIMEM[\"Greenwich\",0,AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\",0.01745329251994328,AUTHORITY[\"EPSG\",\"9122\"]],AUTHORITY[\"EPSG\",\"4326\"]]";
            //
            // // DefaultGeographicCRS def = DefaultGeographicCRS.WGS84;
            //
            // String SOURCE_WKT = "GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\",
            // SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],
            // AXIS[\"Latitude\",NORTH]]";
            //
            // String TARGET_WKT = "GEOGCS[\"WGS 84\",DATUM[\"World Geodetic System 1984\",SPHEROID[\"WGS 84\",
            // 6378137.0, 298.257223563,
            // AUTHORITY[\"EPSG\",\"7030\"]],AUTHORITY[\"EPSG\",\"6326\"]],PRIMEM[\"Greenwich\",
            // 0.0,AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\",0.017453292519943295],AXIS[\"Geodetic latitude\",
            // NORTH],AXIS[\"Geodetic longitude\",EAST],AUTHORITY[\"EPSG\",\"4326\"]]";
            //
            // CoordinateReferenceSystem worldCRS = CRS.parseWKT(EPSG4326);
            // CoordinateReferenceSystem sourceCRS = CRS.parseWKT(SOURCE_WKT);
            // CoordinateReferenceSystem targetCRS = CRS.parseWKT(EPSG4326);

            geometryFactory = JTSFactoryFinder.getGeometryFactory(null);

            // transform = CRS.findMathTransform(sourceCRS, targetCRS);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Point createPoint(String lon, String lat) {
        Coordinate coord = new Coordinate(Double.parseDouble(lon), Double.parseDouble(lat));
        Point point = geometryFactory.createPoint(coord);
        return point;
    }

    /**
     * will return true as the two line strings define exactly the same shape. 两个几何对象是否是重叠的
     *
     * @return
     * @throws ParseException
     */
    public boolean equalsGeo() throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
        LineString geometry1 = (LineString) reader.read("LINESTRING(0 0, 2 0, 5 0)");
        LineString geometry2 = (LineString) reader.read("LINESTRING(5 0, 0 0)");
        // return geometry1 ==geometry2; false
        // check if two geometries are exactly equal; right down to the coordinate level.
        // return geometry1.equalsExact(geometry2); false
        return geometry1.equals(geometry2);// true
    }

    /**
     * The geometries have no points in common 几何对象没有交点(相邻)
     *
     * @return
     * @throws ParseException
     */
    public boolean disjointGeo() throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
        LineString geometry1 = (LineString) reader.read("LINESTRING(0 0, 2 0, 5 0)");
        LineString geometry2 = (LineString) reader.read("LINESTRING(0 1, 0 2)");
        return geometry1.disjoint(geometry2);
    }

    public void distance() throws Exception {
        Coordinate coord = new Coordinate(Double.parseDouble("116"), Double.parseDouble("50"));
        Point p1 = geometryFactory.createPoint(coord);

        p1 = projectTransform(116, 50);
        // (Point) JTS.transform(p1, transform);

        coord = new Coordinate(Double.parseDouble("118"), Double.parseDouble("50"));
        Point p2 = geometryFactory.createPoint(coord);
        // p2 = (Point) JTS.transform(p1, transform);

        p2 = projectTransform(118, 50);

        System.out.println(p1.distance(p2));

        coord = new Coordinate(Double.parseDouble("116"), Double.parseDouble("0"));
        Point p3 = geometryFactory.createPoint(coord);
        // p3 = (Point) JTS.transform(p1, transform);
        p3 = projectTransform(116, 0);

        coord = new Coordinate(Double.parseDouble("118"), Double.parseDouble("0"));
        Point p4 = geometryFactory.createPoint(coord);
        // p4 = (Point) JTS.transform(p1, transform);
        p4 = projectTransform(118, 0);
        System.out.println(p3.distance(p4));
    }

    /**
     * The geometries have at least one point in common. 至少一个公共点(相交)
     *
     * @return
     * @throws ParseException
     */
    public boolean intersectsGeo() throws ParseException {
        WKTReader reader = new WKTReader(geometryFactory);
        LineString geometry1 = (LineString) reader.read("LINESTRING(0 0, 2 0, 5 0)");
        LineString geometry2 = (LineString) reader.read("LINESTRING(0 0, 0 2)");
        Geometry interPoint = geometry1.intersection(geometry2);// 相交点
        System.out.println(interPoint.toText());// 输出 POINT (0 0)
        return geometry1.intersects(geometry2);
    }

    public Point projectTransform(double lon, double lat)
            throws FactoryException, MismatchedDimensionException, TransformException {
        // 原始坐标点
        // PS:通常逻辑上理解经度应该是横坐标x，纬度是y，可是这里经度要填到y，纬度x，否则会报错

        String EPSG4326 = "GEOGCS[\"WGS 84\",DATUM[\"WGS_1984\",SPHEROID[\"WGS 84\",6378137,298.257223563,AUTHORITY[\"EPSG\",\"7030\"]],AUTHORITY[\"EPSG\",\"6326\"]],PRIMEM[\"Greenwich\",0,AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\",0.01745329251994328,AUTHORITY[\"EPSG\",\"9122\"]],AUTHORITY[\"EPSG\",\"4326\"]]";

        // DefaultGeographicCRS def = DefaultGeographicCRS.WGS84;

        String SOURCE_WKT = "GEOGCS[\"GCS_North_American_1983\",DATUM[\"D_North_American_1983\", SPHEROID[\"GRS_1980\",6378137.0,298.257222101]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]], AXIS[\"Latitude\",NORTH]]";

        String TARGET_WKT = "GEOGCS[\"WGS 84\",DATUM[\"World Geodetic System 1984\",SPHEROID[\"WGS 84\", 6378137.0, 298.257223563, AUTHORITY[\"EPSG\",\"7030\"]],AUTHORITY[\"EPSG\",\"6326\"]],PRIMEM[\"Greenwich\", 0.0,AUTHORITY[\"EPSG\",\"8901\"]],UNIT[\"degree\",0.017453292519943295],AXIS[\"Geodetic latitude\", NORTH],AXIS[\"Geodetic longitude\",EAST],AUTHORITY[\"EPSG\",\"4326\"]]";

        CoordinateReferenceSystem worldCRS = CRS.parseWKT(EPSG4326);
        CoordinateReferenceSystem sourceCRS = CRS.parseWKT(SOURCE_WKT);
        CoordinateReferenceSystem targetCRS = CRS.parseWKT(EPSG4326);

        Coordinate coord = new Coordinate(lat, lon);
        Point sourcePoint = geometryFactory.createPoint(coord);

        // 定义转换前和转换后的投影，可以用ESPG或者wkt
        // "PROJCS[\"Xian_1980_3_Degree_GK_CM_111E\",GEOGCS[\"GCS_Xian_1980\",DATUM[\"D_Xian_1980\",SPHEROID[\"Xian_1980\",6378140.0,298.257]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Gauss_Kruger\"],PARAMETER[\"False_Easting\",500000.0],PARAMETER[\"False_Northing\",0.0],PARAMETER[\"Central_Meridian\",111.0],PARAMETER[\"Scale_Factor\",1.0],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]]";
        // CoordinateReferenceSystem mercatroCRS = CRS.parseWKT(strWKTMercator);
        // CoordinateReferenceSystem crsSource = CRS.decode(SOURCE_WKT);
        // CoordinateReferenceSystem crsTarget = CRS.decode(EPSG4326);
        // 投影转换
        MathTransform transform1 = CRS.findMathTransform(sourceCRS, targetCRS, true);

        Point pointTarget = (Point) JTS.transform(sourcePoint, transform1);


        return pointTarget;
    }

    public static void main(String[] args) throws Exception {
        GeometryRelated gr = new GeometryRelated();
        // System.out.println(gr.equalsGeo());
        // System.out.println(gr.disjointGeo());
        // System.out.println(gr.intersectsGeo());
        gr.distance();
    }

}
