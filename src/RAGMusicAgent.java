import java.util.*;

public class RAGMusicAgent<T> {
    private VectorDB<T> vectorDB;
    private EmbeddingGenerator embeddingGenerator;
    private LLMClient llmClient;

    public RAGMusicAgent(VectorDB<T> vectorDB, EmbeddingGenerator embeddingGenerator, LLMClient llmClient) {
        this.vectorDB = vectorDB;
        this.embeddingGenerator = embeddingGenerator;
        this.llmClient = llmClient;
    }

    public String recommend(String userQuery, String type) throws Exception {
        // Generate query embedding
        ps();
        float[] queryEmbedding = embeddingGenerator.generateEmbedding(userQuery);

        // Retrieve top-K items
        ps();
        List<T> matches = vectorDB.search(queryEmbedding, 3);

        // Build augmented prompt
        ps();
        StringBuilder context = new StringBuilder();
        for (T item : matches) {
            context.append(String.format("- %s\n", item));
        }
        System.out.println(context);
        ps();
        String prompt = String.format("""
            **Task**: You are a %s recommendation assistant. Your goal is to provide personalized recommendations based on the user's query and the context items provided.
            
            **User Query**: %s

            **Context Items**:
            %s
           
            **Instructions**:
            1. Analyze the user's query and the context items.
            2. Provide a detailed explanation for why each recommended item matches the user's preferences.
            3. Suggest additional similar items that the user might like, even if they are not in the context items.
               and suggest them base on user query.
            4. Format your response clearly and professionally.

            **Output Format**:
            - **Top Recommendations**:
              1. [Item Name] by [Artist/Director]: [Explanation]
              2. [Item Name] by [Artist/Director]: [Explanation]
              ...
            - **Additional Suggestions**: [List of similar items]
            """, type ,userQuery, context);
//        System.out.println(prompt);
        // Generate response using LLM
        ps();
        System.out.println();
        return llmClient.generateResponse(prompt);
    }
    public static void ps(){
        System.out.print("********");
    }
}