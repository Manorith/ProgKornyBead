package game.data;


import game.core.Player;



import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PlayerDOM implements PlayerDAO{

	private final String fileName;
	
	private static final String PPLAYER = "pplayer";
	private static final String NAME = "name";
	private static final String KNOWNQUESTIONS = "knownquestions";
	private static final String QUESTION = "question";
	
	public PlayerDOM(String fileName) {
		this.fileName = fileName;
	}
	
	public void playerSave(Player player) {

		try {
			Document doc = DOMfunctions.getDocument();
			Element rootElement = doc.createElement(PPLAYER);
			doc.appendChild(rootElement);

			Element name = doc.createElement(NAME);
			name.appendChild(doc.createTextNode(player.getName()));
			rootElement.appendChild(name);
			
			Element knownquestions = doc.createElement(KNOWNQUESTIONS);
			//rootElement.appendChild(knownquestions);
			for(String s : player.getKnownQuestions()){
				Element question = doc.createElement(QUESTION);
				question.appendChild(doc.createTextNode(s));
				knownquestions.appendChild(question);
			}
			rootElement.appendChild(knownquestions);
			
			DOMfunctions.saveInstance(fileName, doc);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Player getPPlayer() {
		Player pplayer = null;

		String name = "";
		LinkedList<String> questions = null;

		try {
			XMLEventReader eventReader = DOMfunctions.loadXMLReader(fileName);

			if (eventReader == null) {

				return null;
			}

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart() == (NAME)) {
						event = eventReader.nextEvent();
						name = event.asCharacters().getData();
					}
					if (startElement.getName().getLocalPart() == (KNOWNQUESTIONS)) {
						questions = new LinkedList<String>();
					}
					if (startElement.getName().getLocalPart() == (QUESTION)) {
						event = eventReader.nextEvent();
						questions.add(event.asCharacters().getData());
					}
					
				}

				if (event.isEndElement()) {

					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (PPLAYER)) {
						
						pplayer = new Player(name);
						for(String s : questions)
							pplayer.getKnownQuestions().add(s);
						
					}
				}
			}

		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 		
		return pplayer;

	}
	
}
