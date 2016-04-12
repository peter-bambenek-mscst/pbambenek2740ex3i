package pbambenek2740ex3i;

import javax.swing.DefaultListModel;

public class DriverExam {

	private char[] answers;
	private char[] responses;
	private final double requiredPct = 0.7;
	
	//////////////////////////////
	// testing
	//private char[] responses = {'B', 'D', 'A', 'A', 'B', 'A', 'B', 'A', 'C', 'D'};
	// testing
	//////////////////////////////

	public DriverExam(char[] answers) {
		this.answers = new char[answers.length];
		for(int i = 0; i < answers.length; i++) {
			this.answers[i] = answers[i];
		}
	}

	public DriverExam(DefaultListModel answers) {
		this.answers = new char[answers.getSize()];
		for (int i = 0; i < answers.getSize(); i++) {
			String a = (String) answers.getElementAt(i);
			this.answers[i] = a.charAt(0);
		}
	}

	// copy elements from Strings in DefaultListModel to char[]
	public void setResponses(DefaultListModel responses) {
		this.responses = new char[responses.getSize()];
		for (int i = 0; i < responses.getSize(); i++) {
			String r = (String) responses.getElementAt(i);
			this.responses[i] = r.charAt(0); // puts a string into an array
		}
	}

	public DefaultListModel getAnswers() {
		DefaultListModel answersListModel = new DefaultListModel();
		// get character value from each item in the answers array and addElement to listmodel
		for(int i = 0; i < answers.length; i++)
	        {
			 String str = String.valueOf(answers[i]); //convert char to string
			 answersListModel.addElement(str);
			}
		return answersListModel;
	}

	// validate(): return index of first element found in responses[] that is not A, B, C, or D
	public int validate() {
		int invalidEntry = -1;
		int i = 0;	// substring and counter
		while (i < answers.length)
		{
			if (responses[i] != 'A' && responses[i] != 'B' && responses[i] != 'C' && responses[i] != 'D') 
			{
				invalidEntry = i;
			break;
			}
		i++;	
		}
		return invalidEntry;   // return index of first invalid entry
	}		

	// passed(): call totalCorrect(). Return 'true' **** IF totalCorrect() >= requiredPct * the number of questions *****
	public boolean passed()  {
		double tc = totalCorrect();
		double al = answers.length;
		double percentageCorrect = tc/al;
		return (percentageCorrect >= requiredPct);  
	}

	public int totalCorrect() {
		int numCorrect = 0;  
		for (int i = 0; i < answers.length; i++)  
			if (responses[i] == answers[i])  
				numCorrect++;  
		return numCorrect;  
	}

	public int totalIncorrect()  {
		int numIncorrect = 0;  
		for (int i = 0; i < answers.length; i++)  
			if (responses[i] != answers[i])  
				numIncorrect++;  
		return numIncorrect;  
	}

	public int[] questionsMissed() {
		int[] missed = {0,0,0,0,0,0,0,0,0,0};
		int m = 0;
		for (int i = 0; i < answers.length; i++) 
		{
			if (responses[i] != answers[i]) // if answer is incorrect
			{
				missed[m] = i + 1;
				m++;
			}
		}
		return missed; // return actual number of the list - example- 1,2,3,4,5,6,7,8,9,10
	}

}
