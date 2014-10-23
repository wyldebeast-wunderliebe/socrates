package com.w20e.socrates.model.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.NodeImpl;
import com.w20e.socrates.util.DataUtility;

public class InstanceXMLSerializer {

    private static final String USER_MISSING = "USER_MISSING";
    private static final String SYSTEM_MISSING = "SYSTEM_MISSING";

    /**
     * String buffer size for variables.
     */
    private static final int BUFF_SIZE = 128;

    private static final String CDATA = "CDATA";

    /**
     * Formatter for time stamps.
     */
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(
            "yyyyMMddHHmmss", Locale.getDefault());

    public static synchronized void serialize(final Instance data,
            final File target) throws IOException,
            TransformerConfigurationException, SAXException {

        Map<String, Object> metaData = data.getMetaData();

        Writer writer = new FileWriter(target);

        StreamResult streamResult = new StreamResult(writer);
        SAXTransformerFactory tf = (SAXTransformerFactory) TransformerFactory
                .newInstance();
        TransformerHandler hd = tf.newTransformerHandler();
        Transformer serializer = hd.getTransformer();

        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        // serializer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"users.dtd");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        hd.setResult(streamResult);
        hd.startDocument();

        AttributesImpl atts = new AttributesImpl();

        hd.startElement("", "", "instance", atts);

        metaData.put("stored", new Date());

        if (metaData.get("storage-type") == null) {
            metaData.put("storage-type", "submit");
        }

        String name;
        Object value;

        for (Iterator<String> i = metaData.keySet().iterator(); i.hasNext();) {

            name = i.next();
            value = metaData.get(name);

            if (value instanceof Date) {
                atts.clear();
                atts.addAttribute("", "", "name", InstanceXMLSerializer.CDATA, name);
                atts.addAttribute("", "", "value", InstanceXMLSerializer.CDATA,
                        InstanceXMLSerializer.FORMATTER.format(value));
                hd.startElement("", "", "meta", atts);
            } else {
                atts.clear();
                atts.addAttribute("", "", "name", InstanceXMLSerializer.CDATA, name);
                atts.addAttribute("", "", "value", InstanceXMLSerializer.CDATA, String.format("%s",
                        value));
                hd.startElement("", "", "meta", atts);
            }

            hd.endElement("", "", "meta");
        }

        Node node;
        
        atts.clear();

        // Store the instance as file.
        for (Iterator<Node> i = data.getAllNodes().iterator(); i.hasNext();) {

            node = i.next();

            StringTokenizer tokenizer = new StringTokenizer(node.getName(),
                    SimplePathMatcher.PATH_SEPARATOR);

            String currToken = "";
            StringBuffer reverseNodeName = new StringBuffer(BUFF_SIZE);

            // write nested structure
            while (tokenizer.hasMoreTokens()) {
                currToken = tokenizer.nextToken();
                hd.startElement("", "", currToken, atts);
                reverseNodeName.insert(0, currToken
                        + SimplePathMatcher.PATH_SEPARATOR);
            }

            // try {
            // reverse value if at all. If null, do nothing.
            if (node.getValue() != null) {
                hd.characters(node.getValue().toString().toCharArray(), 0, node
                        .getValue().toString().length());
            } else {
                hd.characters(DataUtility.USER_MISSING.toCharArray(), 0,
                        DataUtility.USER_MISSING.length());
            }
            // } catch (Exception e) {
            // hd.characters(DataUtility.SYSTEM_MISSING.toCharArray(), 0,
            // DataUtility.SYSTEM_MISSING.length());
            // }

            tokenizer = new StringTokenizer(reverseNodeName.toString(),
                    SimplePathMatcher.PATH_SEPARATOR);

            // close nested structure
            while (tokenizer.hasMoreTokens()) {
                currToken = tokenizer.nextToken();
                hd.endElement("", "", currToken);
            }
        }

        hd.endElement("", "", "instance");

        hd.endDocument();

        writer.flush();

        writer.close();

    }

    public static synchronized Instance deserialize(URI source) {

        InstanceImpl inst = new InstanceImpl();

        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(source.toString());

            NodeList nodes = doc.getDocumentElement().getChildNodes();
            NodeImpl node;
            String value;
            org.w3c.dom.Node n;

            for (int i = 0; i < nodes.getLength(); i++) {

                n = nodes.item(i);

                if (n.getNodeName().equals("meta")) {

                    inst.getMetaData().put(((Element) n).getAttribute("name"),
                            ((Element) n).getAttribute("value"));
                } else if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {

                    node = new NodeImpl(n.getNodeName());

                    value = ((Text) n.getFirstChild()).getNodeValue();

                    node.setValue(InstanceXMLSerializer.normalizeValue(value));

                    inst.addNode(node);
                }
            }

        } catch (ParserConfigurationException e) {
            inst.getMetaData().put("error", e.getMessage());
        } catch (SAXException e) {
            inst.getMetaData().put("error", e.getMessage());
        } catch (IOException e) {
            inst.getMetaData().put("error", e.getMessage());
        }

        return inst;
    }

    /**
     * We need to normalize the value to what it used to be...
     * 
     * @param value
     * @return
     */
    private static Object normalizeValue(String value) {

        if (InstanceXMLSerializer.SYSTEM_MISSING.equals(value)) {
            return null;
        }

        if (InstanceXMLSerializer.USER_MISSING.equals(value)) {
            return null;
        }

        if ("".equals(value)) {
            return null;
        }

        return value;
    }
}
