package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chromosome {
	private  int nAlleles = 40; //the size of dna
    private List<Integer> dna; //list of numbers between 0 and 9
	
    //--------CONSTRUCTORS------------
    
	public Chromosome()
	{
		this.dna = new ArrayList<>(nAlleles);
		
		this.generateDNA();
	}
	
	/**
	 * Creates a chromosome from an existing one
	 * 
	 * @param dna
	 */
	public Chromosome(List<Integer> dna)
	{
		this.dna = new ArrayList<>(nAlleles);
		
		this.setDNA(dna);
	}
	
	//------------------------------------
	
	//-------METHODS----------------------
	
	private void generateDNA()
	{
		Random gerador = new Random();
		
		for(int i = 0; i < this.nAlleles; i++)
		{
			int alelo = gerador.nextInt(9); //generate a number between 0 and 8
			
			this.dna.add(alelo);
		}
	}
	
	//-------------------------------------
	
	//---------GET/SET---------------------
	
	public int getNAlleles()
	{
		return this.nAlleles;
	}
	
	public void setNAlleles(int n)
	{
		this.nAlleles = n;
	}
	
	public List<Integer> getDNA()
	{
		return this.dna;
	}
	
	public void setDNA(List<Integer> dna)
	{
		this.dna = dna;
	}
}
