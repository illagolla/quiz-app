package quiz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Student extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	public String sName ="Isuru,";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
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
	public Student() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 473, 425);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Start Quiz");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sName = txtName.getText();
				Quiz_Form qf = new Quiz_Form();
				qf.show();
				dispose();
			}
		});
		btnNewButton.setBounds(150, 296, 177, 32);
		panel.add(btnNewButton);
		
		JLabel lbl1 = new JLabel("<html>All questions are Multiple choice questions<br/><br/>Choose most suitable answer", SwingConstants.CENTER);
		lbl1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBounds(62, 39, 398, 159);
		panel.add(lbl1);
		
		JButton btnReturn = new JButton("Return Home");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home hm = new Home();
				hm.show();
				dispose();
			}
		});
		btnReturn.setBounds(337, 379, 123, 23);
		panel.add(btnReturn);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblName.setBounds(35, 220, 56, 23);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(109, 220, 276, 22);
		panel.add(txtName);
		txtName.setColumns(10);
		}
}
