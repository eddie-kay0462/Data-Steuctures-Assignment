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
    public DoublySongNode head;
    public DoublySongNode tail;
    private int size;
    private DoublySongNode currentSong; // Add this variable
    private boolean continuousPlay;

    public EnhancedPlayList() {
        this.size = 0;
        this.currentSong= null;
        this.continuousPlay = false;
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
        // Update currentSong if it's the first song added
        if (size == 1) {
            currentSong = newNode;
        }
    }

    // Add a song at a specific position
    public void addSongAtPosition(Song song, int position) {
        if (position < 1 || position > size) {
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
            DoublySongNode current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.previous = current;
            current.next.previous = newNode;
            current.next = newNode;
        }
        size++;
        // Update currentSong if it's the first song added
        if (size == 1) {
            currentSong = newNode;
        }
    }

    // Remove a song by title
    public void removeSongByTitle(String title) {
        if (size == 0) {
            System.out.println("The list is empty");
            return;
        }

        DoublySongNode current = head;
        while (current != null) {
            if (current.data.getTitle().equals(title)) {
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.previous = null;
                    } else {
                        tail = null;
                    }
                } else if (current == tail) {
                    tail = tail.previous;
                    if (tail != null) {
                        tail.next = null;
                    } else {
                        head = null;
                    }
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                size--;
                // Adjust currentSong if the removed song was currentSong
                if (current == currentSong) {
                    currentSong = head; 
                }
                System.out.println("Removed song: " + current.data.getTitle());

                return;
            }
            current = current.next;
        }
        System.out.println("ERROR:Song titled " + title + "is not in the list");
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
            DoublySongNode current = head;
            for (int i = 1; i < position - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            current.next.previous = current;
        }
        size--;
        // Adjust currentSong if the removed song was currentSong
        if (currentSong == null) {
            currentSong = head; 
        }
    }

    // Play the next song
    // public String playNextSong() {
    //     if (currentSong != null && currentSong.next != null) {
    //         currentSong = currentSong.next;
    //         return "Playing " + currentSong.data;
    //     }
    //     return "No next song to play";
    // }
    public void playNextSong() {
        if (currentSong != null && currentSong.next != head) {
            currentSong = currentSong.next;
            System.out.println("Playing next song: " + currentSong.data.getTitle());
        } else if (continuousPlay && currentSong != null) {
            currentSong = head;
            System.out.println("Playing next song: " + currentSong.data.getTitle());
        } else {
            System.out.println("No next song available.");
        }
    }

    
    // // Play the previous song
    // public String playPreviousSong() {
    //     if (currentSong != null && currentSong.previous != null) {
    //         currentSong = currentSong.previous;
    //         return "Playing " + currentSong.data;
    //     }
    //     return "No previous song to play";
    // }


    public void playPreviousSong() {
        if (currentSong != null && currentSong.previous != tail) {
            currentSong = currentSong.previous;
            System.out.println("Playing previous song: " + currentSong.data.getTitle());
        } else if (continuousPlay && currentSong != null) {
            currentSong = tail;
            System.out.println("Playing previous song: " + currentSong.data.getTitle());
        } else {
            System.out.println("No previous song available.");
        }
    }

    public void toggleContinuousPlay() {
        continuousPlay = !continuousPlay;
        System.out.println("Continuous play mode: " + (continuousPlay ? "ON" : "OFF"));
    }
    // Shuffle the playlist
    public void shufflePlaylist() {
        if (size <= 1) return;

        DoublySongNode[] nodes = new DoublySongNode[size];
        DoublySongNode current = head;
        int index = 0;
        while (current != null) {
            nodes[index++] = current;
            current = current.next;
        }

        java.util.Collections.shuffle(java.util.Arrays.asList(nodes));

        head = nodes[0];
        head.previous = null;
        current = head;
        for (int i = 1; i < size; i++) {
            current.next = nodes[i];
            nodes[i].previous = current;
            current = current.next;
        }
        tail = current;
        tail.next = null;

    }

    // Display the playlist in order
    public void displayPlaylist() {
        DoublySongNode current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    // Get the total duration of the playlist
    public int getTotalDuration() {
        int totalDuration = 0;
        DoublySongNode current = head;
        while (current != null) {
            totalDuration += current.data.getDuration();
            current = current.next;
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


