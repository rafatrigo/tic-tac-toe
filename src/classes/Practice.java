package classes;

import java.util.ArrayList;
import java.util.List;

public class Practice {
	
	private Subject best;

	private int nSubjects; //number of subjects for generation
	private int generation;

	private int incompleteMatches; //matches that did not have a result
	private int victoriesP1;

	private Contest board;

	private List<Subject> population; //list of all subjects of a generation
	private List<Subject> bests; 
	
	private int dnaSize;
	private int mutation;
	private int saveSubjects; //percentage of subjects that will go to the next generation
	
	//-------CONTRUCTOR------------------
	
        /**
         * Practice class constructor
         * 
         * @param nSubjects - Number of subjects per generation
         * @param dnaSize - Size of the DNA
         * @param mutation - Percentage of mutation during crossover
         * @param saveSubjects - Number of subjects that will be keeped for the next generation
         */
	public Practice(int nSubjects, int dnaSize, int mutation, int saveSubjects)
	{
		this.nSubjects = nSubjects;
		this.dnaSize = dnaSize;
		this.mutation = mutation;
		this.saveSubjects = saveSubjects;
		this.generation = 0;
		this.incompleteMatches = 0;
		this.victoriesP1 = 0;
		this.board = new Contest();
		this.population = new ArrayList<>();
		this.bests = new ArrayList<>();
	}
	
	//-----------------------------------
	//--------METHODS--------------------

	/**
	 * Create a new generation
	 */
	public void createFirstGeneration()
	{
		for (int i = 0; i < nSubjects; i++) {
			Subject n = new Subject(dnaSize, mutation);

			this.population.add(n); //adds each Subject in the population
		}
	}
	
	/**
	 * Make the subjects compete with each other
	 */
	public void putSubjectsToCompete()
	{
		
		for (int i = 0; i < nSubjects; i++) {
			for (int j = 0; j < nSubjects; j++) {
				board.compete(population.get(i), population.get(j));
			}
		}
	}
        
        /**
	 * Have subjects compete against a specific strategy
	 */
	public void putSubjectsToCompete(ArrayList<Integer> strategy)
	{
            for (int i = 0; i < nSubjects; i++) {
                board.compete(population.get(i), strategy);
            }
	}

	/**
	 * Order the subjects from best to worst
	 */
	public void orderSubjects()
	{
		
		do {

			int indexMelhor = 0;

			for (int i = 0; i < this.population.size(); i++) {
				double m = this.population.get(indexMelhor).getScore() / this.population.get(indexMelhor).getMatches();
				double n = this.population.get(i).getScore() / this.population.get(i).getMatches();

				if (m < n) {
					indexMelhor = i;
				}

			}
					
			
			//adds the currently best subject to the population of the best
			this.bests.add(this.population.get(indexMelhor));
					
			//removes the currently best subject from the population
			this.population.remove(indexMelhor);

		} while (!this.population.isEmpty());
		
		this.setBest();
		
	}
	
	/**
	 * Create a new generation of subjects using a previous generation
	 */
	public void makeNewGenerarion()
	{
            
            // makes a new population adding the first % of the bests
            for (int i = 0; i < (int)(this.nSubjects * (double)(this.saveSubjects/100.0)); i++) {
		this.population.add(bests.get(i));
            }
            
            //crossover the current population to generate the other % 
            while(this.population.size() < this.nSubjects){
                int couples = this.nSubjects - 1;
                if(couples % 2 == 0){
                    couples = couples - 1;
                }
                
                for (int i = 0; 
                        i <= couples && this.population.size() < (int)(this.nSubjects); 
                        i = i + 2) {
                    Subject pai = bests.get(i);
                    Subject mae = bests.get(i + 1);

                    Subject n = new Subject(pai, mae, this.mutation);

                    //add the new subjects to the population
                    this.population.add(n);
                 }
            }
	}

        /**
	 * Clear the list of best subjects
	 * 
	 * In the end of each generation is recommended to clear the list of best subjects
	 */
	public void clearBests()
	{
		// clears the list used to order the subjects
		bests.clear();
	}
	
	/**
	 * Clear all values in the board used to see how well a generation performed
	 */
	public void resetBoardValues()
	{
		this.board.setVictoriesP1(0);
		this.board.setVictoriesP2(0);
		this.board.setDraws(0);
	}

	/**
	 * Print the data of a generation in the console
	 */
	public void printGenerationResult()
	{
		System.out.println("-----GERACAO " + generation + "-------");

		System.out.println("Vitorias P1: " + board.getVictoriesP1());
		System.out.println("Vitorias P2: " + board.getVictoriesP2());
		System.out.println("Empates: " + board.getDraws());
		System.out.println("Partidas indefinidas: " + incompleteMatches);
	}
	
	//------------------------------------
	
	//-----------GET/SET------------------
	
	public int getNSubjects()
	{
		return this.nSubjects;
    }
        
	public void setBest()
	{
		// store the best of the generation
		this.best = this.bests.get(0);
	}
	
	public Subject getBest()
	{
		return this.best;
	}
	
	public void setVictoriesP1()
	{
		this.victoriesP1 = board.getVictoriesP1();
	}
	
	public int getVictoriesP1()
	{
		return this.victoriesP1;
	}
	
	public void setIncompleteMatches()
	{
		this.incompleteMatches = (nSubjects * nSubjects) - (board.getVictoriesP1() + board.getVictoriesP2() + board.getDraws());
	}
	
	public int getIncompleteMatcher()
	{
		return this.incompleteMatches;
	}
	
	public void setGeneration(int value)
	{
		this.generation = value;
	}
	
	public int getGeneration()
	{
		return this.generation;
	}
        
    public Contest getBoard()
    {
    	return this.board;
    }
	
}
