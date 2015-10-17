package mycompany.myproducts.websecuritytestbed.controller;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import mycompany.myproducts.websecuritytestbed.util.DocUtil;
import mycompany.myproducts.websecuritytestbed.handler.GenericInputParserHandler;
import mycompany.myproducts.websecuritytestbed.model.GenericInput;

@Controller
public class XXEController {
	
	@RequestMapping(value = "/xxe/documentbuilder", method = RequestMethod.POST, produces="text/plain")
	@ResponseBody
	public String xxeDocBuilder(HttpServletRequest request) throws SAXException, ParserConfigurationException, IOException, TransformerException{
		
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
		Document doc = db.parse(request.getInputStream());
		
		String response = DocUtil.getStringFromDoc(doc);
		
		return response;

	}
	
	@RequestMapping(value = "/xxe/documentbuilder-SAFE", method = RequestMethod.POST, produces="text/plain")
	@ResponseBody
	public String xxeDocBuilderSAFE(HttpServletRequest request) throws SAXException, ParserConfigurationException, IOException, TransformerException{
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setFeature("http://xml.org/sax/features/external-general-entities", Boolean.FALSE);
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		Document doc = db.parse(request.getInputStream());
		
		String response = DocUtil.getStringFromDoc(doc);
		
		return response;

	}
	
	@RequestMapping(value = "/xxe/xmlreader", method = RequestMethod.POST, produces="text/plain")
	@ResponseBody
	public String xxeXMLReader(HttpServletRequest request) throws SAXException, ParserConfigurationException, IOException, TransformerException{
		
		XMLReader reader = XMLReaderFactory.createXMLReader();
		
		GenericInputParserHandler handler = new GenericInputParserHandler();
		reader.setContentHandler(handler);

		reader.parse(new InputSource(request.getInputStream()));
		
		ArrayList<GenericInput> genericInput = new ArrayList<GenericInput>();
		
		genericInput = handler.getGenericInput();
		
		String response = genericInput.toString();
		
		return response;

	}
	
	@RequestMapping(value = "/xxe/xmlreader-SAFE", method = RequestMethod.POST, produces="text/plain")
	@ResponseBody
	public String xxeXMLReaderSAFE(HttpServletRequest request) throws SAXException, ParserConfigurationException, IOException, TransformerException{
		
		XMLReader reader = XMLReaderFactory.createXMLReader();
		
		GenericInputParserHandler handler = new GenericInputParserHandler();
		reader.setFeature("http://xml.org/sax/features/external-general-entities", Boolean.FALSE);
		reader.setContentHandler(handler);

		reader.parse(new InputSource(request.getInputStream()));
		
		ArrayList<GenericInput> genericInput = new ArrayList<GenericInput>();
		
		genericInput = handler.getGenericInput();
		
		String response = genericInput.toString();
		
		return response;

	}
	
	@RequestMapping(value = "/xxe/sax", method = RequestMethod.POST, produces="text/plain")
	@ResponseBody
	public String xxeSAX(HttpServletRequest request) throws SAXException, ParserConfigurationException, IOException, TransformerException{
		
		SAXParser reader = SAXParserFactory.newInstance().newSAXParser();
		
		GenericInputParserHandler handler = new GenericInputParserHandler();

		reader.parse(new InputSource(request.getInputStream()), handler);
		
		ArrayList<GenericInput> genericInput = new ArrayList<GenericInput>();
		
		genericInput = handler.getGenericInput();
		
		String response = genericInput.toString();
		
		return response;

	}
	
	@RequestMapping(value = "/xxe/sax-SAFE", method = RequestMethod.POST, produces="text/plain")
	@ResponseBody
	public String xxeSAXSAFE(HttpServletRequest request) throws SAXException, ParserConfigurationException, IOException, TransformerException{
		
		SAXParserFactory spf = SAXParserFactory.newInstance();	
		spf.setFeature("http://xml.org/sax/features/external-general-entities", Boolean.FALSE);
		SAXParser reader = spf.newSAXParser();
		
		GenericInputParserHandler handler = new GenericInputParserHandler();

		reader.parse(new InputSource(request.getInputStream()), handler);
		
		ArrayList<GenericInput> genericInput = new ArrayList<GenericInput>();
		
		genericInput = handler.getGenericInput();
		
		String response = genericInput.toString();
		
		return response;

	}

}
