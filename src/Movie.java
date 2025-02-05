public class Movie {
    private String title;
    private String origin;
    private String year;
    private String director;
    private String genre;
    private String plot;

    public Movie(String title, String origin, String year, String director, String genre, String plot) {
        this.title = title;
        this.origin = origin;
        this.year = year;
        this.director = director;
        this.genre = genre;
        this.plot = plot;

    }
    public String getTitle() {return title;}
    public String getOrigin() {return origin;}
    public String getYear() {return year;}
    public String getDirector() {return director;}
    public String getGenre() {return genre;}
    public String getPlot() {return plot;}

    @Override
    public String toString() {
        return String.format("Movie: %s (Year: %s, Director: %s, Genre: %s, Plot: %s)",
                title, year, director, genre, plot);
    }

}