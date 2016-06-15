
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class Initialise_simul extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Initialise_simul frame = new Initialise_simul();
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
	public Initialise_simul()  {
		setTitle("Initialisation des parametre");
		setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		
		final Object[][] data = new Object[20][2] ;
		final String title[] = {"Peripherique","Temps"};
		final JTable tableau = new JTable(new DefaultTableModel(data, title));
		final JScrollPane scrollPane = new JScrollPane(tableau);
		
		setLocationRelativeTo(null);
		setBounds(100, 100, 624, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		textField.setBounds(231, 41, 130, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		final JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Debut.nbPreriph=Integer.parseInt(textField_2.getText());
				Debut.tempsQuantum=Integer.parseInt(textField.getText());
				
			}
		});
		btnValider.setBounds(442, 57, 101, 24);
		btnValider.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		contentPane.add(btnValider);
		
		JLabel lblNewLabel = new JLabel("Temps Quantm:");
		lblNewLabel.setBounds(89, 45, 109, 17);
		contentPane.add(lblNewLabel);
		
		JButton btnOk = new JButton("OK");
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				Debut.periph =new Object[Debut.nbPreriph][2];
				for (int i=0 ;i<Debut.nbPreriph;i++)
					for(int j=0;j<2;j++){
						Object o=tableau.getValueAt(i,j);
						Debut.periph [i][j]=o;
					}
				
				}
			
			});
		
		btnOk.setBounds(258, 331, 89, 23);
		contentPane.add(btnOk);
		
		 
		 
		 
		 JLabel lblNombreDePreepherique = new JLabel("Nombre de Peripherique:");
		 lblNombreDePreepherique.setBounds(89, 79, 140, 17);
		 contentPane.add(lblNombreDePreepherique);
		 
		 textField_2 = new JTextField();
		 textField_2.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		 textField_2.setColumns(10);
		 textField_2.setBounds(231, 76, 130, 24);
		 contentPane.add(textField_2);
		 
		scrollPane.setSize(546, 179);
		scrollPane.setLocation(24, 134);
		contentPane.add(scrollPane);
		
		
		
	}
}
