package example.app.langchain.langchainapp;

import example.app.langchain.langchainapp.llm.CustomerServiceAgent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@CrossOrigin(origins = "*")
public class ChatController {
    private CustomerServiceAgent agent;

    public ChatController(CustomerServiceAgent agent) {
        this.agent = agent;
    }
    @GetMapping("/chat")
    public String chat(@RequestParam("question") String question) {
        System.out.println(STR."Received question \{question}");
        String resp = agent.chat(question);
        System.out.println(resp);
        return resp;
    }
}
