package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Subject {
    private Subject father;
    private Subject mother;
    private Chromosome cm;
    private int matches;
    private int victories;
    private int draws;
    private int defeats;
    private int score;
    private int mutation; //percentage of mutation in the crossover
    
    //------CONSTRUCTORS--------------
    
    /**
     * Create a subject without parents
     * 
     * @param dnaSize The size of dna
     * @param mutation The percentage of mutation that will be used during crossover
     */
    public Subject(int dnaSize, int mutation)
    {
        this.father = null;
        this.mother = null;
        this.matches = 0;
        this.victories = 0;
        this.score = 0;
        this.defeats = 0;
        this.draws = 0;
        this.mutation = mutation;
        this.cm = new Chromosome(dnaSize);
    }
    
    /**
     * Create a subject mixing the dna of a father and a mother
     * 
     * @param father
     * @param mother
     */
    public Subject(Subject father, Subject mother, int mutation)
    {
        this.father = father;
        this.mother = mother;
        this.cm = new Chromosome(this.crossover(father, mother));
        this.matches = 0;
        this.victories = 0;
        this.score = 0;
        this.defeats = 0;
        this.draws = 0;
        this.mutation = mutation;
    }
    
    //------------------------------------------
    
    //-----------METHODS------------------------
    
    /**
     * Mix two DNAs to generate a new one
     * 
     * @param father
     * @param mother
     * @return dna a list of integers between 0 and 8
     */
    private List<Integer> crossover(Subject father, Subject mother)
        {
            Random r = new Random();
            
            List<Integer> fatherDNA = father.getCm().getDNA();
            List<Integer> motherDNA = mother.getCm().getDNA();
            
            int dnaSize = motherDNA.size();
            
            List<Integer> dna = new ArrayList();
            
            int op; //operation
            
            //travels through each position of DNA
            for(int i = 0; i < dnaSize; i++)
            {
                op = r.nextInt(2); // randomly generate 0 or 1
                
                if(op == 0)
                {
                    dna.add(fatherDNA.get(i));
                }else{
                    dna.add(motherDNA.get(i));
                }
            }
            
            
            
            int mt; //mutation
            
            mt = r.nextInt(100); // generate a number between 0 and 99
            
            /*
             * if the mt value is less than the value of the percentage of mutation,
             * it mutates one allele
             */
            if(mt < this.mutation)
            {
            	/*
            	 * generate a number between 0 and the size of the DNA,
            	 * this value will be the position of the allele that 
            	 * will be mutated
            	 */
            	int mtAllele = r.nextInt(dnaSize);
            	
                dna.set(mtAllele, r.nextInt(9)); // set the position of the allele and his new value
            }
            
            
            return dna;
        }
    
    //--------------------------------
    
    //---------------GET/SET----------

    public Subject getFather() {
        return father;
    }

    public void setFather(Subject father) {
        this.father = father;
    }

    public Subject getMother() {
        return mother;
    }

    public void setMother(Subject mother) {
        this.mother = mother;
    }

    public Chromosome getCm() {
        return cm;
    }

    public void setCm(Chromosome cm) {
        this.cm = cm;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }
    
    public void setScore(int p)
    {
        this.score = p;
    }
    
    public int getScore()
    {
        return this.score;
    }
    
    public void setDraws(int e)
    {
        this.draws = e;
    }
    
    public int getDraws()
    {
        return this.draws;
    }
    
    public void setDefeats(int d)
    {
        this.defeats = d;
    }
    
    public int getDefeats()
    {
        return this.defeats;
    }

    public int getMutation() {
        return mutation;
    }

    public void setMutation(int mutation) {
        this.mutation = mutation;
    }
}
