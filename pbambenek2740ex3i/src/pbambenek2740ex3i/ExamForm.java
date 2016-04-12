package pbambenek2740ex3i;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class ExamForm extends JFrame implements ActionListener, ListSelectionListener, FocusListener {

	private JPanel contentPane;
	private JList responsesList;
	private DefaultListModel responsesListModel;
	private JLabel resultLabel;
	private JLabel questNumLabel;
	private JButton prevButton;
	private JButton nextButton;
	private JButton calcPassButton;
	private JButton calcCorrectButton;
	private JButton calcIncorrectButton;
	private JButton listIncorrectButton;
	private JTextField inputAnswerTextField;
	private DriverExam exam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExamForm frame = new ExamForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExamForm() {
		setTitle("PBambenek Ex3I Driver Exam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResponses = new JLabel("Responses:");
		lblResponses.setBounds(10, 11, 79, 14);
		contentPane.add(lblResponses);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(94, 11, 79, 14);
		contentPane.add(lblResult);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setEnabled(false);
		list.setForeground(SystemColor.textInactiveText);
		list.setBackground(SystemColor.control);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(10, 36, 24, 180);
		getContentPane().add(list);
		
		responsesList = new JList();
		responsesList.addListSelectionListener(this);
		responsesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		responsesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		responsesList.setBounds(44, 34, 24, 186);
		contentPane.add(responsesList);
		
		resultLabel = new JLabel("");
		resultLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		resultLabel.setBounds(94, 35, 170, 26);
		contentPane.add(resultLabel);
		
		questNumLabel = new JLabel("#0:");
		questNumLabel.setBounds(10, 232, 24, 14);
		contentPane.add(questNumLabel);
		
		calcPassButton = new JButton("Pass");
		calcPassButton.addActionListener(this);
		calcPassButton.setBounds(94, 72, 117, 23);
		contentPane.add(calcPassButton);
		
		calcCorrectButton = new JButton("Correct");
		calcCorrectButton.addActionListener(this);
		calcCorrectButton.setBounds(94, 106, 117, 23);
		contentPane.add(calcCorrectButton);
		
		calcIncorrectButton = new JButton("Incorrect");
		calcIncorrectButton.addActionListener(this);
		calcIncorrectButton.setBounds(94, 140, 117, 23);
		contentPane.add(calcIncorrectButton);
		
		listIncorrectButton = new JButton("List Incorrect");
		listIncorrectButton.addActionListener(this);
		listIncorrectButton.setBounds(94, 174, 117, 23);
		contentPane.add(listIncorrectButton);
		
		prevButton = new JButton("Prev");
		prevButton.addActionListener(this);
		prevButton.setEnabled(false);
		prevButton.setBounds(78, 228, 62, 23);
		contentPane.add(prevButton);
		
		nextButton = new JButton("Next");
		nextButton.addActionListener(this);
		nextButton.setBounds(78, 255, 62, 23);
		contentPane.add(nextButton);
		
		inputAnswerTextField = new JTextField();
		inputAnswerTextField.addFocusListener(this);
		inputAnswerTextField.setBounds(44, 227, 24, 24);
		contentPane.add(inputAnswerTextField);
		inputAnswerTextField.setColumns(10);
		
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt");
		this.exam = mapper.getDriverExam();
		
		this.responsesListModel = exam.getAnswers();
		responsesList.setModel(this.responsesListModel);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == nextButton) {
			do_nextButton_actionPerformed(arg0);
		}
		if (arg0.getSource() == prevButton) {
			do_prevButton_actionPerformed(arg0);
		}
		if (arg0.getSource() == listIncorrectButton) {
			do_listIncorrectButton_actionPerformed(arg0);
		}
		if (arg0.getSource() == calcIncorrectButton) {
			do_calcIncorrectButton_actionPerformed(arg0);
		}
		if (arg0.getSource() == calcCorrectButton) {
			do_calcCorrectButton_actionPerformed(arg0);
		}
		if (arg0.getSource() == calcPassButton) {
			do_calcPassButton_actionPerformed(arg0);
		}
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource() == responsesList) {
			do_responsesList_valueChanged(arg0);
		}
	}
	
	protected void do_calcPassButton_actionPerformed(ActionEvent arg0) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		}
		else {
			if (exam.passed()) resultLabel.setText("You passed");
			else resultLabel.setText("You failed");
		}
	}
	
	protected void do_calcCorrectButton_actionPerformed(ActionEvent arg0) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		}
		else {
			resultLabel.setText("Total correct: " + Integer.toString(exam.totalCorrect()));
		}
	}
	
	protected void do_calcIncorrectButton_actionPerformed(ActionEvent arg0) {
		this.exam.setResponses((DefaultListModel) responsesList.getModel());
		int invalid = this.exam.validate();
		if (invalid >= 0) {
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		}
		else {
			resultLabel.setText("Total incorrect: " + Integer.toString(exam.totalIncorrect()));
		}
	}
	
	protected void do_listIncorrectButton_actionPerformed(ActionEvent arg0) {
		int[] missed;
		String result = "Incorrect: ";
		missed = exam.questionsMissed();
		int i = 0;
		while (i < missed.length && missed[i] != 0)
		{
			result += missed[i] + " ";
			i++;
		}
		resultLabel.setText(result);
	}
	
	protected void do_prevButton_actionPerformed(ActionEvent arg0) {
		this.responsesListModel.setElementAt(
                inputAnswerTextField.getText().toUpperCase(), 
                responsesList.getSelectedIndex());
        responsesList.setSelectedIndex(responsesList.getSelectedIndex() - 1);
        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());    

        nextButton.setEnabled(true);
        if (responsesList.getSelectedIndex() == 0) 
            prevButton.setEnabled(false);
        inputAnswerTextField.requestFocus();
	}
	
	protected void do_nextButton_actionPerformed(ActionEvent arg0) {
		this.responsesListModel.setElementAt(
                inputAnswerTextField.getText().toUpperCase(), 
                responsesList.getSelectedIndex());
        responsesList.setSelectedIndex(responsesList.getSelectedIndex() + 1);
        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());
        
        prevButton.setEnabled(true);
        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
            nextButton.setEnabled(false);
        inputAnswerTextField.requestFocus();
	}
		
	protected void do_responsesList_valueChanged(ListSelectionEvent arg0) {
		 questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
	        inputAnswerTextField.setText((String)responsesList.getSelectedValue());    

	        prevButton.setEnabled(true);
	        nextButton.setEnabled(true);
	        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
	            nextButton.setEnabled(false);
	        if (responsesList.getSelectedIndex() == 0) 
	            prevButton.setEnabled(false);
	        inputAnswerTextField.requestFocus();        
	}
	
	public void focusGained(FocusEvent arg0) {
		if (arg0.getSource() == inputAnswerTextField) {
			do_inputAnswerTextField_focusGained(arg0);
		}
	}
	
	public void focusLost(FocusEvent arg0) {
	}
	
	protected void do_inputAnswerTextField_focusGained(FocusEvent arg0) {
		inputAnswerTextField.selectAll();
	}
}
