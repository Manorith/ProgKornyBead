package game.data;

import game.core.Question;

import java.util.LinkedList;


public interface QuestionDAO {
	
	void questionSave(LinkedList<Question> questions);
	
	LinkedList<Question> getQuestionList();

}
