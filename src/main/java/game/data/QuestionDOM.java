package game.data;

import game.core.Answer;
import game.core.Question;
import game.core.noCorrectAnswerException;
import game.core.noSuchAnswerException;

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

public class QuestionDOM implements QuestionDAO {

	private final String fileName;

	private static final String QUESTIONS = "questions";
	private static final String QUESTION = "question";
	private static final String TEXT = "text";
	private static final String ANSWERS = "answers";
	private static final String ANSWER = "answer";
	private static final String CORRECT = "correct";
	private static final String DIFFICULTY = "difficulty";
	private static final String TYPE = "type";
	


	public QuestionDOM(String fileName) {
		this.fileName = fileName;
	}
	


	public void questionSave(LinkedList<Question> questions) {

		try {
			Document doc = DOMfunctions.getDocument();
			Element rootElement = doc.createElement(QUESTIONS);
			doc.appendChild(rootElement);

			for (Question q : questions) {
				Element question = doc.createElement(QUESTION);
				rootElement.appendChild(question);

				Element text = doc.createElement(TEXT);
				text.appendChild(doc.createTextNode(q.getQuestion()));
				question.appendChild(text);

				Element answers = doc.createElement(ANSWERS);
				//question.appendChild(answers);
				for (Answer a : q.getAnswers()) {
					Element answer = doc.createElement(ANSWER);
					answer.appendChild(doc.createTextNode(a.getAnswer()));
					answers.appendChild(answer);
				}
				question.appendChild(answers);

				Element correct = doc.createElement(CORRECT);
				try {
					correct.appendChild(doc.createTextNode(q.getCorrect()
							.getAnswer()));
				} catch (DOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				question.appendChild(correct);

				Element difficulty = doc.createElement(DIFFICULTY);
				difficulty.appendChild(doc.createTextNode(Integer.toString(q
						.getDifficulty())));
				question.appendChild(difficulty);

			}

			DOMfunctions.saveInstance(fileName, doc);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public LinkedList<Question> getQuestionList() {
		LinkedList<Question> questionList = new LinkedList<Question>();

		String text = "";
		LinkedList<Answer> answers = new LinkedList<Answer>();
		int difficulty = 0;
		String type = "";
		Answer correct = null;
		Question question = null;
		
		try {
			XMLEventReader eventReader = DOMfunctions.loadXMLReader(fileName);

			if (eventReader == null) {

				return new LinkedList<>();
			}

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();

					if (startElement.getName().getLocalPart() == (TEXT)) {
						event = eventReader.nextEvent();
						text = event.asCharacters().getData();
					}
					if (startElement.getName().getLocalPart() == (ANSWERS)) {
						answers.clear();
					}
					if (startElement.getName().getLocalPart() == (ANSWER)) {
						event = eventReader.nextEvent();
						answers.add(new Answer(event.asCharacters().getData()));
					}
					if (startElement.getName().getLocalPart() == (CORRECT)) {
						event = eventReader.nextEvent();
						correct = new Answer(event.asCharacters().getData());
						
					}
					if (startElement.getName().getLocalPart() == (DIFFICULTY)) {
						event = eventReader.nextEvent();
						difficulty = Integer.parseInt(event.asCharacters()
								.getData());
					}
					if (startElement.getName().getLocalPart() == (TYPE)) {
						event = eventReader.nextEvent();
						type = event.asCharacters().getData();
					}
				}

				if (event.isEndElement()) {

					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart() == (QUESTION)) {

						
						question = new Question(text, answers, difficulty, type, correct);
						
						//question.printAnswer();
						
						questionList.add(question);
					}
				}
			}

		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

			
		 		
		return questionList;

	}
}
