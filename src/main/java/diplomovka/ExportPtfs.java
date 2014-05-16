package diplomovka;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import analyz.PtfsEdge;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import analyz.PtfsLPO;
import analyz.PtfsVertex;

public class ExportPtfs {

	public boolean exportPtfsToXml(PtfsLPO lpo, File file) {

		System.out.println(lpo);
		System.out.println(file);
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("Net");
			doc.appendChild(rootElement);

			Element vertexes = doc.createElement("Vertexes");
			rootElement.appendChild(vertexes);

			for (PtfsVertex vertex : lpo.getPtfsvrcholi()) {
				Element vertex_element = doc.createElement("Vertex");
				vertexes.appendChild(vertex_element);

				Element vertex_id = doc.createElement("Id");
				vertex_id.appendChild(doc.createTextNode(Integer
						.toString(vertex.getId())));
				vertex_element.appendChild(vertex_id);

				Element vertex_x = doc.createElement("X");
				vertex_x.appendChild(doc.createTextNode(Double.toString(vertex
						.getCenterX())));
				vertex_element.appendChild(vertex_x);

				Element vertex_y = doc.createElement("Y");
				vertex_y.appendChild(doc.createTextNode(Double.toString(vertex
						.getCenterY())));
				vertex_element.appendChild(vertex_y);

				Element vertex_name = doc.createElement("Name");
				if( vertex.getName() != null ){
					vertex_name.appendChild(doc.createTextNode(vertex.getName()));	
				}
				vertex_element.appendChild(vertex_name);

				Element vertex_width = doc.createElement("Width");
				vertex_width.appendChild(doc.createTextNode(Double
						.toString(vertex.getVertexWidth())));
				vertex_element.appendChild(vertex_width);

				Element vertex_isInput = doc.createElement("IsInput");
				vertex_isInput.appendChild(doc.createTextNode(Boolean
						.toString(vertex.getIsinput())));
				vertex_element.appendChild(vertex_isInput);

				Element vertex_isOutput = doc.createElement("IsOutput");
				vertex_isOutput.appendChild(doc.createTextNode(Boolean
						.toString(vertex.getIsoutput())));
				vertex_element.appendChild(vertex_isOutput);

			}

			Element edges = doc.createElement("Edges");
			rootElement.appendChild(edges);

			for (PtfsEdge edge : lpo.getPtfshrany()) {
				Element edge_element = doc.createElement("Edge");
				edges.appendChild(edge_element);

				Element edge_dest_id = doc.createElement("SourceVertexId");
				edge_dest_id.appendChild(doc.createTextNode(Integer
						.toString(edge.getSourceId())));
				edge_element.appendChild(edge_dest_id);

				Element edge_sour_id = doc.createElement("DesVertexId");
				edge_sour_id.appendChild(doc.createTextNode(Integer
						.toString(edge.getDestinationId())));
				edge_element.appendChild(edge_sour_id);

			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			// StreamResult result = new StreamResult(new
			// File("D:\\testing.ptfs"));
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);

			System.out.println("Done");
			return true;
		} catch (ParserConfigurationException pce) {
			JOptionPane.showMessageDialog(null, "Could not save file", "Error", JOptionPane.ERROR_MESSAGE);
			pce.printStackTrace();
			return false;
		} catch (TransformerException tfe) {
			JOptionPane.showMessageDialog(null, "Could not save file", "Error", JOptionPane.ERROR_MESSAGE);
			tfe.printStackTrace();
			return false;
		}

	}

}
