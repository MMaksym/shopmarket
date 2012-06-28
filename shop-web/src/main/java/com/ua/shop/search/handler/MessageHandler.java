package com.ua.shop.search.handler;

import com.ua.shop.search.model.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import java.util.LinkedList;
import java.util.List;

public class MessageHandler {

    private static final String ADD_ELEMENT_NAME = "add";
    private static final String DOC_ELEMENT_NAME = "doc";
    private static final String FIELD_ELEMENT_NAME = "field";
    private static final String FIELD_ELEMENT_NAME_ATTRIBUTE = "name";

    private static final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private static DocumentBuilder builder = null;
    private static final TransformerFactory transfac = TransformerFactory.newInstance();
    private static Transformer trans;

    static {
        try {
            builder = factory.newDocumentBuilder();
            trans = transfac.newTransformer();

            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
        } catch (Exception e) {
            throw new InstantiationError(e.getMessage());
        }
    }

    public void marshal(List<Message> messages, Result result) {
        Document document = builder.newDocument();
        Element rootElement = document.createElement(ADD_ELEMENT_NAME);

        for (Message message : messages) {
            Element doc = createDoc(document, message);
            rootElement.appendChild(doc);
        }

        document.appendChild(rootElement);
        DOMSource source = new DOMSource(document);
        try {
            trans.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public void marshal(Message message, Result result) {
        List<Message> messages = new LinkedList<Message>();
        messages.add(message);
        marshal(messages, result);

    }

    private Element createDoc(Document document, Message message) {
        Element doc = document.createElement(DOC_ELEMENT_NAME);

        Element idField = document.createElement(FIELD_ELEMENT_NAME);
        idField.setAttribute(FIELD_ELEMENT_NAME_ATTRIBUTE, "id");
        idField.appendChild(document.createTextNode(message.getId()));
        doc.appendChild(idField);

        Element messageField = document.createElement(FIELD_ELEMENT_NAME);

        messageField.setAttribute(FIELD_ELEMENT_NAME_ATTRIBUTE, "message");
        messageField.appendChild(document.createTextNode(message.getMessage()));
        doc.appendChild(messageField);

        return doc;
    }
}
