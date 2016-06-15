import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class Jeu_essai extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnOk;
	private JButton btnLireAPartir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jeu_essai frame = new Jeu_essai();
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
	public Jeu_essai() {
		
		setTitle("Saisie Jeu d'essai");
		final Object[][] data = new Object[20][5] ;
		String title[] = {"Processus","Priorite", "Temps d'arriver","Temps d'execution","Comportement"};
		final JTable tableau = new JTable(new DefaultTableModel(data, title));
		final JScrollPane scrollPane = new JScrollPane(tableau);
		setLocationRelativeTo(null);
		setBounds(100, 100, 618, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDePocessus = new JLabel("Nombre de pocessus");
		lblNombreDePocessus.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		lblNombreDePocessus.setBounds(94, 42, 134, 20);
		contentPane.add(lblNombreDePocessus);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		textField.setBounds(220, 42, 107, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		final JButton btnValider = new JButton("Valider");
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Debut.nbProcessus=Integer.parseInt(textField.getText());
				
				btnValider.setEnabled(false);
			}
		});
		btnValider.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		btnValider.setBounds(364, 41, 89, 23);
		contentPane.add(btnValider);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Debut.processus= new Object[Debut.nbProcessus][5];
				for (int i=0 ;i<Debut.nbProcessus;i++)
					for(int j=0;j<5;j++){
						Debut.processus [i][j]=tableau.getValueAt(i,j);
					}
			}
		});
		btnOk.setBounds(309, 347, 89, 23);
		contentPane.add(btnOk);
		
		btnLireAPartir = new JButton("Lire a partir d'un fichier");
		btnLireAPartir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				scrollPane.setVisible(false);
				JFileChooser choix = new JFileChooser();
				Component parent = null;
				int retour=choix.showOpenDialog(parent);
				if(retour==JFileChooser.APPROVE_OPTION){
				// un fichier a été choisi (sortie par OK)
				// nom du fichier  choisi 
					choix.getSelectedFile().getName();
			    // chemin absolu du fichier choisi
				choix.getSelectedFile().getAbsolutePath();
				Debut.pathFile= choix.getSelectedFile().getAbsolutePath();
				String fichier=new String();
				String[] ligneProcess =new String[5];
				
				try{
					InputStream ips=new FileInputStream(Debut.pathFile); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String lecture=new String();
					while ((lecture=br.readLine())!=null){
						fichier+=lecture;
						System.out.print(lecture);
					}			
					br.close(); 
				}		
				catch (Exception e){
					System.out.println(e.toString());
				}
				
				ligneProcess=fichier.split(";");
				
				Debut.processus= new Object[Debut.nbProcessus][5]; 
				for(int i=0;i<Debut.nbProcessus;i++)
				{
					Debut.processus[i]=ligneProcess[i].split(":");
					
				}
				}else;
				dispose();
			}
		});
		btnLireAPartir.setBounds(127, 347, 172, 23);
		contentPane.add(btnLireAPartir);
		
		scrollPane.setSize(546, 200);
		scrollPane.setLocation(28, 109);
		contentPane.add(scrollPane);
		
	}
}
