package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Chromosome class is responsible for store and create dna
 */
public class Chromosome {
	private  int nAlleles; //the size of dna
    private List<Integer> dna; //list of numbers between 0 and 9
	
    //--------CONSTRUCTORS------------
    
    /**
     * Create a new chromosome
     * 
     * @param dnaSize the size of the chromosome
     */
	public Chromosome(int dnaSize)
	{
		this.nAlleles = dnaSize;
		this.dna = new ArrayList<>(dnaSize);
		
		this.generateDNA();
	}
	
	/**
	 * Copy a chromosome
	 * 
	 * @param dna The dna of the chromosome that will be copied
	 */
	public Chromosome(List<Integer> dna)
	{
		this.dna = new ArrayList<>(nAlleles);
		
		this.setDNA(dna);
	}
	
	//------------------------------------
	
	//-------METHODS----------------------
	
	/**
	 * Generate dna
	 */
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
