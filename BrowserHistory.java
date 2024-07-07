import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Class representing a node in the browser history doubly linked list
class HistoryNode
{
    String url;
    String timestamp;
    HistoryNode prev;
    HistoryNode next;

    
    /**
     * Constructor to initialize a HistoryNode
     * @param url of the visited page
     * @param timestamp the timestamp of the visit
     */
    public HistoryNode(String url, String timestamp)
    {
        this.url = url;
        this.timestamp = timestamp;
        this.prev = null;
        this.next = null;
    }
}

// Class representing the browser history as a doubly linked list
public class BrowserHistory 
{
    private HistoryNode head;
    private HistoryNode tail;
    int size = 0; // Size of the history list

     /**
     * Constructor to initialize an empty browser history
     */
    public BrowserHistory()
    {
        this.head = null;
        this.tail = null;
    }


    
    /**
     * Add a newpage to the end of the history
     * @param url the url of the visited page
     * @param timestamp the timestamp of visit
     */
    public void addPage(String url, String timestamp)
    {
        //Initialize the new HistoryNode
        HistoryNode newNode = new HistoryNode(url, timestamp);
        HistoryNode current = head;
        newNode.next = null;

         // If the list is empty, set the new node as both head and tail
        if (head == null)
        {
            head = newNode;
            tail = newNode;
            newNode.prev = null;
            size++;
            return;
        }

        // Traverse to the end of the list and add the new node
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
     * Removes a page from the history by its timestamp
     * @param timestamp the timestamp of the page to remove
     */
    public void removePage(String timestamp)
    {
        //If the list is empty, print a message and return
        HistoryNode current = head;
        if (size == 0)
        {
            System.out.println("The history is empty");
            return;
        }

        //Traverse the list to find the node with the matching timestamp
        while (current!=null)
        {
            if (current.timestamp.equals(timestamp))
            {
                //if the page to be removed  the head
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
                 //If the node is in between
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
     * Displays the browser history from the oldest to the newest
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

    /**
     * Displays the browser history from the newest to the oldest
     */
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
     * Gets the size of the history list
     * @return the size of the history list
     */
    public int getSizeHistory()
    {
        return size;
    }

    /**
     * Checks if the history list is empty
     * @return true if the history list is empty, false otherwise
     */
    public boolean isHistoryEmpty()
    {
        return size ==0;
    }

   

    /**
     * Saves the browser history to a file named "history.txt"
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
     * Loads the browser history from the file "history.txt" and displays it to the console
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
