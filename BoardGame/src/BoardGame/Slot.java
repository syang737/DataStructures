package BoardGame;

import BoardGame.DoublyLinkedList.ReadOnlyNode;

public class Slot
{
    private final int id;
    private final char color;
    private DoublyLinkedList<Player> players = new DoublyLinkedList<Player>();

    public Slot(int id, char color)
    {
        this.id = id;
        this.color = color;
    }

    public int getID()
    {
        return id;
    }
    
    public char getColor()
    {
        return color;
    }

    public int getNumPlayers()
    {
        return players.size();
    }

    public void addPlayer(Player p)
    {
        players.addLast( p );
    }
    
    public void removePlayer( Player p )
    {
        players.remove( p );
    }
    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( id );
        builder.append( color );
        if( getNumPlayers() > 0 )
        {
            builder.append( "[" );
            for( int i = 0; i<getNumPlayers(); i++ )
            {
                if( i > 0 )
                {
                    builder.append( "," );
                }
                Player p = players.removeFirst();
                builder.append( (p.getId() +1));
                players.addLast(p);
            }            
            builder.append( "]" );
        }
        return builder.toString();
    }
   
}
