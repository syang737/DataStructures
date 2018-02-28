
package BoardGame;

import java.util.Arrays;
import java.util.Scanner;

public class Main
{
	public static final int TOTAL_GAMES = 1000;
	public static final int BOARD_SIZE = 125;	
	
    public static void main( String args[] )
    {	
    		Scanner sc = new Scanner(System.in);
    		
    		System.out.print("How many players? ");
		int numPlayers = sc.nextInt();
    		
    		
    		System.out.println("Playing " + TOTAL_GAMES + " games in total");
    		
    		
    		System.out.print("Which game would you like to see?");
    		int gameNum = sc.nextInt();
    		
    		System.out.print("Which move of this game would you like to print?");
    		int moveNum = sc.nextInt();
    		
    		
        testOne(numPlayers, gameNum, moveNum);
        
        sc.close();
    }
    
    
    
    public static void testOne(int numPlayers, int gameNum, int moveNum)
    {
        int winnings[] = new int[numPlayers];
        int numMoves[] = new int[numPlayers];
        Arrays.fill(winnings, 0);
        Arrays.fill(numMoves, 0);
        for( int i = 1; i<=TOTAL_GAMES; i++ )
        {
        		Game g = new Game(BOARD_SIZE, numPlayers );
        		if(i == gameNum) {
        			g.playRounds(moveNum);
        			System.out.println("Board at game number " + gameNum + ", move number " + moveNum);
        			System.out.println(g);
        		}
            
            while( !g.playRounds(numPlayers) )
            	{
            		for(int j = 0; j < numPlayers; j++)
            		{
            			Player p = g.getPlayer(j);
            			if(!p.finished())
            				numMoves[j] ++;
            		}
            	}
            // System.out.println(g);
            for( int j = 0; j<numPlayers; j++ )
            {
                Player p = g.getPlayer(j);
                if( p.getFinishOrder() == 1 )
                {
                    winnings[j] ++;
                }
            }
        }
        System.out.println("Move stats:");
        for( int i = 0; i<numPlayers; i++ )
        {
            System.out.println("Player " + (i+1) + " on average took " + (double)numMoves[i]/TOTAL_GAMES + " moves to win");
        }
        System.out.println("\nWinning stats:");
        
        
        for( int i = 0; i<numPlayers; i++ )
        {
            System.out.println("Player " + (i+1) + " won " + winnings[i] + " game(s), or " + (winnings[i]*100/TOTAL_GAMES) + "%" );
        }
    }
    
    public static void testAdHoc()
    {
        int numPlayer = 3;
        Game g = new Game( 15, numPlayer, true, 0 );  
        System.out.println(g);
        
        for( int i = 0; i<10; i++ )
        {
            int player = i%numPlayer;
            boolean ret = g.playOneMove(player);
            if( ret )
            {
                System.out.println("player " + player + " has finished" );
            }
            System.out.println(g);
        }
    }
    
}
