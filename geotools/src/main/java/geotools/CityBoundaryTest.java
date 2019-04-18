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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

public class CityBoundaryTest {
	private static final String strWKTMercator = "PROJCS[\"World_Mercator\"," + "GEOGCS[\"GCS_WGS_1984\","
			+ "DATUM[\"WGS_1984\"," + "SPHEROID[\"WGS_1984\",6378137,298.257223563]]," + "PRIMEM[\"Greenwich\",0],"
			+ "UNIT[\"Degree\",0.017453292519943295]]," + "PROJECTION[\"Mercator_1SP\"],"
			+ "PARAMETER[\"False_Easting\",0]," + "PARAMETER[\"False_Northing\",0],"
			+ "PARAMETER[\"Central_Meridian\",0]," + "PARAMETER[\"latitude_of_origin\",0]," + "UNIT[\"Meter\",1]]";

	private CoordinateReferenceSystem mercatroCRS;

	private MathTransform transform;

	private GeometryFactory geoFactory = new GeometryFactory();

	public CityBoundaryTest() {
		try {
			mercatroCRS = CRS.parseWKT(strWKTMercator);
			transform = CRS.findMathTransform(DefaultGeographicCRS.WGS84, mercatroCRS);
		} catch (FactoryException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		GeometryWSG84 gr = new GeometryWSG84();
		Polygon polygon = readCityBoundary();

		Point pointIn = gr.createPoint(55.3895321, 25.3585432);                //Sharjah
		Point pointOut = gr.createPoint(55.2961962, 25.2683521);				//Dubai
		Point pointboundary = gr.createPoint(55.0979187,25.5250676);			//exactly on the boundary
		Point pointboundary2 = gr.createPoint(55.556222,25.242738);					// S116 road 
		Point pointIn2 = gr.createPoint(55.62320107255792, 25.31112295);		// another place in Sharjah 
		

		long t1 = System.currentTimeMillis();
		System.out.println(polygon.contains(pointIn) + "[" + (System.currentTimeMillis() - t1) + "]");
		t1 = System.currentTimeMillis();
		System.out.println(polygon.contains(pointOut) + "[" + (System.currentTimeMillis() - t1) + "]");
		t1 = System.currentTimeMillis();
		System.out.println(polygon.contains(pointboundary) + "[" + (System.currentTimeMillis() - t1) + "]");
		t1 = System.currentTimeMillis();
		System.out.println(polygon.contains(pointboundary2) + "[" + (System.currentTimeMillis() - t1) + "]");
		t1 = System.currentTimeMillis();
		System.out.println(polygon.contains(pointIn2) + "[" + (System.currentTimeMillis() - t1) + "]");
	}

	public static Polygon readCityBoundary() throws Exception {
		List<String> positions = Files.readAllLines(Paths.get("C:\\Users\\ecaiyag\\Desktop\\TMP\\Sharjah.csv"));

		double[] lons = new double[positions.size()];
		double[] lats = new double[positions.size()];

		for (int i = 0; i < positions.size(); i++) {
			String line = positions.get(i);
			System.out.println(line);
			String[] nums = line.split(",");
			lons[i] = Double.parseDouble(nums[0]);
			lats[i] = Double.parseDouble(nums[1]);
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
		GeometryWSG84 gr = new GeometryWSG84();
		Polygon polygon = gr.createPolygonByWKT(lons, lats);

		return polygon;
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

		if (lons.length != lats.length) {
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

}
