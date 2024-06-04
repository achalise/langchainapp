package example.app.langchain.langchainapp;

import example.app.langchain.langchainapp.service.DocIngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

@RestController()
@CrossOrigin(origins = "*")
public class UploadController {

    private final DocIngestionService docIngestionService;

    @Autowired
    public UploadController(DocIngestionService docIngestionService) {
        this.docIngestionService = docIngestionService;
    }

    @PostMapping(value = "/upload")
    public StatusResponse uploadDocument(@RequestPart("file") FilePart file) {
        System.out.println("The uploaded file: " + file);
        file.headers().forEach((h,j) -> System.out.print(STR."""
                \{h} -> \{j}
                """));
        // Get the filename from the request parameter
        String filename = file.filename();

        // Create a new file in the specified directory with the original filename
        String uploadDirectory = "tmp";
        File newFile = new File(uploadDirectory, filename);

       Mono<Void> asyncStoreDoc = Mono.fromRunnable(() -> {
           try {
               docIngestionService.storeDocument(newFile.getAbsolutePath());
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       });

        file.transferTo(newFile.toPath()).then(asyncStoreDoc).subscribe();

        return new StatusResponse("OK", "Successful");
    }
}

record StatusResponse(String status, String message) { }