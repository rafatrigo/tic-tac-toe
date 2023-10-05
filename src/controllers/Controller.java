package controllers;

import java.util.List;

import classes.Contest;
import classes.Practice;
import classes.Subject;
import java.util.ArrayList;

public class Controller {
	
	private Practice practice;
    private int move; //the number of the move when the best subject is playing
    private Contest board;
    private ArrayList<Integer> UserStrategy; //the strategy of the human player who beat the best subject
	
	/**
	 * Constructor of the controller
	 * 
	 * @param nSubjects The number of subjects per generation
	 * @param dnaSize The size of each subject's dna
	 * @param mutation The percentage of mutation during crossover
	 * @param saveSubjects The percentage of subjects that will go to the next generation
	 */
	public Controller(int nSubjects, int dnaSize, int mutation, int saveSubjects)
	{
		this.practice = new Practice(nSubjects, dnaSize, mutation, saveSubjects);
                this.board = this.practice.getBoard();
                this.move = 0;
                this.UserStrategy = new ArrayList<>();
	}
	
	/**
	 * Train the subjects using the configuration on the gui
	 */
	public void trainSubjects()
	{
		
		practice.createFirstGeneration();
		
		do {
			
			practice.putSubjectsToCompete();
			
			practice.orderSubjects();
			
			practice.makeNewGenerarion();
			
			practice.setVictoriesP1();
			
			practice.setIncompleteMatches();
			
			practice.printGenerationResult();
			
			practice.setGeneration(practice.getGeneration() + 1);
			
			practice.resetBoardValues();
			
			practice.clearBests();
			
		}while(practice.getVictoriesP1() <= (practice.getNSubjects()*practice.getNSubjects() * 0.7));
	}
        
     /**
	 * Train the subjects against the human player strategy
	 */
	public void trainSubjectsAgainstStrategy()
	{
            for(int i = 0; i < 20; i++){
                practice.putSubjectsToCompete(this.UserStrategy);

                practice.orderSubjects();

                practice.makeNewGenerarion();

                practice.setVictoriesP1();

                practice.setIncompleteMatches();

                practice.printGenerationResult();

                practice.setGeneration(practice.getGeneration() + 1);

                practice.resetBoardValues();

                practice.clearBests();
            }
	}
	
	/**
	 * Make the subject play the first move
	 * 
	 * @return The position of the movie
	 */
	public int startGame()
	{
		Subject best = this.practice.getBest();
		List<Integer> dnaBest = best.getCm().getDNA();
                
                this.move = 0; //restarts the moves on the beginning of every match
                
                this.board.clear();
                
                this.board.play(dnaBest.get(0), 1);
                
                /*
                    I could have assigned the value 2 to the move variable
                    at the beginning of this method, but I am assigning it now
                    for a better understanding of the process
                
                    Every time a subjects play the move is increased by 2
                    because the pairs position on the dna are used when the
                    subject plays first and the odd position when he is the
                    second player
                
                    So for the second move of the subject it will use the
                    position number 2
                */
                this.move = 2;
		
		return dnaBest.get(0);
		
	}
        
    /**
     * Receives a position from the second player to update the board
     * 
     * @param position
     * @return If the position is a valid position returns true
     */
    public boolean updateBoardP2(int position)
    {
        return this.board.play(position, 2);
    }

    public int subjectPlay()
    {
        Subject best = this.practice.getBest();
        List<Integer> dnaBest = best.getCm().getDNA();

        boolean validMove = false;
        int movePosition;

        do{
            movePosition = dnaBest.get(this.move);

            validMove = this.board.play(movePosition, 1);

            move += 2;

        }while(!validMove);

        return movePosition;
    }

    public int checkEnd()
    {
        return this.board.winner(this.board.getBoard());
    }
        
    public void addUserLastMove(int m){
        this.UserStrategy.add(m);
    }
    
    public void resetUserStrategy(){
        this.UserStrategy.clear();
    }
}
