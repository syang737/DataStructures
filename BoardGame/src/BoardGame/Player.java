package BoardGame;

import BoardGame.DoublyLinkedList.ReadOnlyNode;

public class Player
{
    private int id;
    private int finishedOrder = -1;
    private ReadOnlyNode<Slot> slotNode = null;

    public Player(int i, ReadOnlyNode<Slot> s)
    {
        this.id = i;
        moveTo( s );
    }

    public int getId()
    {
        return id;
    }
    
    public ReadOnlyNode<Slot> getSlot()
    {
        return slotNode;
    }

    /**
     * Move this player to a new slot
     * @param newNode the new slot to move to
     */
    public void moveTo(ReadOnlyNode<Slot> newNode )
    {
        if( slotNode != null )
        {
            slotNode.getElement().removePlayer( this );
        }
        this.slotNode = newNode;
        newNode.getElement().addPlayer(this);
    }

    public boolean finished()
    {
        return finishedOrder >= 0;
    }
    
    public int getFinishOrder()
    {
        return finishedOrder;
    }
    
    public void finish( int order )
    {
        finishedOrder = order;
    }
    
    @Override
    public String toString()
    {
        String info;
        if( finished() )
        {
            info = "finished "+String.valueOf(getFinishOrder());
        }
        else
        {
            info = "@" + slotNode.getElement().getID();
        }
        return "Player" + (id+1) + " " + info;
    }
}
