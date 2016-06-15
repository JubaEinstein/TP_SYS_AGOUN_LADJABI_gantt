import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.xml.crypto.Data;

import org.omg.CORBA.FREE_MEM;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class Resultat extends JFrame {

	private JPanel contentPane;
	Ordonnanceur ordonnanceur;
	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 */
	public Resultat() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Diagramme de gantt");
		setBounds(100, 100, 1390, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(161, 51, 89, 23);
		contentPane.add(btnNewButton);
		//simuler();
		this.setContentPane(new Gantt(ordonnanceur));
		setLocationRelativeTo(null); 
		
		
	}
	/*public void simuler(){
		
		Processus process;
		int dateEntre,tempsExec,priorite;
		String[] comportement;
		ordonnanceur =new Ordonnanceur();
		for (int i=0;i<Debut.nbPreriph;i++){
		
			Peripherique periph =new Peripherique((String)Debut.periph[i][0], (int)Debut.periph[i][1]);
			ordonnanceur.peripheriques.put(periph.nomPeriph,periph);
		}
		for(int i=0;i<Debut.nbProcessus;i++)
		{
			dateEntre=(int)(Debut.processus[i][2]);
			tempsExec=(int)(Debut.processus[i][3]);
			priorite=(int)(Debut.processus[i][1]);
			process=new Processus((String)Debut.processus[i][0],dateEntre,tempsExec,priorite);
			comportement=((String)Debut.processus[i][4]).split("-");
			for(int j=0;j<comportement.length;j++)
				process.ajouterComportement(comportement[j],ordonnanceur.peripheriques.get(comportement[j]));
			ordonnanceur.toutLesProcess.add(process);
		}
		ordonnanceur.simuler();
		
	}*/

}

