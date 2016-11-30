package br.com.dashboard.repositories;

import org.w3c.dom.Node;

public class XMLUtil {

	public static Node findFirstElementChild(Node parent) {
        if (parent == null) {
            return null;
        }

        Node child = parent.getFirstChild();

        if (child == null) {
            return null;
        }

        for (Node n = child; n != null; n = n.getNextSibling()) {
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                return n;
            }
        }
        return null;
    }
}