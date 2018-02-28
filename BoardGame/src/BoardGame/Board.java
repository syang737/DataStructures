package BoardGame;

import BoardGame.DoublyLinkedList.ReadOnlyNode;

public class Board
{
    private DoublyLinkedList<Slot> list;

    public Board( int size)
    {
        this.list = new DoublyLinkedList<>();
        //Sets board with colors and no players
        for( int i = 0; i<size; i++ )
        {
            switch (i % 3)
            {
                case 0:
                    list.addLast(new Slot(i, 'r'));
                    break;
                case 1:
                    list.addLast(new Slot(i, 'g'));
                    break;
                case 2:
                    list.addLast(new Slot(i, 'b'));
                    break;
            }
        }
    }

    public ReadOnlyNode<Slot> getFirstSlot()
    {
        return list.getFirstRONode();
    }
    
    public ReadOnlyNode<Slot> getNextSlot( ReadOnlyNode<Slot> s, int n )
    {
    		//getting the slot n spaces forward
        for( int i = 0; i<n; i++ )
        {
            s = list.getNext(s);
            if( s == null )
            {
                return null;
            }
        }
        return s;
    }
    
    public ReadOnlyNode<Slot> getPrevSlot( ReadOnlyNode<Slot> s, int n )
    {
    	//getting the slot n spaces back
        for( int i = 0; i<n; i++ )
        {
            s = list.getPrev(s);
            if( s == null )
            {
                return list.getFirstRONode();
            }
        }
        return s;
    }
    
    @Override
    public String toString()
    {
        return list.toString();
    }
}
