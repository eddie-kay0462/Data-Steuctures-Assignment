import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class HistoryNode
{
    String url;
    String timestamp;
    HistoryNode prev;
    HistoryNode next;

    /*
     * 
     */
    public HistoryNode(String url, String timestamp)
    {
        this.url = url;
        this.timestamp = timestamp;
        this.prev = null;
        this.next = null;
    }
}
public class BrowserHistory 
{
    private HistoryNode head;
    private HistoryNode tail;
    int size = 0;

    public BrowserHistory()
    {
        this.head = null;
        this.tail = null;
    }


    
    /**
     * Add a newpage to the end of the history
     * @param url
     * @param timestamp
     */
    public void addPage(String url, String timestamp)
    {
        //Initialize the new History
        HistoryNode newNode = new HistoryNode(url, timestamp);
        HistoryNode current = head;
        newNode.next = null;
        if (head == null)
        {
            head = newNode;
            tail = newNode;
            newNode.prev = null;
            size++;
            return;
        }
        while (current.next !=null)
        {
            current = current.next;
        }
        current.next = newNode;
        newNode.prev = current;
        tail = newNode;
        size++;
    }

    /**
     * This method will remove a page by its timestamp
     * @param timestamp
     */
    public void removePage(String timestamp)
    {
        HistoryNode current = head;
        if (size == 0)
        {
            System.out.println("The history is empty");
            return;
        }
        while (current!=null)
        {
            if (current.timestamp.equals(timestamp))
            {
                //if the page to be removed is the same as the head
                if (current == head)
                {
                    head = current.next;
                    if (head !=null)
                    {
                        head.prev = null;
                    }
                    else
                    {
                        tail = null;  //if head is null then the list is empty so set tail to be null
                    }
                }

                //if the page to be removed is the last or tail 
                else if (current == tail)
                {
                    tail = tail.prev;
                    if (tail !=null)
                    {
                        tail.next = null;
                    }
                    else
                    {
                        head  = null; //if tail is null then the list is empty so set head to be null
                    }
                }
                 //if the element corresponds to an item in between
                else
                {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
    }

    /**
     * This method will display the browser history starting from the 
     * oldest to the newest
     */
    public void displayHistoryForward()
    {
        if(isHistoryEmpty())
        {
            System.out.println("The list is empty");
            return;
        }
        else
        {
            HistoryNode current = head;
            while (current!=null)
            {
                System.out.println("Visited "+ current.url + " at " + current.timestamp);
                current = current.next;    
            }
        }
       
    }

    public void displayHistoryBackward()
    {
        if(isHistoryEmpty())
        {
            System.out.println("The list is empty");
            return;
        }
        else
        {
            HistoryNode current = tail;
            while (current!=null)
            {
                System.out.println("Visited " + current.url + " at " + current.timestamp);
                current = current.prev;
            }
        }
        
    }

    /**
     * get the size of the list
     * @return
     */
    public int getSizeHistory()
    {
        return size;
    }

    public boolean isHistoryEmpty()
    {
        return size ==0;
    }

   

    /**
     * This method will save the browser history to a file named
     * histoty.txt
     */
    public  void saveBrowserHistory()
    { 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("history.txt")))
        {
            HistoryNode current = head;
            while (current!=null)
            {
                writer.write("Visited " + current.url + " at " + current.timestamp + "\n");   
                current = current.next; 
            }
            writer.close();
            
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        
    }

    /**
     * This method will load the history and display it to the console
     */
    public void loadBrowserHistory()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("history.txt")))
        {
           String line;
           while ((line = reader.readLine()) !=null)
           {
            System.out.println(line);
           }
           
           reader.close();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
