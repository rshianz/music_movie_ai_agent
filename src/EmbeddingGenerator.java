import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class EmbeddingGenerator {
    private static final String API_URL = "http://localhost:11434/api";

    public float[] generateEmbedding(String text) {
        try {
            URL url = new URL(API_URL + "/embeddings");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject payload = new JSONObject();
            payload.put("model", "nomic-embed-text");
            payload.put("prompt", text);

            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.writeBytes(payload.toString());
                wr.flush();
            }

            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the response
                JSONArray jsonArray = new JSONObject(response.toString()).getJSONArray("embedding");
                float[] embedding = new float[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    embedding[i] = (float) jsonArray.getDouble(i);
                }
                return embedding;
            } else {
                System.out.println("There has been an error with the response code: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}