package home.files;

import home.entities.Users;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Xmlpars {
    public static List<Users> parse(String path) throws IOException, SAXException, ParserConfigurationException, NoSuchFieldException, IllegalAccessException {
        String tag, value; Field field;
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(path);
        Node root = document.getDocumentElement();
       // NodeList  nodeList = doc.getElementsByTagName("row"); = root.getChildNodes();
        System.out.println("===== XML read: root " + root.getNodeName());
        NodeList nodeList = document.getElementsByTagName("users");
        if (nodeList==null || nodeList.getLength()==0) {return null;}
        List<Users> users = new ArrayList<>();
        for (int i=0;i<nodeList.getLength();i++) {
            Node nitem = nodeList.item(i);
            System.out.println("===== XML read: item " + nitem.getNodeName());
          //  NamedNodeMap attributes =nitem.getAttributes();
            NodeList attributes = nitem.getChildNodes();
            if (attributes!=null && attributes.getLength()>0) {
              try {
                  Users user = new Users();
                  for (int j = 0; j < attributes.getLength(); j++) {

                      Node attribute = attributes.item(j);
                      if (attribute.getNodeType() == Node.ELEMENT_NODE) {
                          //    tag = attribute.getNodeName().toString();
                          tag = attribute.getNodeName();
                          System.out.println("===== XML read: attributes tag " + tag);
                          value = attribute.getTextContent();// getNodeValue();
                          System.out.println("===== XML read: attributes value " + value);

                          field = user.getClass().getDeclaredField(tag);
                          field.setAccessible(true);
                          field.set(user, (tag.equals("id") ? Long.parseLong(value) : value));
                      }
                  }
                  users.add(user);
                  System.out.println("===== XML read: user " + user);
              } catch (Exception e)  {}
            }
        }
      return users;
    }

}
