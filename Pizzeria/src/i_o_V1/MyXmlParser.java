/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package i_o_V1;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *This Class Creates a DOM tree Object File from an xml file 
 * @author User
 */
public class MyXmlParser {
    
    
    // Reads an XML file into a DOM document
    public static Document getDocument(String docString) {
	         
	try {
            // API used to convert XML into a DOM object tree
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Ignore all of the comments
            factory.setIgnoringComments(true);
            // Ignore white space in elements
            factory.setIgnoringElementContentWhitespace(true);
            // Validate the XML as it is parsed
            factory.setValidating(true);	            
            // Provides access to the documents data
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Takes the document
            return builder.parse(new InputSource(docString));
	}
        catch(Exception ex){
	    System.out.println(ex.getMessage());
	}
	return null;
    }
}
