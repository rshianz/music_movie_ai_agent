import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;


public class EmbeddingCache {

    public static void saveEmbeddings(List<float[]> embeddings, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(embeddings);
            System.out.println("Embeddings saved to " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<float[]> loadEmbeddings(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            System.out.println("Embeddings file does not exist or is empty.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<float[]> embeddings = (List<float[]>) ois.readObject();
            System.out.println("Embeddings loaded from " + filePath);
            return embeddings;
        } catch (Exception e) {
            System.out.println("Error loading embeddings: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}