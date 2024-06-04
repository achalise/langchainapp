package example.app.langchain.langchainapp.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import example.app.langchain.langchainapp.llm.CustomerServiceAgent;
import example.app.langchain.langchainapp.llm.CustomerServiceTools;
import example.app.langchain.langchainapp.service.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.List;

@Configuration
public class Config {
    @Bean
    CustomerServiceAgent customerSupportAgent(StreamingChatLanguageModel streamingChatLanguageModel,
                                              ChatLanguageModel chatLanguageModel,
                                              ContentRetriever contentRetriever,
                                              CustomerServiceTools customerService) {
        return AiServices.builder(CustomerServiceAgent.class)
                .chatLanguageModel(chatLanguageModel)
                .streamingChatLanguageModel(streamingChatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .tools(customerService)
                .contentRetriever(contentRetriever)
                .build();
    }

    @Bean
    CustomerServiceTools customerServiceTools() {
        return new CustomerServiceTools(new CustomerService());
    }

    @Bean
    StreamingChatLanguageModel streamingModel() {
        String key = System.getenv("AI_OPENAI_API_KEY");
        return OpenAiStreamingChatModel.withApiKey(key);
    }

    @Bean
    ChatLanguageModel chatLanguageModel() {
        String key = System.getenv("AI_OPENAI_API_KEY");
        return OpenAiChatModel.withApiKey(key);
    }

    @Bean
    ContentRetriever contentRetriever(EmbeddingStore<TextSegment> embeddingStore, EmbeddingModel embeddingModel) {
        int maxResults = 1;
        double minScore = 0.6;

        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(maxResults)
                .minScore(minScore)
                .build();
    }

    @Bean
    EmbeddingModel embeddingModel() {
        return new AllMiniLmL6V2EmbeddingModel();
    }

    @Bean
    EmbeddingStore<TextSegment> embeddingStore(EmbeddingModel embeddingModel, ResourceLoader resourceLoader) throws IOException {

        // Normally, you would already have your embedding store filled with your data.
        // However, for the purpose of this demonstration, we will:

        // 1. Create an in-memory embedding store
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        return embeddingStore;
    }

    @Bean
    FileSystemResourceLoader resourceLoader() {
        return new FileSystemResourceLoader();
    }

}
