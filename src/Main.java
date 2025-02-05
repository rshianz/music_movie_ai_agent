import java.util.*;

public class Main {
    private static final String EMBEDDINGS_FILE_PATH_SONGS = "embeddings_songs.dat";
    private static final String EMBEDDINGS_FILE_PATH_ALBUMS = "embeddings_albums.dat";
    private static final String EMBEDDINGS_FILE_PATH_MOVIES = "embeddings_movies.dat";

    public static void main(String[] args) {
        try {
            System.out.println("Initializing components...");
            ps();
            EmbeddingGenerator embeddingGenerator = new EmbeddingGenerator();
            LLMClient llmClient = new LLMClient();
            ps();
            // Load database data:
            String csvFilePathSongs = "UltimateClassicRock.csv";
            List<Song> songs = SongCSVReader.readSongsFromCSV(csvFilePathSongs);
            ps();
            String csvFilePathAlbums = "rym_top_5000_all_time.csv";
            List<Album> albums = SongCSVReader.readAlbumsFromCSV(csvFilePathAlbums);
            ps();
            String csvFilePathMovies = "movie.csv";
            List<Movie> movies = SongCSVReader.readMoviesFromCSV(csvFilePathMovies);
            ps();
            // Vector db
            VectorDB<Song> songDB = new VectorDB<>();
            VectorDB<Album> albumDB = new VectorDB<>();
            VectorDB<Movie> movieDB = new VectorDB<>();
            ps();
            System.out.println();
            // Embeddings
            //song
            List<float[]> songEmbeddings = EmbeddingCache.loadEmbeddings(EMBEDDINGS_FILE_PATH_SONGS);
            if (songEmbeddings == null) {
                songEmbeddings = new ArrayList<>();
                for (Song song : songs) {
                    float[] embedding = embeddingGenerator.generateEmbedding(
                            song.getTitle() + " by " + song.getArtist() + " from " + song.getAlbum() + " album "
                    );
                    songEmbeddings.add(embedding);
                    songDB.indexItem(song, embedding);
                    System.out.println(":0");
                }
                EmbeddingCache.saveEmbeddings(songEmbeddings, EMBEDDINGS_FILE_PATH_SONGS);
                System.out.println("done songs embed");
            } else {
                for (int i = 0; i < songs.size(); i++) {
                    songDB.indexItem(songs.get(i), songEmbeddings.get(i));
                }
            }

            //albums
            List<float[]> albumEmbeddings = EmbeddingCache.loadEmbeddings(EMBEDDINGS_FILE_PATH_ALBUMS);
            if (albumEmbeddings == null) {
                albumEmbeddings = new ArrayList<>();
                for (Album album : albums) {
                    float[] embedding = embeddingGenerator.generateEmbedding(
                            album.getTitle() + " by " + album.getArtist() + " in genre " + album.getGenre() + " describes " + album.getDescription()
                    );
                    albumEmbeddings.add(embedding);
                    albumDB.indexItem(album, embedding);
                    System.out.println("0_o");
                }
                EmbeddingCache.saveEmbeddings(albumEmbeddings, EMBEDDINGS_FILE_PATH_ALBUMS);
                System.out.println("done albums embed");
            } else {
                for (int i = 0; i < albums.size(); i++) {
                    albumDB.indexItem(albums.get(i), albumEmbeddings.get(i));
                }
            }

            // movie
            List<float[]> movieEmbeddings = EmbeddingCache.loadEmbeddings(EMBEDDINGS_FILE_PATH_MOVIES);
            if (movieEmbeddings == null) {
                movieEmbeddings = new ArrayList<>();
                for (Movie movie : movies) {
                    float[] embedding = embeddingGenerator.generateEmbedding(
                            movie.getTitle() + " directed by " + movie.getDirector() + " made in " + movie.getOrigin()
                            + " in " + movie.getGenre() + "genre, description is " + movie.getPlot()
                    );
                    movieEmbeddings.add(embedding);
                    movieDB.indexItem(movie, embedding);
                    System.out.println("*_*");
                }
                EmbeddingCache.saveEmbeddings(movieEmbeddings, EMBEDDINGS_FILE_PATH_MOVIES);
                System.out.println("done movie embed");
            } else {
                for (int i = 0; i < movies.size(); i++) {
                    movieDB.indexItem(movies.get(i), movieEmbeddings.get(i));
                }
            }

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Choose a section: 1. Songs 2. Albums 3. Movies (or type 'exit' to quit):");
                String userChoice = scanner.nextLine();

                if (userChoice.equalsIgnoreCase("exit")) {
                    break;
                }

                RAGMusicAgent<?> agent;
                switch (userChoice) {
                    case "1":
                        agent = new RAGMusicAgent<>(songDB, embeddingGenerator, llmClient);
                        break;
                    case "2":
                        agent = new RAGMusicAgent<>(albumDB, embeddingGenerator, llmClient);
                        break;
                    case "3":
                        agent = new RAGMusicAgent<>(movieDB, embeddingGenerator, llmClient);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        continue;
                }

                // User query
                System.out.println("Enter your query:");
                String userQuery = scanner.nextLine();
                System.out.println("Processing your query ...");

                String response = agent.recommend(userQuery);
                System.out.println("AI Response:\n" + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void ps(){
        System.out.print("*******");
    }
}