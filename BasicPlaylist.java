//Class representing a ndoe in the singly linked list for songs
class SinglySongNode 
{
    Song data; //The song data
    SinglySongNode next; //reference to the next node of song

    /**
     * Initialise a SinglySongNode
     * @param data 
     */
    SinglySongNode(Song data)
    {
        this.data = data;
        this.next = null;
    }
}

//Class representing a basic playlist using a singly linked list
public class BasicPlaylist 
{
    private SinglySongNode head; //reference to the firstt node in the list
    int size;  //Size of the playlist

    /**
     * Constructor to initialize an empty playlist
     */
    BasicPlaylist()
    {
        this.size = 0;
    }

    /**
     * Adds a song to the end of the playlist
     * @param song the song to add
     */
    public void addSongAtEnd(Song song)
    {
        SinglySongNode newNode = new SinglySongNode(song);
        if (head == null)
        {
            head = newNode;
        }
        else
        {
            SinglySongNode current = head;
            while (current.next !=null)
            {
                current = current.next;    
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Adds a song at a specific position in the playlist
     * @param song the song to add
     * @param position the position at which to add the song
     */
    public void addSongAtPosition(Song song, int position)
    {
        SinglySongNode newNode = new SinglySongNode(song);

        //Check if the position is valid
        if (position > (size+1) || position <1)
        {
            System.out.println("Adding a song a position " + position + " is invalid. Position should be between 1 and " + (size+1) + " inclusive");
            return;
        }

        //Add the song at the first position
        else if (position == 1)
        {
            newNode.next = head;
            head = newNode;
            
        }
        
        //Add the song at the last position
        else if (position == (size+1))
        {
            SinglySongNode current = head;
            while (current.next!=null)
            {
                current = current.next;    
            }
           current.next = newNode;
        }
        //Add a song in between the first and last position
        else
        {
            SinglySongNode current = head;
            int count = 1;
            while (count < (position-1))
            {
                current = current.next;
                count++;    
            }
            newNode.next = current.next;
            current.next = newNode;
           
        }
        size++;
    }

    /**
     * Removes a song from the playlist by its title
     * @param title the title of the song to remove
     */
    public void removeSongByTitle(String title)
    {
        SinglySongNode current = head;
        //Check if the playlist is empty
        if (head == null)
        {
            System.out.println("The playlist is empty");
            return;
        }

        //Check if the head node is the song to be removed
        else if (head.data.getTitle().equals(title))
        {
            head = head.next;
            size--;
            return;
        }

        //Traverse the list to find the song to remove
        while (current.next!=null)
        {
            //if the next node contains the song to be removed, bypass it
            if (current.next.data.getTitle().equals(title))
            {
                current.next = current.next.next;
                size--;
                return; //exit after removing the node
            }
            current = current.next;
        }
        System.out.println("Music with title " + title+ " is not in the list");
    }


    /**
     * Removes a song from the playlist by its position
     * @param position the postion of the son to remove
     */
    public void removeSongByPosition(int position)
    {
        // Check if the position is the first one
        if (position ==1)
        {
            head = head.next;
        }
        // Check if the position is the last one
        else if (position == size)
        {
            SinglySongNode current = head;
            int count =1;
            while (count < (position-1))
            {
                current = current.next;
                count++;    
            }
            current.next = null;
        }
        // Check if the position is between the first and last
        else if ((position>1) && (position<size))
        {
            SinglySongNode current = head;
            int count = 1;
            while (count < (position-1))
            {
                current = current.next;
                count++;    
            }
            current.next = current.next.next;
        } 
        else
        {
            System.out.println("Invalid position");
            return;
        }
        size--;
    }

    /**
     * Displays the playlist in order
     */
    public void displayPlaylist()
    {
        if (isPlaylistEmpty())
        {
            System.out.println("Playlist is empty");
            return;
        }
        SinglySongNode current = head;
        while (current!=null)
        {
            System.out.println(current.data);
            current = current.next;    
        }
    }

    /**
     * Gets the total duration of the playlist in seconds
     * @return the total duration of the playlist
     */
    public int getTotalDuration()
    {
        int totalDuration = 0;
        SinglySongNode current = head;
        while (current!=null)
        {
            totalDuration += current.data.getDuration();
            current = current.next;    
        }
        return totalDuration;
    }

    /**
     * Gets the size of the playlist
     * @return the size of the playlist
     */
    public int getSizePlaylist()
    {
        return size;
    }

    /**
     * Check if the playlist is empty
     * @return true if the playlist is empty, false otherwise
     */
    public boolean isPlaylistEmpty()
    {
        return size==0;
    }
}
