package BoardGame;

import java.util.Random;

import BoardGame.DoublyLinkedList.ReadOnlyNode;

public class Game
{
    public ReadOnlyNode<Slot> FINISH = new ReadOnlyNode<Slot>(new Slot(-1, 'f'), null, null);
    private Board board;
    private Player[] players;
    private Random random;
    private boolean verbose = false;
    
    public Game( int size, int numPlayers, boolean verbose, long seed )
    {
        this.verbose = verbose;
        if( seed <= 0 )
        {
            seed = System.currentTimeMillis();
        }
        if( verbose )
        {
            System.out.println("Game Seed: " + seed + "L" );
        }
        random = new Random( seed );
        board = new Board( size );
        players = new Player[ numPlayers ];
        
        for( int i = 0; i<numPlayers; i++ )
        {
            players[i] = new Player( i, board.getFirstSlot() );
        }
    }
    
    public Game(int size, int numPlayers)
    {
        this( size, numPlayers, false, 0 );
    }
    
    public int getNumPlayers()
    {
        return players.length;
    }
    
    public Player getPlayer( int i )
    {
        return players[i];
    }
    
    /**
     * play n rounds, each round moves a single player.
     * If all the players are finished, return true.
     * Otherwise return false
     * 
     * @param n
     * @return 
     */
    private int nextPlayer = 0;
    public boolean playRounds( int n )
    {
        for( int i = 0; i<n; i++ )
        {
            playOneMove( nextPlayer );
            
            for( int j = 0; j<players.length; j++ )
            {
            		nextPlayer = (nextPlayer + 1)%players.length;	
                if( ! players[ nextPlayer ].finished() )
                    break;
            }
        }
        return players[ nextPlayer ].finished();
    }
    
    /**
     * Play a single move for a given player
     * 
     * @param player
     * @return true if the player finished, false otherwise
     */
    public boolean playOneMove( int player )
    {
        if( players[player].finished() )
        {
            return true;
        }        
        Player p = players[player];
        ReadOnlyNode<Slot> target = makeMove(board, p);
        if( target == null )
        {
            p.moveTo(FINISH);
            p.finish(FINISH.getElement().getNumPlayers());
            return true;
        }
        else
        {
            p.moveTo(target);
            return false;
        }
    }
    
    private ReadOnlyNode<Slot> makeMove( Board b, Player p )
    {
        int n = random.nextInt( 6 ) + 1;
        if( verbose )
        {
            System.out.println("Game player: " + p.getId() + " rolled dice: " + n );
        }
        ReadOnlyNode<Slot> slot = p.getSlot();
        
        slot = b.getNextSlot(slot, n);
        if( slot == null )
        {
            return null;
        }

        // check the rules
        Slot s = slot.getElement();
        if( s.getNumPlayers() > 0 )
        {
            return b.getPrevSlot( slot, 2 );
        }

        switch ( s.getColor() )
        {
            case 'b':
                return b.getNextSlot( slot, 2 );
            case 'r':
                return b.getPrevSlot( slot, 1 );
        }
        return slot;
    }
    
    @Override
    public String toString()	
    {
        StringBuilder builder = new StringBuilder();
        builder.append( board.toString() );
        builder.append( "\n" );
        int count = 0;
        for( Player p : players )
        {
            builder.append("\t");
            builder.append( p );
            count ++;
            if( count % 10 == 0 )
            {
                builder.append( "\n" );
            }
        }
        if( count % 10 != 0 )
        {
            builder.append( "\n" );
        }
        return builder.toString();
    }
}
