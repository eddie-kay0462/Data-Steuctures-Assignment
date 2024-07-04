public class FullyFunctionalPlaylist extends EnhancedPlayList {
    private boolean continuousPlayMode;
    private DoublySongNode current;

    public FullyFunctionalPlaylist() {
        super();
        this.continuousPlayMode = false;
    }

    @Override
    public String playNextSong() {
        if (current == null || current.next == null) {
            current = continuousPlayMode ? head : null;
        } else {
            current = current.next;
        }
        return current != null ? "Playing " + current.data : null;
    }

    @Override
    public String playPreviousSong() {
        if (current == null || current.previous == null) {
            current = continuousPlayMode ? tail : null;
        } else {
            current = current.previous;
        }
        return current != null ? "Playing " + current.data : null;
    }

    // Toggle continuous play mode
    public void toggleContinuousPlayMode() {
        this.continuousPlayMode = !this.continuousPlayMode;
    }

    @Override
    public void addSongAtEnd(Song song) {
        super.addSongAtEnd(song);
        if (continuousPlayMode && tail != null) {
            tail.next = head; // Make the list circular
            head.previous = tail;
        }
    }

    @Override
    public void removeSongByTitle(String title) {
        super.removeSongByTitle(title);
        if (continuousPlayMode && tail != null) {
            tail.next = head; // Maintain the circular nature
            head.previous = tail;
        }
    }

    @Override
    public void removeSongByPosition(int position) {
        super.removeSongByPosition(position);
        if (continuousPlayMode && tail != null) {
            tail.next = head; // Maintain the circular nature
            head.previous = tail;
        }
    }

    @Override
    public void shufflePlaylist() {
        super.shufflePlaylist();
        if (continuousPlayMode && tail != null) {
            tail.next = head; // Maintain the circular nature
            head.previous = tail;
        }
    }
}
