package RetroPingPong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InstructionWindow2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstructionWindow2 frame = new InstructionWindow2();
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
	public InstructionWindow2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(210, 40, 700, 500);
		contentPane = new JPanel();
		setTitle("Retro Ping Pong - Instruction Window");
		setResizable(false);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setBounds(284, 63, 117, 39);
		lblInstructions.setForeground(Color.white);
		lblInstructions.setFont(new Font("Serif", Font.BOLD, 20));
		contentPane.add(lblInstructions);

		JLabel lblinTheSingle = new JLabel("<HTML>In the two player mode player one will be playing using the left paddle and player two will be playing using the right paddle. To move the left paddle up and down use the W and S keys. To move the right paddle up and down use the UP and DOWN arrow keys. To pause press the M key on the keyboard. The first person to get 3 wins, wins the game.<HTML>");
		lblinTheSingle.setBounds(40, 113, 615, 178);
		lblinTheSingle.setForeground(Color.white);
		lblinTheSingle.setFont(new Font("Serif", Font.BOLD, 20));
		contentPane.add(lblinTheSingle);

		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				TwoPlayerGame twoplayergame = new TwoPlayerGame();
				TwoPlayerGame.NewScreen(null);
			}
		});
		
		btnContinue.setBounds(481, 375, 154, 52);
		btnContinue.setForeground(Color.black);
		btnContinue.setBackground(Color.white);
		btnContinue.setFont(new Font("Serif", Font.BOLD, 20));
		contentPane.add(btnContinue);
	}
}
