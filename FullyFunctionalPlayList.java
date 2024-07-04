/**
 * A class representing a fully functional playlist using a doubly linked circular list structure.
 */
public class FullyFunctionalPlayList {
    /**
     * Inner class representing a node in the playlist.
     */
    private class Node {
        Song data; // Data (Song) stored in the node
        Node previous;  // Reference to the previous node
        Node next; //reference to the next node

        /**
         * 
         * @param data
         */
        Node(Song data) {
            this.data = data;
            this.previous = null;
            this.next = null;
        }
    }
    public Node head;
    public Node tail;
    public int size;
    public Node currentSong;
    public boolean continuousPlay;


    /**
     * Constructs an empty FullyFunctionalPlayList with initial values.
     */
    FullyFunctionalPlayList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.currentSong = null;
        this.continuousPlay = false;
    }

    /**
     * Adds a song to the end of the palylist
     * @param song
     */
    public void addSongAtEnd(Song song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = newNode;
            tail = newNode;
            head.next = head; // Circular link
            head.previous = head; // Circular link
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            newNode.next = head;
            head.previous = newNode;
            tail = newNode;
        }
        size++;
        if (size == 1) {
            currentSong = newNode;
        }
    }

    /**
     * Add a song at a specific position
     * @param song
     * @param position
     */
    public void addSongAtPosition(Song song, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Invalid position. Position should be between 1 and " + (size + 1) + " inclusive");
            return;
        }

        Node newNode = new Node(song);
        if (position == 1) {
            if (head == null) {
                head = newNode;
                tail = newNode;
                head.next = head;
                head.previous = head;
            } else {
                newNode.next = head;
                newNode.previous = tail;
                head.previous = newNode;
                tail.next = newNode;
                head = newNode;
            }
        } else if (position == size + 1) {
            tail.next = newNode;
            newNode.previous = tail;
            newNode.next = head;
            head.previous = newNode;
            tail = newNode;
        } else {
            Node current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.previous = current;
            current.next.previous = newNode;
            current.next = newNode;
        }
        size++;
        if (size == 1) {
            currentSong = newNode;
        }
    }

    /**
     * Remove a song by title
     * @param title
     */
    public void removeSongByTitle(String title) {
        if (size == 0) {
            System.out.println("The list is empty");
            return;
        }

        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.getTitle().equals(title)) {
                if (current == head) {
                    head = head.next;
                    tail.next = head;
                    head.previous = tail;
                } else if (current == tail) {
                    tail = tail.previous;
                    tail.next = head;
                    head.previous = tail;
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                size--;
                if (size == 0) {
                    head = null;
                    tail = null;
                    currentSong = null;
                } else if (current == currentSong) {
                    currentSong = head;
                }
                System.out.println("Removed song: " + title);
                return;
            }
            current = current.next;
        }
        System.out.println("Song with title \"" + title + "\" not found in the playlist.");
    }

    /**
     *  Remove a song by position
     * @param position
     */
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
            tail.next = head;
            head.previous = tail;
        } else if (position == size) {
            tail = tail.previous;
            tail.next = head;
            head.previous = tail;
        } else {
            Node current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            current.next.previous = current;
        }
        size--;
        if (size == 0) {
            head = null;
            tail = null;
            currentSong = null;
        } else if (currentSong == null) {
            currentSong = head;
        }
    }

    /**
     * play the next song
     */
    public void playNextSong() {
    if (currentSong != null) {
        currentSong = currentSong.next;
        if (currentSong == head && !continuousPlay) {
            currentSong = null;
            System.out.println("Reached the end of the playlist. Continuous play is OFF.");
        } else {
            System.out.println("Playing next song: " + currentSong.data.getTitle());
        }
    } else {
        System.out.println("No next song available.");
    }

    // Check if continuous play is ON and the current song is null (end of playlist)
    if (continuousPlay && currentSong == null && head != null) {
        currentSong = head;
        System.out.println("Restarting playlist from the beginning.");
        System.out.println("Playing next song: " + currentSong.data.getTitle());
    }
}

    /**
     * Play the previous song
     */
    public void playPreviousSong() 
    {
    if (currentSong != null) {
        currentSong = currentSong.previous;
        if (currentSong == tail && !continuousPlay) {
            currentSong = null;
            System.out.println("Reached the beginning of the playlist. Continuous play is OFF.");
        } else {
            System.out.println("Playing previous song: " + currentSong.data.getTitle());
        }
    } else {
        System.out.println("No previous song available.");
    }

    // Check if continuous play is ON and the current song is null (beginning of playlist)
    if (continuousPlay && currentSong == null && tail != null) {
        currentSong = tail;
        System.out.println("Restarting playlist from the end.");
        System.out.println("Playing previous song: " + currentSong.data.getTitle());
    }
    }

    /**
     * Toggles continuous play mode on or off.
     */
    public void toggleContinuousPlay() {
        continuousPlay = !continuousPlay;
        System.out.println("Continuous play mode: " + (continuousPlay ? "ON" : "OFF"));
    }

    /**
     * Shuffle the playlist
     */
    public void shufflePlaylist() {
        if (size <= 1) return;

        Node[] nodes = new Node[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            nodes[i] = current;
            current = current.next;
        }

        java.util.Collections.shuffle(java.util.Arrays.asList(nodes));

        head = nodes[0];
        head.previous = nodes[size - 1];
        current = head;
        for (int i = 1; i < size; i++) {
            current.next = nodes[i];
            nodes[i].previous = current;
            current = current.next;
        }
        tail = current;
        tail.next = head;
        head.previous = tail;

        currentSong = head;
    }

    /**
     * Display the list in  order
     */
    public void displayPlaylist() {
        if (size == 0) {
            System.out.println("The playlist is empty.");
            return;
        }
        Node current = head;
        for (int i = 0; i < size; i++) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    /**
     * return the total duration of the songs 
     * @return
     */
    public int getTotalDuration() {
        int totalDuration = 0;
        Node current = head;
        for (int i = 0; i < size; i++) {
            totalDuration += current.data.getDuration();
            current = current.next;
        }
        return totalDuration;
    }

    /**
     * return the size of the list
     * @return
     */
    public int getSizePlaylist() {
        return size;
    }


}