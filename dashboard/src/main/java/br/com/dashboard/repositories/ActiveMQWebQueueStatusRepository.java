package br.com.dashboard.repositories;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import br.com.dashboard.conf.ConfigProperties;
import br.com.dashboard.interfaces.QueueStatusRepository;
import br.com.dashboard.models.dto.QueueStatus;

public class ActiveMQWebQueueStatusRepository implements QueueStatusRepository {
	
	public List<QueueStatus> all() {

		List<QueueStatus> result = new ArrayList<QueueStatus>();

		try {
			Properties prop = new ConfigProperties().getProperty();

			String active_mq_url = prop.getProperty("active_mq_url");

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(active_mq_url).openStream());

			// root
			Node currentNode = doc.getFirstChild();

			while (XMLUtil.findFirstElementChild(currentNode) != null) {
				if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
					if (currentNode.getNodeName().equals("queue")) {
						Node statsNode = XMLUtil.findFirstElementChild(currentNode);
						if (statsNode.getNodeName().equals("stats")) {
							String queueName = currentNode.getAttributes().getNamedItem("name").getNodeValue();
							String size = statsNode.getAttributes().getNamedItem("size").getNodeValue();
							String consumerCount = statsNode.getAttributes().getNamedItem("consumerCount")
									.getNodeValue();
							String enqueueCount = statsNode.getAttributes().getNamedItem("enqueueCount").getNodeValue();
							String dequeueCount = statsNode.getAttributes().getNamedItem("dequeueCount").getNodeValue();
							result.add(
									new QueueStatus(queueName, Integer.parseInt(size), Integer.parseInt(enqueueCount),
											Integer.parseInt(consumerCount), Integer.parseInt(dequeueCount)));
						}
					}
				}
				currentNode = XMLUtil.findFirstElementChild(currentNode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}