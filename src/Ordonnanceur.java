import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingDeque;


public class Ordonnanceur {
	LinkedList<Processus> toutLesProcess;
	ArrayList<LinkedBlockingDeque<Processus>> processusPret;
	Map<String,Peripherique> peripheriques;
	Processus actif;
	int tempsActuel;
	int quantum;
	int nbrPeriph;
	boolean processeurVide;		
	boolean finOrdonnancement;
	
	public Ordonnanceur()
	{
		toutLesProcess=new LinkedList<Processus>();
		processusPret=new ArrayList<LinkedBlockingDeque<Processus>>();
		for(int i=0;i<9;i++)
			processusPret.add(new LinkedBlockingDeque<Processus>());
		peripheriques=new TreeMap<String,Peripherique>();
		tempsActuel=0;
		quantum=Debut.tempsQuantum;
		nbrPeriph=Debut.nbPreriph;
		processeurVide=true;		
		finOrdonnancement=false;
	}
	
	void simuler()
	{
		Peripherique p=new Peripherique("f0",10);
		peripheriques.put(p.nomPeriph, p);
		p=new Peripherique("f1",10);
		peripheriques.put(p.nomPeriph, p);
		tempsActuel=0;
		quantum=30;
		nbrPeriph=2;
		while(!finOrdonnancement)
			bcl();
		
	}
	
	void bcl()
	{
		Comportement comportement;
		Peripherique es;
		Processus process;
		boolean finES=false;
		
		//Processus actif
		if (!processeurVide)
		{
			comportement=actif.restant.peek();//etape actuel
			if (comportement.duree==comportement.execute)//fin de cette etape
			{
				actif.fini.add(actif.restant.poll());//supression de l'etape actuel
				comportement=actif.restant.peek();//etape suivante
				
				if (comportement==null)//fin d'execution
				{
					actif.etat=Etat.FIN;
					actif.dateSortie=tempsActuel;
				}
				
				else//vers la fil bloqué
				{				
					actif.etat=Etat.BLOQUE;
					es=peripheriques.get(comportement.peripherique);
					es.ajouterProcessus(actif);
				}
				processeurVide=true;
			}
			else
			{
				if( actif.quantum==0)//vers la file d'attente
				{				
					actif.quantum=quantum;
					actif.priorite=0;
					actif.etat=Etat.PRET;
					processusPret.get(0).addLast(actif);
					processeurVide=true;
				}
			}
		}
		
		//files des processus bloqué
		Collection<Peripherique> parcours;
		parcours=peripheriques.values();
		Iterator<Peripherique> it;
		it=parcours.iterator();
		finES=false;
		while (it.hasNext())
		{
			es=it.next();
			process=es.fileBloque();   //dans la file bloqué 
			if (process!=null)
			{
				if (process.priorite<8)
					process.priorite++;
				process.etat=Etat.PRET;//vers la file pret avec sa priorité +1
				processusPret.get(process.priorite).addLast(process);
				finES=true;//fin d'une operation ES
			}
		}
		if (finES && !processeurVide)
		{
			actif.etat=Etat.PRET;
			processusPret.get(actif.priorite).addFirst(actif);
			processeurVide=true;
		}
		
		//entre d'une nouveau processus
		Iterator<Processus> i;
		boolean nouveauProcessus=false;
		i=toutLesProcess.iterator();
		while(i.hasNext())
		{
			process=i.next();
			if (process.dateEntre==tempsActuel)
			{
				process.etat=Etat.PRET;
				process.quantum=quantum;
				processusPret.get(process.priorite).add(process);
				nouveauProcessus=true;
			}
		}
		
		if (nouveauProcessus && !processeurVide)
		{
			actif.etat=Etat.PRET;
			processusPret.get(actif.priorite).addFirst(actif);
			processeurVide=true;
		}
		
		
		if (processeurVide)
		{
			int j=8;//choisir le processus le plus prioritaire pour l'execution
			while (j>=0 && processeurVide)
			{
				 //parcourir tout les files des processus
				//préts par ordre decroissant des priorités
				if (!processusPret.get(j).isEmpty())			     
				{
					//file non vide	
					process=processusPret.get(j).poll();
					comportement=process.restant.peek();//etape actuel	
					if (comportement.duree==comportement.execute)//fin de cette etape
					{
						process.fini.add(process.restant.poll());//supression de l'etape actuel
						comportement=process.restant.peek();//etape suivante
					}
					if (comportement==null)//fin d'execution du processus
					{
						process.etat=Etat.FIN;
						process.dateSortie=tempsActuel;
					}
					else							
					{	
						if (comportement.etat==Etat.BLOQUE)//vers la fil bloqué
						{
							process.etat=Etat.BLOQUE;
							es=peripheriques.get(comportement.peripherique);
							es.ajouterProcessus(process);
						}
						else	
						{	
							if( process.quantum==0)//vers la file d'attente
							{				
								process.quantum=quantum;
								process.priorite=0;
								process.etat=Etat.PRET;
								processusPret.get(0).addLast(process);
							}	
							else//executé la nouvelle etape
							{
								actif=process;
								actif.etat=Etat.ACTIF;
								processeurVide=false;
							}
						}			
					}
				}
				else j--;
			}
		}
		
		finOrdonnancement=true;//supposons que tout les processus ont terminé
		i=toutLesProcess.iterator();
		while(i.hasNext() && finOrdonnancement)
		{
			process=i.next();
			if (process.etat!=Etat.FIN)  //s'il existe au moin un process 
				finOrdonnancement=false; //qui n'a pas terminé son execution
		}								 //on continu l'execution
		
		
		if (!finOrdonnancement) //passage a t+1
		{
			tempsActuel++;
			i=toutLesProcess.iterator();
			while(i.hasNext())			//mettre a jour l'etat des processus
			{
				process=i.next();
				comportement=new Comportement();
				comportement.duree=0;
				comportement.execute=1;
				switch(process.etat)
				{
				case NON_EXISTANT:					
					comportement.etat=Etat.NON_EXISTANT;					
					break;
				case PRET:
					comportement.etat=Etat.PRET;
					process.tempsResidence++;
					process.tempsAttente++;					
					break;
				case BLOQUE:
					comportement.etat=Etat.BLOQUE;
					process.tempsResidence++;
					process.tempsBloque++;					
					break;
				case ACTIF:
					comportement.etat=Etat.ACTIF;
					process.tempsResidence++;
					process.restant.peek().execute++;
					process.quantum--;					
					break;
				case FIN:
					comportement.etat=Etat.FIN;
					break;
				}
				process.fini.add(comportement);
				
			}
			

			i=toutLesProcess.iterator();
			while(i.hasNext())			
			{
				process=i.next();
				int index=0;
				while ((index+1)<process.fini.size())
				{
					if (process.fini.get(index).etat==process.fini.get(index+1).etat)
					{
						process.fini.get(index).duree++;
						process.fini.remove(index+1);
					}
					else
						index++;
				}
				
			}
			
			
			
			it=parcours.iterator();
			finES=false;
			while (it.hasNext())   //mettre a jour l'etat des peripheriqus
			{
				es=it.next();
				es.incrementerCompteur();
			}
		}
	}	
	
}











