/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
/**
 *
 * @author User
 */
public class MenuPizzeReader{
    
    public void readFile(){
        
        Document xmlMenu = getDocument("./databases/MenuPizze.xml");
        
        System.out.println("Root "+xmlMenu.getDocumentElement().getNodeName());
    }
    
    // Reads an XML file into a DOM document
    private static Document getDocument(String docString) {
	         
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
