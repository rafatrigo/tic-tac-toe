package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contest {
	private int victoriesP1;
    private int victoriesP2;
    private int draws;
    
    /**
     * Array of values that represents the board
     * 
     * 0 1 2
     * 3 4 5
     * 6 7 8
     */
    private int[][] board =
        {
            {0,0,0},
            {0,0,0},
            {0,0,0}
        };
    
    //---------CONSTRUCTORS--------------
    
    public Contest()
    {
        this.victoriesP1 = 0;
        this.victoriesP2 = 0;
        this.draws = 0;
    }
    
    //-----------------------------------
    
    //---------METHODS-------------------
    
    /**
     * puts two Subjects to compete and at the end of the match
     * rewards the Subjects
     * 
     * @param p1 The subject that will make the first move
     * @param p2 The subject that will make the second move
     */
    public void compete(Subject p1, Subject p2)
    {
        List<Integer> dnaP1 = p1.getCm().getDNA();
        List<Integer> dnaP2 = p2.getCm().getDNA();
        
        int end = 3; //value for when the game is not over
        
        int player = 1; //define which subject will make a move
        
        int i = 0; //used to know which position in player 1's DNA we are in
        
        int j = 1; //used to know which position in player 2's DNA we are in
        
        int mov = 0; //where on the board will the player play
        
        while(i < dnaP1.size() && j <= dnaP2.size() && end == 3)
        {
            if(player != 2)
            {
                mov = dnaP1.get(i);
                
                
                //The play method try to make a move, and if the move is valid he return true
                //If the play method returns true sum 1 in player, passing the turn to player 2
                if(this.play(mov, player))
                {
                	player++;
                }
                
               i+=2;
            }else{
                mov = dnaP2.get(j);
                
                //The play method try to make a move, and if the move is valid he return true
                //If the play method returns true subtract 1 in player, passing the turn to player 1
                if(this.play(mov, player))
                {
                	player--;
                }
                
                j+=2;
            }
            
            
            end = this.winner(this.board); //check if the match is over
            
            if(end != 3)
            {
            	this.reward(p1, p2, end);
            }
            
        }
        
        p1.setMatches(p1.getMatches() + 1); //p1 matches +1
        
        p2.setMatches(p2.getMatches() + 1); //p2 matches +1 
        
        this.clear(); //clear the board

    }

    /**
     *	Pits subjects against a fixed strategy
     * 
     * @param p1 The subject that will make the first move
     * @param strategy The sequence of moves that the subjects will play against
     */
    public void compete(Subject p1, ArrayList<Integer> strategy)
    {
        List<Integer> dnaP1 = p1.getCm().getDNA();
        
        int end = 3; //value for when the game is not over
        
        int player = 1; //define which subject will make a move
        
        int i = 0; //used to know which position in player 1's DNA we are in
        
        int j = 0; //used to know which position in player 2's DNA we are in
        
        int mov = 0; //where on the board will the player play
        
        while(i < dnaP1.size() && j < strategy.size() && end == 3)
        {
            if(player != 2)
            {
                mov = dnaP1.get(i);
                
                
                //The play method try to make a move, and if the move is valid he return true
                //If the play method returns true sum 1 in player, passing the turn to player 2
                if(this.play(mov, player))
                {
                	player++;
                }
                
               i+=2;
            }else{
                mov = strategy.get(j);
                
                //The play method try to make a move, and if the move is valid he return true
                //If the play method returns true subtract 1 in player, passing the turn to player 1
                if(this.play(mov, player))
                {
                	player--;
                }
                
                j+=1;
            }
            
            
            end = this.winner(this.board); //check if the match is over
            
            if(end != 3)
            {
            	this.reward(p1, end);
            }
            
        }
        
        p1.setMatches(p1.getMatches() + 1); //p1 matches +1
        
        this.clear(); //clear the board

    }
    
    /**
     * rewards and punish the subjects
     * 
     * @param p1 The first subject to play
     * @param p2 The second subject to play
     * @param result the result of the match 0 = draw, 1 = p1 won and 2 = p2 won
     */
    public void reward(Subject p1, Subject p2, int result)
    {
    	//if p1 won
        if(result == 1)
        {
            
            p1.setVictories(p1.getVictories()+ 1); //p1 victories +1
            
            p1.setScore(p1.getScore() + 3); //p1 score +3
            
            p2.setDefeats(p2.getDefeats() + 1); //p2 defeats +1
            
            this.victoriesP1++;
            
        }else if(result == 2) //if p2 won 
        {

            p2.setVictories(p2.getVictories()+ 1); //p2 victories +1
            
            p2.setScore(p2.getScore() + 3); //p2 score +3
            
            p1.setDefeats(p1.getDefeats() + 1); //p1 defeats +1
            
            this.victoriesP2++;
            
        }else if(result == 0) //draw
        {
            this.draws++;
            p1.setScore(p1.getScore() + 1); //p1 score +1
            p1.setDraws(p1.getDraws() + 1); //p1 draws +1
            p2.setScore(p2.getScore() + 1); //p2 score +1
            p2.setDraws(p2.getDraws() + 1); //p2 draws +1
        }
    }
    
    /**
     * rewards and punish the subjects
     * 
     * @param p1 p1 The first subject to play
     * @param result the result of the match 0 = draw, 1 = p1 won and 2 = p2 won
     */
    public void reward(Subject p1, int result)
    {
    	//if p1 won
        if(result == 1)
        {
            p1.setVictories(p1.getVictories()+ 1); //p1 victories +1
            
            p1.setScore(p1.getScore() + 3); //p1 score +3
            
            this.victoriesP1++;
            
        }else if(result == 2) //if p2 won 
        {

            p1.setDefeats(p1.getDefeats() + 1); //p1 defeats +1
            
            this.victoriesP2++;
            
        }else if(result == 0) //draw
        {
            this.draws++;
            p1.setScore(p1.getScore() + 1); //p1 score +1
            p1.setDraws(p1.getDraws() + 1); //p1 draws +1
        }
    }

    /**
     * check if anyone won
     * 
     * @param board The array containing the moves
     * @return 0 = draw, 1 = p1 won, 2 = p2 won and 3 = game is not over
     */
    public int winner(int[][] board)
    {
        //p1 won
        if(
                (board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 1) //filled in on line 1
                ||
                (board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 1) //filled in on line 2
                ||
                (board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 1) //filled in on line 3
                ||
                (board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 1) //filled in column 1
                ||
                (board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 1) //filled in column 2
                ||
                (board[0][2] == 1 && board[1][2] == 1 && board[2][2] == 1) //filled in column 3
                ||
                (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1) //filled diagonally
                ||
                (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1) //filled diagonally
            )
                {
                    return 1;
                }
        //p2 won
        else if(
                (board[0][0] == 2 && board[0][1] == 2 && board[0][2] == 2) //filled in on line 1
                ||
                (board[1][0] == 2 && board[1][1] == 2 && board[1][2] == 2) //filled in on line 2
                ||
                (board[2][0] == 2 && board[2][1] == 2 && board[2][2] == 2) //filled in on line 3
                ||
                (board[0][0] == 2 && board[1][0] == 2 && board[2][0] == 2) //filled in column 1
                ||
                (board[0][1] == 2 && board[1][1] == 2 && board[2][1] == 2) //filled in column 2
                ||
                (board[0][2] == 2 && board[1][2] == 2 && board[2][2] == 2) //filled in column 3
                ||
                (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2) //filled diagonally
                ||
                (board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2) //filled diagonally
                )
                    {
                        return 2;
                    }
        //draw if all the spaces are filled but nobody won
        else if(
                (board[0][0] != 0 && board[0][1] != 0 && board[0][2] != 0) 
                &&
                (board[1][0] != 0 && board[1][1] != 0 && board[1][2] != 0) 
                &&
                (board[2][0] != 0 && board[2][1] != 0 && board[2][2] != 0) 
                )
                    {
                        return 0;
                    }
        //game is not over
        else{
            return 3;
        }
    }
    
    /**
     * Set all the positions on the board as 0
     */
    public void clear()
    {
        this.board[0][0] = 0;
        this.board[0][1] = 0;
        this.board[0][2] = 0;
        
        this.board[1][0] = 0;
        this.board[1][1] = 0;
        this.board[1][2] = 0;
        
        this.board[2][0] = 0;
        this.board[2][1] = 0;
        this.board[2][2] = 0;
        
    }
    
    /**
     * Print the board on the console
     */
    public void show()
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                int pos = this.board[i][j];
                char jogada = ' ';
                
                switch(pos)
                {
                    case 0:
                        jogada = ' ';
                        break;
                    case 1:
                        jogada = 'X';
                        break;
                    case 2:
                        jogada = 'O';
                        break;
                    default:
                        
                        break;
                }
                
                if(j < 2)
                {
                    System.out.print(" " + jogada + " |");
                }else{
                    System.out.println(" " + jogada + " ");
                }
                
            }
            if(i != 2)
            {
                System.out.println("-----------");
            }
        }
    }
    
    /**
     * Make the best subject compete against a human player
     * 
     * @param p The best subject
     */
    public void hCompete(Subject p)
    {
        Scanner read = new Scanner(System.in);
        
        List<Integer> dnaP = p.getCm().getDNA();
        
        int i, op, mov, player;
        int end = 3;
        
        //asks the user if he wants to play first
        System.out.println("Voce vai comecar jogando?\n1-SIM\n0-NAO\n> ");
        op = read.nextInt();
        
        if(op == 1) //if he wants to play first
        {
            player = 1;
            i = 1;
           while(i <= dnaP.size() && end == 3)
           {
                
                this.show(); //print the board
                System.out.println("\n\n");
                
                //user's turn to play
                if(player != 2)
                {
                  
                    //ask the user where he wants to play
                    System.out.print("Onde voce quer jogar?\n> ");
                    mov = read.nextInt();
                    
                    //The play method try to make a move, and if the move is valid he return true
                    //If the play method returns true sum 1 in player, passing the turn to player 2
                    if(this.play(mov, player))
                    {
                    	player++;
                    }else {
                    	System.out.println("Esta posicao ja esta ocupada, escolha novamente.");
                    }
                    
                }else{
                    mov = dnaP.get(i);

                    //The play method try to make a move, and if the move is valid he return true
                    //If the play method returns true subtract 1 in player, passing the turn to player 1
                    if(this.play(mov, player))
                    {
                    	player--;
                    }
                    
                    i+=2;
                }
               
               //check if anyone won
                end = this.winner(this.board);

                //p1 won
                if(end == 1)
                {
                    System.out.println("Parabens, voce ganhou!");
                }else if(end == 2) //p2 won
                {
                    System.out.println("Voce perdeu.");
                }else if(end == 0) //draw
                {
                    System.out.println("Empatou.");
                }
                
                //if the Subject has no more movements
                if(i == dnaP.size())
                {
                    System.out.println("Esse e o ultimo movimento da maquina.");
                }
                
                //print the board
                this.show(); 
                System.out.println("\n\n");
                
            }
            
        }else{ //if he doesn't play first
           
            player = 2;
            i = 0;
           while(i < dnaP.size() && end == 3)
           {
                //print the board
                this.show(); 
                System.out.println("\n\n");
                
                //user's turn to play
                if(player != 2)
                {
                  
                    //ask the user where he wants to play
                    System.out.print("Onde voce quer jogar?\n> ");
                    mov = read.nextInt();
                    
                    //The play method try to make a move, and if the move is valid he return true
                    //If the play method returns true sum 1 in player, passing the turn to player 2
                    if(this.play(mov, player))
                    {
                    	player++;
                    }else {
                    	System.out.println("Esta posicao ja esta ocupada, escolha novamente.");
                    }
                    
                    
                }else{
                    mov = dnaP.get(i);
                    
                    //The play method try to make a move, and if the move is valid he return true
                    //If the play method returns true subtract 1 in player, passing the turn to player 1
                    if(this.play(mov, player))
                    {
                    	player--;
                    }

                    i+=2;
                }
               
                //check if anyone won
                end = this.winner(this.board);

                //p1 won
                if(end == 1)
                {
                    System.out.println("Parabens, voce ganhou!");
                }else if(end == 2) //p2 won
                {
                    System.out.println("Voce perdeu.");
                }else if(end == 0) //draw
                {
                    System.out.println("Empatou.");
                }
                
                //if the Subject has no more movements
                if(i == dnaP.size())
                {
                    System.out.println("Esse e o ultimo movimento da maquina.");
                }
                
                //print the board
                this.show(); 
                System.out.println("\n\n");

            }
            
        }
    }
    
    /**
     * Make a move on the board
     * 
     * @param position The position of the board where the player wants to play
     * @param player The player 1 = first player or 2 = second player
     * @return False if the move is not valid or True if the move is a valid move
     */
    public boolean play(int position, int player)
    {
    	
    	boolean validMove = false;
    	
    	switch(position)
        {
            case 0:
                
                if(this.board[0][0] == 0)
                {
                    this.board[0][0] = player;
                    validMove = true;
                }
                
                break;
            case 1:
                if(this.board[0][1] == 0)
                {
                    this.board[0][1] = player;
                    validMove = true;
                }
                break;
            case 2:
                if(this.board[0][2] == 0)
                {
                    this.board[0][2] = player;
                    validMove = true;
                }
                break;
            case 3:
                if(this.board[1][0] == 0)
                {
                    this.board[1][0] = player;
                    validMove = true;
                }
                break;
            case 4:
                if(this.board[1][1] == 0)
                {
                    this.board[1][1] = player;
                    validMove = true;
                }
                break;
            case 5:
                if(this.board[1][2] == 0)
                {
                    this.board[1][2] = player;
                    validMove = true;
                }
                break;
            case 6:
                if(this.board[2][0] == 0)
                {
                    this.board[2][0] = player;
                    validMove = true;
                }
                break;
            case 7:
                if(this.board[2][1] == 0)
                {
                    this.board[2][1] = player;
                    validMove = true;
                }
                break;
            case 8:
                if(this.board[2][2] == 0)
                {
                    this.board[2][2] = player;
                    validMove = true;
                }
                break;
        }
    	
    	return validMove;
    }
    
//----------------------------------------
    
//----------GET/SET-----------------------
    
    public void setVictoriesP1(int vitorias)
    {
        this.victoriesP1 = vitorias;
    }
    
    public int getVictoriesP1()
    {
        return this.victoriesP1;
    }
    
    public void setVictoriesP2(int vitorias)
    {
        this.victoriesP2 = vitorias;
    }
    
    public int getVictoriesP2()
    {
        return this.victoriesP2;
    }
    
    public void setDraws(int empates)
    {
        this.draws = empates;
    }
    
    public int getDraws()
    {
        return this.draws;
    }
    
    public int[][] getBoard()
    {
        return this.board;
    }
}
