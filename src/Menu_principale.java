import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import java.awt.Canvas;
import java.awt.Button;


public class Menu_principale extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_principale frame = new Menu_principale();
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
	public Menu_principale() {
		setTitle("Simulateur de Prcessus");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInitialiserSimulateur = new JButton("Initialiser Simulateur");
		btnInitialiserSimulateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame inisialise =new Initialise_simul();
				inisialise.setVisible(true);
								
			}
		});
		btnInitialiserSimulateur.setBounds(197, 132, 137, 23);
		contentPane.add(btnInitialiserSimulateur);
		
		JButton btnJeuDessai = new JButton("Jeu d'essai");
		btnJeuDessai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame jeu_essai =new Jeu_essai();
				jeu_essai.setVisible(true);
				
			}
		});
		btnJeuDessai.setBounds(197, 188, 137, 23);
		contentPane.add(btnJeuDessai);
		
		JButton btnNewButton = new JButton("Resultat");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame resultat =new Resultat();
				resultat.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(197, 244, 137, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Quitter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(197, 303, 137, 23);
		contentPane.add(btnNewButton_1);
		
	}
}
