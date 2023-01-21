package quiz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Home extends JFrame {
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
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
		
		JButton studentBtn = new JButton("Student");
		studentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student st = new Student();
				st.show();
				dispose();
			}
		});
		studentBtn.setBounds(267, 211, 160, 52);
		panel.add(studentBtn);
		
		JButton teacherBtn = new JButton("Teacher");
		teacherBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeachersLogin lg = new TeachersLogin();
				lg.show();
				dispose();
				
			}
		});
		teacherBtn.setBounds(44, 211, 160, 52);
		panel.add(teacherBtn);
		
		JLabel lblNewLabel = new JLabel("MCQ HUB");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel.setBounds(135, 75, 203, 52);
		panel.add(lblNewLabel);
	}
}
