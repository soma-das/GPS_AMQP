package soma.test.amqp0.datasimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

//import soma.test.amqp0.datasimulator.Coordinate;
import no.ntnu.item.arctis.runtime.Block;


public class DataSimulator extends Block {
	
	public List<Coordinate> route;
	private int index = 0;
	private boolean forward = true;
	
	public int temp = 0;
	public static HashMap<Integer, String> hm1 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm2 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm3 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm4 = new HashMap<Integer, String>();
	
	
	
	
	public String getTrainRouteFile() {
		
		//return new File("files" + File.separator + "route.kml");
				File file = new File("files" + File.separator + "90track.bbm");
				
				try {												
					//File file = new File(directory);
						BufferedReader br = new BufferedReader(new FileReader(file));
						String xml = "";
						String s;
						while ((s = br.readLine()) != null) {
								xml += s;
							}
						br.close();
					return xml;
					}
					catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Incorrect Train Route file chosen!");
						return null;
					}
			}
	
	
	
	
	
	
	/*
	public List<Coordinate> convertRouteFromKML(Kml kml) {
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		if (kml.getFeature() instanceof Document) {
			Document d = (Document) kml.getFeature();
			for (Feature f : d.getFeature()) {
				if (f instanceof Placemark) {
					Placemark p = (Placemark) f;
					if (p.getGeometry() instanceof Polygon) {
						Polygon pg = (Polygon) p.getGeometry();
						LinearRing lr = pg.getOuterBoundaryIs().getLinearRing();
						for (de.micromata.opengis.kml.v_2_2_0.Coordinate c : lr.getCoordinates()) {
							Coordinate cn = new Coordinate();
							cn.latitude = (float) c.getLatitude();
							cn.longitude = (float) c.getLongitude();
							list.add(cn);
						}
						return list;
					} else if (p.getGeometry() instanceof LineString) {
						LineString pg = (LineString) p.getGeometry();
						for (de.micromata.opengis.kml.v_2_2_0.Coordinate c : pg.getCoordinates()) {
							Coordinate cn = new Coordinate();
							cn.latitude = (float) c.getLatitude();
							cn.longitude = (float) c.getLongitude();
							list.add(cn);
						}
						return list;
					}
				}
			}
		}
		logger.error("Failed to read list of coordinates from KML file!");
		return list;
	}*/

/*
	public void convertRouteFromParse(final Document document) {
		treeWalk(document.getRootElement());
	}
	
	public void treeWalk(Element element) {
		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				treeWalk((Element) node);
				Element element1 = (Element) node;
				if ((element1.elementText("X") != null)&&(element1.elementText("Y") != null)&&(element1.elementText("Width") != null)&&(element1.elementText("Height") != null)) {
					
					hm1.put(temp, element1.elementText("X"));
				    hm2.put(temp, element1.elementText("Y"));
				    hm3.put(temp, element1.elementText("Width"));
				    hm4.put(temp, element1.elementText("Height"));

//					arr[temp][index] = element1.elementText("X");
//					index = index + 1;
//					arr[temp][index] = element1.elementText("Y");
//					index = index + 1;
//					arr[temp][index] = element1.elementText("Width");
//					arr[temp][index] = element1.elementText("Height");
					index = 0;
					temp = temp + 1;
				}
			} else {
			}
		}
		
	}

	/*
	public HashMap<Integer, String> getNextPosition() {
		HashMap<Integer, String> next = new HashMap<Integer, String>();
		
		next.put(0, hm1.get("X"));
		next.put(1, hm2.get("Y"));
		next.put(2, hm3.get("Width"));
		next.put(3, hm4.get("Height"));
		return next;
	}
	*/	

public void convertRouteFromParse(Document document){
	
	/// Aishwarya do coding here to get data out 
	
}

	
public Coordinate getNextPosition() {
	Coordinate next = route.get(index);

	if(forward && index==route.size()-1) {
		// arrived at the end
		forward=false;
	} else if (!forward && index==0) {
		// arrived at the beginning
		forward=true;
	} else if(forward) {
		++index;
	} else if(!forward) {
		--index;
	}
	return next;
}
	
	
}
