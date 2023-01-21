package quiz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Teacher extends JFrame {

	private JPanel contentPane;
	private JTextField txtQNo;
	private JTextField txtCAnswer;
	private JTextField txtAnswer1;
	private JTextField txtAnswer2;
	private JTextField txtAnswer3;
	private JTextField txtAnswer4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teacher frame = new Teacher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * connect the mySQL
	 */
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	
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
	
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from questions");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
	
		}
		catch(Exception e1)
		{
			System.out.println(e1);
		}
	}
	
	

	/**
	 * Create the frame.
	 */
	public Teacher() {
		Connect();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1184, 711);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add/ Edit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 82, 1164, 245);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setBounds(10, 45, 79, 14);
		panel_1.add(lblQuestion);
		
		JLabel lblCAnswer = new JLabel("Correct answer");
		lblCAnswer.setBounds(10, 88, 96, 14);
		panel_1.add(lblCAnswer);
		
		JTextArea txtAreaQuestion = new JTextArea();
		txtAreaQuestion.setBounds(116, 26, 1038, 50);
		panel_1.add(txtAreaQuestion);
		
		txtCAnswer = new JTextField();
		txtCAnswer.setBounds(116, 85, 453, 20);
		panel_1.add(txtCAnswer);
		txtCAnswer.setColumns(10);
		
		JLabel lblAnswer1 = new JLabel("Answer 1");
		lblAnswer1.setBounds(10, 119, 79, 14);
		panel_1.add(lblAnswer1);
		
		JLabel lblAnswer2 = new JLabel("Answer 2");
		lblAnswer2.setBounds(10, 150, 79, 14);
		panel_1.add(lblAnswer2);
		
		JLabel lblAnswer3 = new JLabel("Answer 3");
		lblAnswer3.setBounds(10, 181, 79, 14);
		panel_1.add(lblAnswer3);
		
		JLabel lblAnswer4 = new JLabel("Answer 4");
		lblAnswer4.setBounds(10, 217, 79, 14);
		panel_1.add(lblAnswer4);
		
		txtAnswer1 = new JTextField();
		txtAnswer1.setBounds(116, 116, 453, 20);
		panel_1.add(txtAnswer1);
		txtAnswer1.setColumns(10);
		
		txtAnswer2 = new JTextField();
		txtAnswer2.setColumns(10);
		txtAnswer2.setBounds(116, 147, 453, 20);
		panel_1.add(txtAnswer2);
		
		txtAnswer3 = new JTextField();
		txtAnswer3.setColumns(10);
		txtAnswer3.setBounds(116, 178, 453, 20);
		panel_1.add(txtAnswer3);
		
		txtAnswer4 = new JTextField();
		txtAnswer4.setColumns(10);
		txtAnswer4.setBounds(116, 214, 453, 20);
		panel_1.add(txtAnswer4);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String question,cAns,ans1,ans2,ans3,ans4;
				
				question = txtAreaQuestion.getText();
				cAns = txtCAnswer.getText();
				ans1 = txtAnswer1.getText();
				ans2 = txtAnswer2.getText();
				ans3 = txtAnswer3.getText();
				ans4 = txtAnswer4.getText();
				
				try {
						pst = con.prepareStatement("insert into questions(question,correct_ans,answer1,answer2,answer3,answer4)values(?,?,?,?,?,?)");
						pst.setString(1, question);
						pst.setString(2, cAns);
						pst.setString(3, ans1);
						pst.setString(4, ans2);
						pst.setString(5, ans3);
						pst.setString(6, ans4);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Question Added!!!");
						table_load();
						txtAreaQuestion.setText("");
						txtCAnswer.setText("");
						txtAnswer1.setText("");
						txtAnswer2.setText("");
						txtAnswer3.setText("");
						txtAnswer4.setText("");
						txtQNo.setText("");
						txtAreaQuestion.requestFocus();
					}
				catch(SQLException e1) {
						e1.printStackTrace();
				}
			}
		});
		
		btnNewButton.setBounds(736, 140, 96, 35);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtAreaQuestion.setText("");
				txtCAnswer.setText("");
				txtAnswer1.setText("");
				txtAnswer2.setText("");
				txtAnswer3.setText("");
				txtAnswer4.setText("");
				txtQNo.setText("");
				txtAreaQuestion.requestFocus();
				
			}
		});
		btnNewButton_1.setBounds(1010, 140, 96, 35);
		panel_1.add(btnNewButton_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String question,cAns,ans1,ans2,ans3,ans4,qNo;
				
				question = txtAreaQuestion.getText();
				cAns = txtCAnswer.getText();
				ans1 = txtAnswer1.getText();
				ans2 = txtAnswer2.getText();
				ans3 = txtAnswer3.getText();
				ans4 = txtAnswer4.getText();
				qNo = txtQNo.getText();
				
				
				try {
						pst = con.prepareStatement("update questions set question=?,correct_ans=?,answer1=?,answer2=?,answer3=?,answer4=? where q_no=?");
						pst.setString(1, question);
						pst.setString(2, cAns);
						pst.setString(3, ans1);
						pst.setString(4, ans2);
						pst.setString(5, ans3);
						pst.setString(6, ans4);
						pst.setString(7, qNo);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Question Updated!!!");
						table_load();
						txtAreaQuestion.setText("");
						txtCAnswer.setText("");
						txtAnswer1.setText("");
						txtAnswer2.setText("");
						txtAnswer3.setText("");
						txtAnswer4.setText("");
						txtQNo.setText("");
						txtAreaQuestion.requestFocus();
					}
				catch(SQLException e1) {
						e1.printStackTrace();
				}
				
			}
		});
		btnUpdate.setBounds(876, 139, 96, 36);
		panel_1.add(btnUpdate);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 11, 1164, 48);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblQNo = new JLabel("Question No.");
		lblQNo.setBounds(10, 19, 86, 14);
		panel_2.add(lblQNo);
		
		txtQNo = new JTextField();
		txtQNo.setBounds(115, 16, 86, 20);
		panel_2.add(txtQNo);
		txtQNo.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String qNo;
				
				qNo = txtQNo.getText();
				
				
				try {
						pst = con.prepareStatement("delete from questions where q_no=?");
						pst.setString(1, qNo);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Question Deleted!!!");
						table_load();
						txtAreaQuestion.setText("");
						txtCAnswer.setText("");
						txtAnswer1.setText("");
						txtAnswer2.setText("");
						txtAnswer3.setText("");
						txtAnswer4.setText("");
						txtQNo.setText("");
						txtAreaQuestion.requestFocus();
					}
				catch(SQLException e1) {
						e1.printStackTrace();
				}
				
			}
		});
		btnDelete.setBounds(413, 15, 89, 23);
		panel_2.add(btnDelete);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int id = Integer.parseInt(txtQNo.getText());
					
						pst = con.prepareStatement("select question,correct_ans,answer1,answer2,answer3,answer4 from questions where q_no='"+id+"'");
						ResultSet rs = pst.executeQuery();
					
					if(rs.next()) 
					{
						String question = rs.getString(1);
						String correct_ans = rs.getString(2);
						String answer1 = rs.getString(3);
						String answer2 = rs.getString(4);
						String answer3 = rs.getString(5);
						String answer4 = rs.getString(6);
						
						txtAreaQuestion.setText(question);
						txtCAnswer.setText(correct_ans);
						txtAnswer1.setText(answer1);
						txtAnswer2.setText(answer2);
						txtAnswer3.setText(answer3);
						txtAnswer4.setText(answer4);
					}
					else
					{
						txtAreaQuestion.setText("");
						txtCAnswer.setText("");
						txtAnswer1.setText("");
						txtAnswer2.setText("");
						txtAnswer3.setText("");
						txtAnswer4.setText("");						
					}
					
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(281, 15, 89, 23);
		panel_2.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 338, 1164, 323);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("EXIT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contentPane = new JPanel();
				if(JOptionPane.showConfirmDialog(contentPane,"Confirm if you want to exit","Login",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					//System.exit(0);
					Home hm = new Home();
					hm.setVisible(true);
					dispose();
				}
				
			}
		});
		btnNewButton_2.setBounds(1041, 665, 96, 35);
		panel.add(btnNewButton_2);
		table_load();
	}
}
