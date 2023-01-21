package quiz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.sql.*;

public class Quiz_Form extends JFrame {
	
	String[] questions = {
			"Which company created Java?",
			"Which year was Java created?",
			"What was Java originally called?",
			"Who is credited with creating Java?"
		};
	String[][] options = {
			{"Sun Microsystems","Starbucks","Microsoft","Alphabet"},
			{"1989","1996","1972","1492"},
			{"Apple","Latte","Oak","Koffing"},
			{"Steve Jobs","Bill Gates","James Gosling","Mark Zuckerburg"}
		};
	String[] answers = {
			"Sun Microsystems",
			"1996",
			"Oak",
			"James Gosling"
		};
	
	
	
	int index;
	int correct;
	int total_questions = questions.length;
	String answer;
	int correct_guesses=0;
	
	String id= "1";
	
	Connection con;
	ResultSet rs;
	PreparedStatement pst;
	
	JPanel panel = new JPanel();
	JPanel panel_Q_Container = new JPanel();
	JLabel lbl_Question = new JLabel("Question Here ???");
	JRadioButton radioButton1 = new JRadioButton("JRadioButton1");
	JRadioButton radioButton2 = new JRadioButton("JRadioButton2");
	JRadioButton radioButton3 = new JRadioButton("JRadioButton3");
	JRadioButton radioButton4 = new JRadioButton("JRadioButton4");
	JButton jButton_Next_Q = new JButton("Next");
	JButton btnReturn = new JButton("Return home");
	
	private JPanel contentPane;
	ButtonGroup buttonGroup = new ButtonGroup();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quiz_Form frame = new Quiz_Form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizapp","root","");
		}
		catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	

/**
	 * Create the frame.
	 */
	public Quiz_Form() {
		nextQuestion();
		
//		jButton_Next_QActionPerformed(null);
		
		setTitle("MCQ HUB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 473, 425);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		panel_Q_Container.setBackground(new Color(230, 230, 250));
		panel_Q_Container.setBounds(0, 0, 473, 171);
		panel.add(panel_Q_Container);
		panel_Q_Container.setLayout(null);
		
		
		lbl_Question.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Question.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_Question.setBounds(10, 11, 453, 149);
		panel_Q_Container.add(lbl_Question);
		
		
		buttonGroup.add(radioButton1);		
		radioButton1.setBounds(10, 210, 450, 23);
		radioButton1.setBackground(Color.WHITE);
		panel.add(radioButton1);
		
		buttonGroup.add(radioButton2);		
		radioButton2.setBounds(10, 236, 450, 23);
		radioButton2.setBackground(Color.WHITE);
		panel.add(radioButton2);
		
		buttonGroup.add(radioButton3);			
		radioButton3.setBounds(10, 262, 450, 23);
		radioButton3.setBackground(Color.WHITE);
		panel.add(radioButton3);		
		
		buttonGroup.add(radioButton4);		
		radioButton4.setBounds(10, 288, 450, 23);
		radioButton4.setBackground(Color.WHITE);
		panel.add(radioButton4);
		jButton_Next_Q.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		
		jButton_Next_Q.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if(jButton_Next_Q.getText().equals("Restart The Quiz"))
			        {
			            // restart the quiz
					 	index = 0;
			            jButton_Next_Q.setText("Next");
			            panel_Q_Container.setBackground(new java.awt.Color(240, 255, 255));
			            correct = 0;
			            visibleRadioB(true);
			            nextQuestion();
			            enableRbuttons(true);
			            
			        }
				 else if(jButton_Next_Q.getText().equals("Results"))
			        {
			    	  	enableRbuttons(false);
			        	// display the user score
			    	  	Student std = new Student();
			    	  	String name = std.sName;
			            lbl_Question.setText(name + " your Score: " + correct + " / " + questions.length);
			            if(correct >= (float) questions.length/2)
			            {
			               panel_Q_Container.setBackground(Color.green);
			            }
			            else{
			                panel_Q_Container.setBackground(Color.red);
			            }
			            
			            jButton_Next_Q.setText("Restart The Quiz");
			        }
			        
				 else if(jButton_Next_Q.getText().equals("Finish"))
			        {
			        	corrects();
			        	enableRbuttons(false);
			        	buttonGroup.clearSelection();
			        	
			        	radioButton1.setText("");
						radioButton2.setText("");
						radioButton3.setText("");
						radioButton4.setText("");
			        	
						// display the user score
			            lbl_Question.setText("Finished..!");
			            
			            jButton_Next_Q.setText("Results");
			            visibleRadioB(false);
			        }
			        
				 else if(jButton_Next_Q.getText().equals("Next"))
			        {
			               // enable radio buttons
			        	 corrects();
			        	 buttonGroup.clearSelection();
			        	 index++;
			             nextQuestion();
			             enableRbuttons(true);
			             if(index == questions.length-1){
			                 jButton_Next_Q.setText("Finish");
			                 
			             }
			             
			             
			        	}
			}
		});
		
		jButton_Next_Q.setBounds(150, 327, 175, 30);
		panel.add(jButton_Next_Q);
		
		btnReturn.setBounds(337, 379, 123, 23);
		panel.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home hm = new Home();
				hm.show();
				dispose();
			}
		});
		
		
	}
	
	public void start()
	{
		
	}
	
	public void corrects() 
	{
		
		if(radioButton1.isSelected())
        {
       	 if(radioButton1.getText()==answers[index])
       	 {
       		 correct++;
       	 }
        }
        if(radioButton2.isSelected())
        {
       	 if(radioButton2.getText()==answers[index])
       	 {
       		 correct++;
       	 }
        }
        if(radioButton3.isSelected())
        {
       	 if(radioButton3.getText()==answers[index])
       	 {
       		 correct++;
       	 }
        }
        if(radioButton4.isSelected())
        {
       	 if(radioButton4.getText()==answers[index])
       	 {
       		 correct++;
       	 }
        }
	}
	
	public void visibleRadioB(boolean yes_or_no)
	{
		radioButton1.setVisible(yes_or_no);
		radioButton2.setVisible(yes_or_no);
		radioButton3.setVisible(yes_or_no);
		radioButton4.setVisible(yes_or_no);
	}
	
	public void nextQuestion()
	{
		//System.out.println(index);			
			lbl_Question.setText(questions[index]);
			radioButton1.setText(options[index][0]);
			radioButton2.setText(options[index][1]);
			radioButton3.setText(options[index][2]);
			radioButton4.setText(options[index][3]);
			
			/*
			try
			{
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from questions where q_no='"+id+"'");
				
				while(rs.next())
				{
					lbl_Question.setText(rs.getString(1));
					radioButton1.setText(rs.getString(2));
					radioButton2.setText(rs.getString(3));
					radioButton3.setText(rs.getString(4));
					radioButton4.setText(rs.getString(5));
				}
				
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e);
			}
			*/
			
			
	}
	
    public void enableRbuttons(boolean yes_or_no)
    {
        radioButton1.setEnabled(yes_or_no);
        radioButton2.setEnabled(yes_or_no);
        radioButton3.setEnabled(yes_or_no);
        radioButton4.setEnabled(yes_or_no);
    }
}
