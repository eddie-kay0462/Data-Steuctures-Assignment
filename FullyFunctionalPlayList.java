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
    private boolean isContinuous = true;
    private int size = 0;

    // Add a song to the end of the playlist
    public void addSongAtEnd(Song song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = newNode;
            tail = newNode;
            head.next = head;
            head.prev = tail;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            tail = newNode;
            head.prev = tail;
        }
        size++;
    }

    // Add a song at a specific position (position starting from 1)
    public void addSongAtPosition(Song song, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Invalid postion");
        }
        Node newNode = new Node(song);
        if (position == 1) {
            if (head == null) {
                head = newNode;
                tail = newNode;
                head.next = head;
                head.prev = tail;
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
        boolean found = false;
        do {
            if (current.song.getTitle().equals(title)) {
                found = true;
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
                break;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("Song with title '" + title + "' is not in the list");
        }
    }


    // Remove a song by position (position starting from 1)
    public void removeSongByPosition(int position) {
        if (head == null || position < 1 || position > size) {
            System.out.println("Invalid position");
            return;
        }
        Node current = head;
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
            for (int i = 1; i < position; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
            if (current == tail) {
                tail = current.prev;
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
        } else if (current.next != head) {
            current = current.next;
        } else {
            if (isContinuous) {
                current = current.next;
            } else {
                System.out.println("No next song to play");
                return;
            }
        }
        if (current != null) {
            System.out.println("Playing: " + current.song);
        }
    }

    // Play the previous song
    public void playPreviousSong() {
        if (current == null) {
            current = head;
        } else if (current.prev != tail) {
            current = current.prev;
        } else {
            if (isContinuous) {
                current = current.prev;
            } else {
                System.out.println("No previous song to play");
                return;
            }
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
}
