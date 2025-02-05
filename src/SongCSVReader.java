import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongCSVReader {
    public static List<Song> readSongsFromCSV(String filePath) throws IOException, CsvException {
        List<Song> songs = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();
            // Skip the header row
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                String title = row[0];
                String artist = row[1];
                String album = row[2];
                int year = Integer.parseInt(row[3]);
                String duration = row[4];
                int timeSignature = Integer.parseInt(row[5]);
                double danceability = Double.parseDouble(row[6]);
                double energy = Double.parseDouble(row[7]);
                int key = Integer.parseInt(row[8]);
                double loudness = Double.parseDouble(row[9]);
                int mode = Integer.parseInt(row[10]);
                double speechiness = Double.parseDouble(row[11]);
                double acousticness = Double.parseDouble(row[12]);
                double instrumentalness = Double.parseDouble(row[13]);
                double liveness = Double.parseDouble(row[14]);
                double valence = Double.parseDouble(row[15]);
                double tempo = Double.parseDouble(row[16]);
                int popularity = Integer.parseInt(row[17]);


                Song song = new Song(title, artist, album, year, duration, timeSignature, danceability, energy, key, loudness, mode, speechiness, acousticness, instrumentalness, liveness, valence, tempo, popularity);
                songs.add(song);
            }
        }
        return songs;
    }

    public static List<Album> readAlbumsFromCSV(String filePath) throws IOException, CsvException {
        List<Album> albums = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> rows = reader.readAll();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                String title = row[1];
                String artist = row[2];
                String genre = row[4];
                String description = row[5];
                Album album = new Album(title, artist, description, genre);
                albums.add(album);


            }
        }
        return albums;
    }


    public static List<Movie> readMoviesFromCSV(String filePath) throws IOException {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                try {
                    String[] row = parseCSVLine(line);
                    if (row.length < 9) {
                        continue;
                    }

                    String title = row[1].trim();
                    String origin = row[2].trim();
                    String director = row[3].trim();
                    String year = row[0].trim();
                    String genre = row[5].trim();
                    String plot = row[7].trim();

                    Movie movie = new Movie(title, origin, year, director, genre, plot);
                    movies.add(movie);
                } catch (Exception e) {
                    System.err.println("Skipping malformed row: " + line);
                    e.printStackTrace();
                }
            }
        }
        return movies;
    }

    private static String[] parseCSVLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes; // Toggle quotes
            } else if (c == ',' && !inQuotes) {
                values.add(sb.toString());
                sb.setLength(0); // Reset StringBuilder
            } else {
                sb.append(c);
            }
        }
        values.add(sb.toString()); // Add the last field
        return values.toArray(new String[0]);
    }

}
