import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;


public class Gantt extends JPanel {


	/**
	 * Create the panel.
	 */
	public Gantt(Ordonnanceur ordonnanceur) {
		setBackground(Color.WHITE);
		setLayout(null);
		Color colorInexistant=Color.gray;
		Color colorBloque=Color.red;
		Color colorActif=Color.green;
		final Object[][] data = new Object[Debut.nbProcessus][6] ;
		String title[] = {"Processus","Date d'entrer", "Temps de sortie","Temps de résidence","Temps de blocage","Temps de d'attente"};
		final JTable tableau = new JTable(data, title);
		tableau.setEnabled(false);
		
		tableau.setForeground(Color.BLACK);
		tableau.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane(tableau);
		
		scrollPane.setSize(966, 106);
		scrollPane.setLocation(362, 38);
		add(scrollPane);
		
		
	}
	protected void paintComponent(Graphics g) {
		Graphics2D surface = (Graphics2D) g;  
		surface.setColor(Color.gray);
		surface.setFont(new Font("Arrial", Font.ITALIC+Font.BOLD, 48));
	    surface.drawString("Resultat", 10, 50);
	    
	    surface.setColor(Color.BLACK);
	    surface.setStroke(new BasicStroke(3));
		 g.drawLine(80,650, 80, 250);
		 //!----dessin axe y--
		 g.drawLine(80, 250,74,260);		
		 g.drawLine(80, 250,86,260);
		 //!----dessin axe x--
	     g.drawLine(1290,644,1300, 650);
	     g.drawLine(1290,656,1300, 650);
	     //----------------------
	     g.drawLine(80,650,1300, 650);
	   
	     int time_dernier=239;
	     

	     float echelle_tempf = (float)1300/time_dernier;
	     int echelle_temp = 1210/time_dernier;
	     
	     int echelle_prcessus= 400/(Debut.nbProcessus+1);
	     float  echelle_prcessusf= (float)400/(Debut.nbProcessus+1);
	     
	     if ((echelle_prcessusf-echelle_prcessus)>=0.5) echelle_prcessus++;
	     
	     float[] motif = {5, 5, 5, 5, 5, 5};
	
	     for(int i=650;i>250;i--){
	    	 if ((i-650)%echelle_prcessus==0 && (i-650)!=0 && (650-i)/echelle_prcessus<=Debut.nbProcessus){
	    		 
	    	   	 surface.setStroke(new BasicStroke(1));
	   	    	 surface.setFont(new Font("Arrial", Font.BOLD, 10));
 		    	 surface.drawString("p0", 55,i );
 		        surface.setStroke(new BasicStroke(3));
 		    	 g.drawLine(78,i, 82,i );
 		    	surface.setStroke(new BasicStroke(1));
	    	 }
	    	 
	    	 
	     }
	     
	     for (int i=80 ; i<1300;i++){
	    	 if ((i-80)%((int)echelle_tempf*10)==0 && (i-80)/(int)echelle_tempf<time_dernier+(int)echelle_prcessusf){
	    		 surface.setStroke(
	             new BasicStroke(1,                  // épaisseur
	                   BasicStroke.CAP_ROUND,   // extrémité
	                   BasicStroke.JOIN_MITER,   // intersection
	                   15,                                       // limite angulaire
	                   motif,                   // motif de remplissage                
	                   0                          // phase de remplissage
	             ));
		    	 //g.setColor();
	    		 g.drawLine(i,647, i,257 );
		    	 
		    	 surface.setStroke(new BasicStroke(1));
		    	 surface.setFont(new Font("Arrial", Font.BOLD, 10));
		    	 surface.drawString(Integer.toString((i-80)/echelle_temp), i,670 );
	    	 }
	     }
	     surface.setFont(new Font("Arial Unicode MS", Font.BOLD, 11));
	     surface.drawString("Temps", 1310,655 );
	     surface.drawString("Processus", 55,240 );
	     surface.setStroke(new BasicStroke(0));
	     	  
	   }   
	
}
