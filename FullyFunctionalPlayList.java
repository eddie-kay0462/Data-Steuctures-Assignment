import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FullyFunctionalPlayList {
    private class Node {
        Song song;
        Node next;
        Node prev;

        Node(Song song) {
            this.song = song;
        }
    }

    private Node head;
    private Node tail;
    private Node current;
    private boolean isContinuous = false;
    private int size = 0;

    // Add a song to the end of the playlist
    public void addSongAtEnd(Song song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
            newNode.prev = tail;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            tail = newNode;
            head.prev = tail;
        }
        size++;
    }

    // Add a song at a specific position
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
                newNode.next = head;
                newNode.prev = tail;
            } else {
                newNode.next = head;
                newNode.prev = tail;
                head.prev = newNode;
                tail.next = newNode;
                head = newNode;
            }
        } else {
            Node current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        size++;
    }

    // Remove a song by title
    public void removeSongByTitle(String title) {
        if (head == null) {
            System.out.println("The list is empty");
            return;
        }
        Node current = head;
        do {
            if (current.song.getTitle().equals(title)) {
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    if (current == head) head = current.next;
                    if (current == tail) tail = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        } while (current != head);
    }

    // Remove a song by position
    public void removeSongByPosition(int position) {
        if (head == null) {
            System.out.println("The list is empty");
            return;
        }
        if (position < 1 || position > size) {
            System.out.println("Invalid position. Position should be between 1 and " + size + " inclusive");
            return;
        }
        if (position == 1) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head.prev.next = head.next;
                head.next.prev = head.prev;
                head = head.next;
            }
        } else {
            Node current = head;
            for (int i = 1; i < position && current.next != head; i++) {
                current = current.next;
            }
            if (current.next != head) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                if (current == tail) {
                    tail = current.prev;
                }
            }
        }
        size--;
    }

    // Display the playlist in order
    public void displayPlaylist() {
        if (head == null) return;
        Node current = head;
        do {
            System.out.println(current.song);
            current = current.next;
        } while (current != head);
    }

    // Calculate the total duration of the playlist
    public int getTotalDuration() {
        int totalDuration = 0;
        if (head == null) return totalDuration;
        Node current = head;
        do {
            totalDuration += current.song.getDuration();
            current = current.next;
        } while (current != head);
        return totalDuration;
    }

    // Play the next song
    public void playNextSong() {
        if (current == null) {
            current = head;
        } else {
            current = current.next;
        }
        if (current != null) {
            System.out.println("Playing: " + current.song);
        }
    }

    // Play the previous song
    public void playPreviousSong() {
        if (current == null) {
            current = head;
        } else {
            current = current.prev;
        }
        if (current != null) {
            System.out.println("Playing: " + current.song);
        }
    }

    // Shuffle the playlist (randomly rearranging songs)
    public void shufflePlaylist() {
        List<Song> songs = new ArrayList<>();
        if (head == null) return;
        Node current = head;
        do {
            songs.add(current.song);
            current = current.next;
        } while (current != head);
        Collections.shuffle(songs);
        head = null;
        tail = null;
        size = 0;
        for (Song song : songs) {
            addSongAtEnd(song);
        }
    }

    // Toggle continuous play mode
    public void toggleContinuousPlayMode() {
        isContinuous = !isContinuous;
        System.out.println("Continuous play mode is now " + (isContinuous ? "enabled" : "disabled"));
    }

    // Get the current size of the playlist
    public int getSize() {
        return size;
    }
}
