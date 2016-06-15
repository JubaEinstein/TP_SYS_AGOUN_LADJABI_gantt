import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;


public class Processus {
	String nomProcess;
	Etat etat;
	int dateEntre;
	int dateSortie;
	int tempsResidence;
	int tempsBloque;
	int tempsAttente;
	int tempsExec;
	int priorite;
	int quantum;
	Queue<Comportement> comportement;
	Queue<Comportement> restant;
	ArrayList<Comportement> fini;
	
	public Processus(String nom,int dateEntre,int tempsExec,
					int priorite)
	{
		nomProcess=nom;
		etat=Etat.NON_EXISTANT;
		this.dateEntre=dateEntre;
		dateSortie=-1;
		tempsResidence=0;
		tempsBloque=0;
		tempsAttente=0;
		this.tempsExec=tempsExec;
		this.priorite=priorite;
		quantum=0;
		comportement=new LinkedList();
		restant=new LinkedList();
		fini=new ArrayList();
	}
	void ajouterComportement(String comportement,Peripherique periph)
	{
		Comportement c=new Comportement();
		c.execute=0;
		try
		{
			int longeur=Integer.parseInt(comportement);
			c.duree=longeur;
			c.etat=Etat.PRET;
		}
		catch(NumberFormatException ex)
		{
			c.etat=etat.BLOQUE;
			c.peripherique=comportement;
			
			c.duree=periph.dureeES;
		}
		this.comportement.add(c);
		this.restant.add(c);
	}
	
}
