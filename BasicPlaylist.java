class SinglySongNode 
{
    Song data;
    SinglySongNode next;

    SinglySongNode(Song data)
    {
        this.data = data;
        this.next = null;
    }
}

public class BasicPlaylist 
{
    private SinglySongNode head;
    int size;

    BasicPlaylist()
    {
        this.size = 0;
    }

    //add a song to the end of the playlist
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

    public void addSongAtPosition(Song song, int position)
    {
        SinglySongNode newNode = new SinglySongNode(song);
        if (position > (size+1) || position <1)
        {
            System.out.println("Adding a song a position " + position + " is invalid. Position should be between 1 and " + (size+1) + " inclusive");
            return;
        }
        //add the song at position 1
        else if (position == 1)
        {
            newNode.next = head;
            head = newNode;
            
        }
        //add a song at the last position
        else if (position == (size+1))
        {
            SinglySongNode current = head;
            while (current.next!=null)
            {
                current = current.next;    
            }
           
        }
        //add a song in between the first and last position
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

    //remove a song by title
    public void removeSongByTitle(String title)
    {
        SinglySongNode current = head;
        if (head == null)
        {
            System.out.println("The playlist is empty");
            return;
        }
        else if (head.data.getTitle().equals(title))
        {
            head = head.next;
            size--;
            return;
        }

        while (current.next!=null)
        {
            //if the current nodes' next has the title to remove, copy the reference of the node after that node to the current one so the one with the title is deleted
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


    //remove a song by position
    public void removeSongByPosition(int position)
    {
        if (position ==1)
        {
            head = head.next;
        }
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

    //Display the list in order
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

    //get the total dutation of the playlist (in seconds)
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

    public int getSizePlaylist()
    {
        return size;
    }

    public boolean isPlaylistEmpty()
    {
        return size==0;
    }
}
