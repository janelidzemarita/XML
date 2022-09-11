package Figure;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Thread{


	private static final ExecutorService s = Executors.newCachedThreadPool();
	private static final ExecutorService d = Executors.newCachedThreadPool();
	public static final String xmlFilePath = "src/main/java/XML_Files/output.xml";

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {

//		createXML("src/main/java/InputFiles/Circles_in.dat");
		createXML("src/main/java/InputFiles/Rectangles_in.dat");
//		createXML("src/main/java/InputFiles/Triangles_in.dat");


	}

	public static void createXML(String args) throws IOException, ParserConfigurationException, TransformerException {
		try (FileInputStream inStrm = new FileInputStream(args);
			 BufferedInputStream bf = new BufferedInputStream(inStrm);
			 BufferedReader read = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8))
			 ){

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			int count = 0;
			if(args == "src/main/java/InputFiles/Circles_in.dat"){
				Element root = document.createElement("Circle");
				document.appendChild(root);
				Element radius = document.createElement("Radiuses");
				root.appendChild(radius);
				Attr rad = document.createAttribute("Radius");
				radius.setAttributeNode(rad);
				while(read.ready()){
					count ++;
					String str = read.readLine()+ "\n";
					radius.appendChild(document.createTextNode(str));
				}

			}else if(args == "src/main/java/InputFiles/Rectangles_in.dat"){
				Element root = document.createElement("Rectangle");
				document.appendChild(root);
				Element length = document.createElement("Length");
				Element height = document.createElement("Height");
				root.appendChild(length);
				root.appendChild(height);
				Attr len = document.createAttribute("Length");
				Attr hei = document.createAttribute("Height");
				length.setAttributeNode(len);
				height.setAttributeNode(hei);

				while(read.ready()){
					count ++;
					String[] str = read.readLine().split("-");
					String str1 = str[0] + "\n";
					String str2 = str[1] + "\n";
					length.appendChild(document.createTextNode(str1));
					length.appendChild(document.createTextNode(str2));
				}

			}else {
				Element root = document.createElement("Triangle");
				document.appendChild(root);
				Element ab = document.createElement("AB");
				Element bc = document.createElement("BC");
				Element ca = document.createElement("CA");
				root.appendChild(ab);
				root.appendChild(bc);
				root.appendChild(ca);
				Attr abSide = document.createAttribute("AB");
				Attr bcSide = document.createAttribute("BC");
				Attr caSide = document.createAttribute("CA");
				ab.setAttributeNode(abSide);
				bc.setAttributeNode(bcSide);
				ca.setAttributeNode(caSide);
				while(read.ready()){
					count ++;
					String[] str = read.readLine().split("-");
					String str1 = str[0] + "\n";
					String str2 = str[1] + "\n";
					String str3 = str[2] + "\n";
					ab.appendChild(document.createTextNode(str1));
					bc.appendChild(document.createTextNode(str2));
					ca.appendChild(document.createTextNode(str3));
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));

			transformer.transform(domSource, streamResult);
		}

		System.out.println("Done creating XML File");
	}
}



