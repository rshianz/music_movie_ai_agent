public class Album {
    private String title;
    private String artist;
    private String description;
    private String genre;


    public Album(String title, String artist, String description, String genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.description = description;

    }

    // Getters
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getDescription() { return description; }
    public String getGenre() { return genre; }

    @Override
    public String toString() {
        return String.format("Album: %s (Artist: %s, Genre: %s, Description: %s)",
                getTitle(), getArtist(), getGenre(), getDescription());
    }
}
