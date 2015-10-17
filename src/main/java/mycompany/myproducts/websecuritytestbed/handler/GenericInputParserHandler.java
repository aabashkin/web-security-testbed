package mycompany.myproducts.websecuritytestbed.handler;

import java.util.ArrayList;
import java.util.Stack;
 


import mycompany.myproducts.websecuritytestbed.model.GenericInput;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
public class GenericInputParserHandler extends DefaultHandler
{
    //This is the list which shall be populated while parsing the XML.
    private ArrayList genericInputList = new ArrayList();
 
    //As we read any XML element we will push that in this stack
    private Stack elementStack = new Stack();
 
    //As we complete one user block in XML, we will push the User instance in userList
    private Stack objectStack = new Stack();
 
    public void startDocument() throws SAXException
    {
        //System.out.println("start of the document   : ");
    }
 
    public void endDocument() throws SAXException
    {
        //System.out.println("end of the document document     : ");
    }
 
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        //Push it in element stack
        this.elementStack.push(qName);
 
        //If this is start of 'user' element then prepare a new User instance and push it in object stack
        if ("input".equals(qName))
        {
            //New User instance
        	GenericInput genericInput = new GenericInput();
 
            //Set all required attributes in any XML element here itself
            if(attributes != null && attributes.getLength() == 1)
            {
            	genericInput.setInput(attributes.getValue(0));
            }
            this.objectStack.push(genericInput);
        }
    }
 
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        //Remove last added  element
        this.elementStack.pop();
 
        //User instance has been constructed so pop it from object stack and push in userList
        if ("input".equals(qName))
        {
        	GenericInput object = (GenericInput) this.objectStack.pop();
            this.genericInputList.add(object);
        }
    }
 
    /**
     * This will be called everytime parser encounter a value node
     * */
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        String value = new String(ch, start, length).trim();
 
        if (value.length() == 0)
        {
            return; // ignore white space
        }
 
        //handle the value based on to which element it belongs
        if ("input".equals(currentElement()))
        {
            GenericInput genericInput = (GenericInput) this.objectStack.peek();
            genericInput.setInput(value);
        }
    }
 
    /**
     * Utility method for getting the current element in processing
     * */
    private String currentElement()
    {
        return (String) this.elementStack.peek();
    }
 
    //Accessor for userList object
    public ArrayList getGenericInput()
    {
        return genericInputList;
    }
}

