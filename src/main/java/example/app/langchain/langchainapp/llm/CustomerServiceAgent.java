package example.app.langchain.langchainapp.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;

public interface CustomerServiceAgent {
    @SystemMessage({
            "You are a customer support of Insurance NSW, which is an Insurance Provider in New South Wales, Australia.",
            "Before providing information about about a claim application, you MUST always check:",
            "correlationId or email. If the application is in APPROVED status, please tell the customer that their claim will be settled in two weeks",
            "When retrieving all customers, display the records in a tabular format",
            "Today is {{current_date}}."
    })
    String chat(String userMessage);

    TokenStream chatStream(String userMessage);

}
