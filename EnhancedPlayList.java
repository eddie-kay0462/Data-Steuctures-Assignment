class DoublySongNode {
    Song data;
    DoublySongNode previous;
    DoublySongNode next;

    DoublySongNode(Song data) {
        this.data = data;
        this.previous = null;
        this.next = null;
    }
}

public class EnhancedPlayList {
    private DoublySongNode head;
    private DoublySongNode tail;
    private DoublySongNode current;
    private int size;
    public boolean continuousPlayMode;
  

    public EnhancedPlayList() {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.size = 0;
        this.continuousPlayMode = false;
    }

    // Add a song to the end of the playlist
    public void addSongAtEnd(Song song) {
        DoublySongNode newNode = new DoublySongNode(song);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
       
    }

    // Add a song at a specific position
    public void addSongAtPosition(Song song, int position) {
        if (position < 1 || position > size+1) {
            System.out.println("Adding a song at position " + position + " is invalid. Position should be between 1 and " + (size + 1) + " inclusive");
            return;
        }

        DoublySongNode newNode = new DoublySongNode(song);
        if (position == 1) {
            newNode.next = head;
            if (head != null) {
                head.previous = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else if (position == size + 1) {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        } else {
            DoublySongNode node = head;
            for (int i = 1; i < position - 1; i++) {
                node = node.next;
            }
            newNode.next = node.next;
            newNode.previous = node;
            node.next.previous = newNode;
            node.next = newNode;
        }
        size++;
      
    }

    // Remove a song by title
    public void removeSongByTitle(String title) {
        if (size == 0) {
            System.out.println("The list is empty");
            return;
        }

        DoublySongNode node = head;
        while (node != null) {
            if (node.data.getTitle().equals(title)) {
                if (node == head) {
                    head = head.next;
                    if (head != null) {
                        head.previous = null;
                    } else {
                        tail = null;
                    }
                } else if (node == tail) {
                    tail = tail.previous;
                    if (tail != null) {
                        tail.next = null;
                    } else {
                        head = null;
                    }
                } else {
                    node.previous.next = node.next;
                    node.next.previous = node.previous;
                }
                size--;
           
                return;
            }
            node = node.next;
        }
        System.out.println("ERROR:Song titled " + title + " is not in the list");
    }

    // Remove a song by position
    public void removeSongByPosition(int position) {
        if (size == 0) {
            System.out.println("The list is empty");
            return;
        }

        if (position < 1 || position > size) {
            System.out.println("Invalid position. Position should be between 1 and " + size + " inclusive");
            return;
        }

        if (position == 1) {
            head = head.next;
            if (head != null) {
                head.previous = null;
            } else {
                tail = null;
            }
        } else if (position == size) {
            tail = tail.previous;
            tail.next = null;
        } else {
            DoublySongNode node = head;
            for (int i = 1; i < position - 1; i++) {
                node = node.next;
            }
            node.next = node.next.next;
            node.next.previous = node;
        }
        size--;
       
    }


    /**
     * Play the next song
     */
    public void playNextSong()
    {
        if (current == null)
        {
            current = head;
        }
        else if (current.next!=null)
        {
            current = current.next;
        }
        else
        {
            if (continuousPlayMode)
            {
                current = head;
            }
            else
            {
                System.out.println("No next song to play.");
                return;
            }
            
        }
        if (current!=null)
        {
            System.out.println("Playing: " + current.data.getTitle());
        }
       
    }

    //play the previous song
    public void playPreviousSong()
    {
        if (current == null)
        {
            current = head;
        }
        else if (current.previous!=null)
        {
            current = current.previous;
        }
        else
        {   
            if (continuousPlayMode)
            {
                current = tail;
            }
            else
            {
                System.out.println("No previous song to play.");
                return;
            }
           
        }
        if (current!=null)
        {
            System.out.println("Playing: " + current.data.getTitle());
        }
        
    }

 

    // Shuffle the playlist
    public void shufflePlaylist() {
        if (size <= 1) return;

        DoublySongNode[] nodes = new DoublySongNode[size];
        DoublySongNode node = head;
        int index = 0;
        while (node != null) {
            nodes[index++] = node;
            node = node.next;
        }

        java.util.Collections.shuffle(java.util.Arrays.asList(nodes));

        head = nodes[0];
        head.previous = null;
        node = head;
        for (int i = 1; i < size; i++) {
            node.next = nodes[i];
            nodes[i].previous = node;
            node = node.next;
        }
        tail = node;
        tail.next = null;

    }

    // Display the playlist in order
    public void displayPlaylist() {
        DoublySongNode node = head;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    // Get the total duration of the playlist
    public int getTotalDuration() {
        int totalDuration = 0;
        DoublySongNode node = head;
        while (node != null) {
            totalDuration += node.data.getDuration();
            node = node.next;
        }
        return totalDuration;
    }

    // Get the size of the playlist
    public int getSizePlaylist() {
        return size;
    }

    // Check if the playlist is empty
    public boolean isPlaylistEmpty() {
        return size == 0;
    }

}


