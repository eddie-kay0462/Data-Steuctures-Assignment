// Class representing a node in a doubly linked list
class DoublySongNode {
    Song data; // the song data stored in this node
    DoublySongNode previous; // reference to the previous node in the list
    DoublySongNode next; // reference to the next node in the list

    // Constructor to create a new node with given song data
    DoublySongNode(Song data) {
        this.data = data;
        this.previous = null;
        this.next = null;
    }
}

// Class representing an enhanced playlist implemented as a doubly linked list
public class EnhancedPlayList {
    protected DoublySongNode head;
    protected DoublySongNode tail;
    private DoublySongNode current;
    protected int size;

    // Constructor to initialize an empty playlist
    public EnhancedPlayList() {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.size = 0;
    }

    // Method to add a song at the end of the playlist
    public void addSongAtEnd(Song song) {
        DoublySongNode newNode = new DoublySongNode(song);
        if (head == null) { // if the list is empty, let both head and tail point to the new node
            head = newNode;
            tail = newNode;
        } else { // if the list is not empty, append new node to the end
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++; // Increment the size of the playlist
    }

    // Adds a song at a specific position in the playlist
    public void addSongAtPosition(Song song, int position) {
        // Validate position
        if (position < 1 || position > size + 1) {
            System.out.println("Adding a song at position " + position + " is invalid. Position should be between 1 and " + (size + 1) + " inclusive");
            return;
        }

        DoublySongNode newNode = new DoublySongNode(song);
        // Add at the beginning
        if (position == 1) {
            newNode.next = head;
            if (head != null) {
                head.previous = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else if (position == size + 1) { // Add at the end
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        } else {
            DoublySongNode node = head;
            for (int i = 1; i < position - 1; i++) { // Traverse to the position to add
                node = node.next;
            }
            newNode.next = node.next;
            newNode.previous = node;
            node.next.previous = newNode;
            node.next = newNode;
        }
        size++;
    }

    // Removes a song by the title
    public void removeSongByTitle(String title) {
        if (size == 0) { // Check if the playlist is empty
            System.out.println("The list is empty");
            return;
        }

        DoublySongNode node = head;
        while (node != null) { // Traverse the list to find the song
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
                size--; // Decrement the size of the playlist
                return;
            }
            node = node.next;
        }
        System.out.println("ERROR: Song titled " + title + " is not in the list");
    }

    // Removes a song by its position
    public void removeSongByPosition(int position) {
        if (size == 0) {
            System.out.println("The list is empty");
            return;
        }

        if (position < 1 || position > size) {
            System.out.println("Invalid position. Position should be between 1 and " + size + " inclusive");
            return;
        }

        if (position == 1) { // Remove from the beginning
            head = head.next;
            if (head != null) {
                head.previous = null;
            } else {
                tail = null;
            }
        } else if (position == size) { // Remove from the end
            tail = tail.previous;
            tail.next = null;
        } else {
            DoublySongNode node = head;
            for (int i = 1; i < position - 1; i++) { // Traverse to the given position
                node = node.next;
            }
            node.next = node.next.next;
            node.next.previous = node;
        }
        size--;
    }

    // Play the next song
    public void playNextSong() {
        // If no song is currently playing, start with the head
        if (current == null) {
            current = head;
        } else if (current.next != null) { // If there is a next song, move to it
            current = current.next;
        } else { // If at the end of the playlist
            System.out.println("No next song to play.");
            return;
        }
        if (current != null) {
            System.out.println("Playing: " + current.data.getTitle());
        }
    }

    // Play the previous song
    public void playPreviousSong() {
        if (current == null) {
            current = head;
        } else if (current.previous != null) { // If there is a previous song, move to it
            current = current.previous;
        } else { // If at the start of the playlist
            System.out.println("No previous song to play.");
            return;
        }
        if (current != null) {
            System.out.println("Playing: " + current.data.getTitle());
        }
    }

    // Shuffle the playlist
    public void shufflePlaylist() {
        if (size <= 1) return; // No need to shuffle if the playlist has 0 or 1 song

        DoublySongNode[] nodes = new DoublySongNode[size];
        DoublySongNode node = head;
        int index = 0;
        while (node != null) { // Populate the array with nodes from the list
            nodes[index++] = node;
            node = node.next;
        }

        java.util.Collections.shuffle(java.util.Arrays.asList(nodes)); // Shuffle the array

        head = nodes[0];
        head.previous = null;
        node = head;
        for (int i = 1; i < size; i++) { // Reconstruct the doubly linked list from the shuffled array
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
        while (node != null) { // Traverse the list and print each song
            System.out.println(node.data);
            node = node.next;
        }
    }

    // Get the total duration of the playlist
    public int getTotalDuration() {
        int totalDuration = 0;
        DoublySongNode node = head;
        while (node != null) { // Traverse the list and sum the durations of all songs
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
