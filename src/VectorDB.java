import java.util.ArrayList;
import java.util.List;

// Idea --> cos(@) = a.b / ab

public class VectorDB<T> {
    private List<T> items;
    private List<float[]> embeddings;

    public VectorDB() {
        items = new ArrayList<>();
        embeddings = new ArrayList<>();
    }

    public void indexItem(T item, float[] embedding) {
        if (embedding == null) {
            return;
        }
        items.add(item);
        embeddings.add(embedding);
    }

    public List<T> search(float[] queryEmbedding, int topK) {
        List<T> results = new ArrayList<>();
        List<Double> similarities = new ArrayList<>();

        // Calculate cosine similarity between query and all embeddings
        for (int i = 0; i < embeddings.size(); i++) {
            double similarity = cosineSimilarity(queryEmbedding, embeddings.get(i));
            similarities.add(similarity);
        }

        // Find the top-K most similar items
        for (int i = 0; i < topK; i++) {
            int maxIndex = findMaxIndex(similarities);
            results.add(items.get(maxIndex));
            similarities.set(maxIndex, Double.NEGATIVE_INFINITY); // Mark as used, wont repet
        }

        return results;
    }

    private double cosineSimilarity(float[] vectorA, float[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    private int findMaxIndex(List<Double> list) {
        int maxIndex = 0;
        double maxValue = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > maxValue) {
                maxValue = list.get(i);
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}