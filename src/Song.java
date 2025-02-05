public class Song {
    private String title;
    private String artist;
    private String album;
    private int year;
    private String duration;
    private int timeSignature;
    private double danceability;
    private double energy;
    private int key;
    private double loudness;
    private int mode;
    private double speechiness;
    private double acousticness;
    private double instrumentalness;
    private double liveness;
    private double valence;
    private double tempo;
    private int popularity;

    public Song(String title, String artist, String album, int year, String duration, int timeSignature,
                double danceability, double energy, int key, double loudness, int mode, double speechiness,
                double acousticness, double instrumentalness, double liveness, double valence, double tempo,
                int popularity) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.duration = duration;
        this.timeSignature = timeSignature;
        this.danceability = danceability;
        this.energy = energy;
        this.key = key;
        this.loudness = loudness;
        this.mode = mode;
        this.speechiness = speechiness;
        this.acousticness = acousticness;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.valence = valence;
        this.tempo = tempo;
        this.popularity = popularity;
    }

    // Getters
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getAlbum() { return album; }
    public int getYear() { return year; }
    public String getDuration() { return duration; }
    public int getTimeSignature() { return timeSignature; }
    public double getDanceability() { return danceability; }
    public double getEnergy() { return energy; }
    public int getKey() { return key; }
    public double getLoudness() { return loudness; }
    public int getMode() { return mode; }
    public double getSpeechiness() { return speechiness; }
    public double getAcousticness() { return acousticness; }
    public double getInstrumentalness() { return instrumentalness; }
    public double getLiveness() { return liveness; }
    public double getValence() { return valence; }
    public double getTempo() { return tempo; }
    public int getPopularity() { return popularity; }

    @Override
    public String toString() {
        return String.format("%s by %s (Album: %s, Year: %d, Danceability: %.2f, Energy: %.2f, Tempo: %.0f, Popularity: %d)",
                title, artist, album, year, danceability, energy, tempo, popularity);
    }
}