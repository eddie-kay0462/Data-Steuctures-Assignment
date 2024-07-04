import java.util.Scanner;

public class PlayListTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EnhancedPlayList playlist = new EnhancedPlayList();
        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Add Song");
            System.out.println("2. Add Song at Position");
            System.out.println("3. Remove Song by Title");
            System.out.println("4. Remove Song by Position");
            System.out.println("5. Play Next Song");
            System.out.println("6. Play Previous Song");
            System.out.println("7. Shuffle Playlist");
            System.out.println("8. Display Playlist");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            while (!scanner.hasNextInt())
            {
                System.out.println("Invalid input. Please enter a number (1-9)");   
                scanner.next(); //consume the invalid input
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine().trim();
                    System.out.print("Enter artist name: ");
                    String artist = scanner.nextLine().trim();
                    System.out.print("Enter duration in seconds: ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    playlist.addSongAtEnd(new Song(title, artist, duration));
                    break;
                case 2:
                    System.out.print("Enter song title: ");
                    String titlePos = scanner.nextLine().trim();
                    System.out.print("Enter artist name: ");
                    String artistPos = scanner.nextLine().trim();
                    System.out.print("Enter duration in seconds: ");
                    int durationPos = scanner.nextInt();
                    System.out.print("Enter position: ");
                    int position = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    playlist.addSongAtPosition(new Song(titlePos, artistPos, durationPos), position);
                    break;
                case 3:
                    System.out.print("Enter song title to remove: ");
                    String removeTitle = scanner.nextLine().trim();
                    playlist.removeSongByTitle(removeTitle);
                    break;
                case 4:
                    System.out.print("Enter song position to remove: ");
                    int removePosition = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    playlist.removeSongByPosition(removePosition);
                    break;
                case 5:
                    System.out.println(playlist.playNextSong());
                    break;
                case 6:
                    System.out.println(playlist.playPreviousSong());
                    break;
                case 7:
                    playlist.shufflePlaylist();
                    System.out.println("Playlist shuffled");
                    break;
                case 8:
                    System.out.println("----------------------------------------------");
                    playlist.displayPlaylist();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    exit = true;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}