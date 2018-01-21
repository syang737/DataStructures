import java.io.*;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws FileNotFoundException
	{
		// The name of the file to open.
        String fileName = "words.txt";

        File file = new File(fileName);

        String word = null;

        Scanner sc = new Scanner(file);

        Link begin = null;
        Link end = null;
        Link temp = null;
        while(sc.hasNext())
        {
            word = sc.next();

            Link l = new Link(word, null);
            if (begin == null) {
                begin = l;                
            }
            else if (end == null){
                end = l;
                begin.setNext(end);
            }
            else{
                end.setNext(l);
                temp = l;
                l = end;
                end = temp;
            }
        }
        // printList(begin);
        printList(reverseList(begin));
        sc.close();     
	}
    private static Link reverseList(Link head)
    {   
        if(head==null||head.getNext()==null)
        	return head;

        Link p1 = head;
        Link p2 = p1.getNext();

        head.setNext(null);

        while(p1!=null&&p2!=null){
        	Link t = p2.getNext();
        	p2.setNext(p1);
        	p1=p2;
        	p2=t;
        }
    }
    private static Link everyOtherLink(Link begin)
    {
        return null;
    }
    private static void printList(Link begin)
    {
        for (;begin != null; begin = begin.getNext()) {System.out.println(begin.getPayload());}
    }
}