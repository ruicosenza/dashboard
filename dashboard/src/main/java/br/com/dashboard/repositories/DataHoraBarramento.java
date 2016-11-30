package br.com.dashboard.repositories;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DataHoraBarramento {

	public String getDataHoraBarramento() {
		String url = "http://esb.interc2.defesa.mil.br/dt/now";
		RestTemplate restTemplate = new RestTemplate();
		String xml = restTemplate.getForObject(url, String.class);
		String data = "";

		if (!xml.isEmpty()) {

			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

				InputSource is = new InputSource(new StringReader(xml));

				Document doc = dBuilder.parse(is);

				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("esb");

				Node nNode = nList.item(0);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					try {
						data = eElement.getElementsByTagName("utcDate").item(0).getFirstChild().getNodeValue();
					} catch (Exception e) {
						data = eElement.getElementsByTagName("utcDate").item(0).getTextContent();
					}
					
					data = data.replaceAll("[A-Z]", " ");
					data = data.trim();

					String dia = data.split(" ")[0].split("-")[2];
					String mes = data.split(" ")[0].split("-")[1];
					String ano = data.split(" ")[0].split("-")[0];
					String hora = data.split(" ")[1].split(":")[0];
					String minuto = data.split(" ")[1].split(":")[1];
					String segundo = data.split(" ")[1].split(":")[2];

					data = dia + "/" + mes + "/" + ano + " " + hora + ":" + minuto + ":" + segundo;
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return data;
	}
}