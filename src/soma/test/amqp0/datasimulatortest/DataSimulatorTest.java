package soma.test.amqp0.datasimulatortest;

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
import no.ntnu.item.arctis.runtime.Block;
import soma.test.amqp0.datasimulator.Coordinate;

public class DataSimulatorTest extends Block {
	public List<Coordinate> route;
	private int index = 0;
	private boolean forward = true;
	public int temp = 0;
	public static HashMap<Integer, String> hm1 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm2 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm3 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> hm4 = new HashMap<Integer, String>();

	public String getTrainRouteFile() {
		File file = new File("files" + File.separator + "90track.bbm");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String xml = "";
			String s;
			while ((s = br.readLine()) != null) {
				xml += s;
			}
			br.close();
			return xml;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Incorrect Train Route file chosen!");
			return null;
		}
	}

	public List<Coordinate> convertRouteFromParse(Document document) {
		ArrayList<Coordinate> list = new ArrayList<Coordinate>();
		treeWalk(document.getRootElement());
		System.out.println(hm1);
		
		Coordinate cn = new Coordinate();
		cn.latitude = Double.parseDouble(hm1.get(1));
		cn.longitude = Double.parseDouble(hm2.get(1));
		list.add(cn);
		return list;
		
		//logger.error("Failed to read list of coordinates from KML file!");
		//return list;   line 52  53 of sensor block code 
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
					break;
				}
			}
		}
		
	}

	public Coordinate getNextPosition() {
		Coordinate next = route.get(index);
		if (forward && index == route.size() - 1) {
			forward = false;
		} else if (!forward && index == 0) {
			forward = true;
		} else if (forward) {
			++index;
		} else if (!forward) {
			--index;
		}
		return next;
	}
}
