public class Movie {
    private int releaseYear;
    private String title;
    private String originEthnicity;
    private String director;
    private String cast;
    private String genre;
    private String wikiPage;
    private String plot;

    // Constructor
    public Movie(int releaseYear, String title, String originEthnicity, String director, String cast, String genre, String wikiPage, String plot) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.originEthnicity = originEthnicity;
        this.director = director;
        this.cast = cast;
        this.genre = genre;
        this.wikiPage = wikiPage;
        this.plot = plot;
    }

    // Getters and Setters (optional, depending on your use case)
    public int getReleaseYear() {
        return releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginEthnicity() {
        return originEthnicity;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }

    public String getGenre() {
        return genre;
    }

    public String getWikiPage() {
        return wikiPage;
    }

    public String getPlot() {
        return plot;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "releaseYear=" + releaseYear +
                ", title='" + title + '\'' +
                ", originEthnicity='" + originEthnicity + '\'' +
                ", director='" + director + '\'' +
                ", cast='" + cast + '\'' +
                ", genre='" + genre + '\'' +
                ", wikiPage='" + wikiPage + '\'' +
                ", plot='" + plot + '\'' +
                '}';
    }
}