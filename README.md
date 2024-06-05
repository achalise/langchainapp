# langchainapp

See [Building FullStack LangChain4J Application using SpringBoot and React](https://chalise-arun.medium.com/step-by-step-guide-to-building-a-fullstack-langchain4j-application-0dfd4f0ef7bc) for more details.

Ensure OpenAI API Key is available as env variable:

```export AI_OPENAI_API_KEY=$(AI_OPENAI_API_KEY)```

And Docker desktop is running.
To start the application 

```./mvnw spring-boot:run```

We can now query our internal data using RAG:

```
GET http://localhost:8080/chat?question=Please list the current applications

HTTP/1.1 200 OK
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: text/plain;charset=UTF-8
Content-Length: 1002

Here are the current applications:

| Correlation ID | Email           | First Name | Last Name | Address                       | Claim Type   | Amount | Status           |
|----------------|-----------------|------------|-----------|-------------------------------|--------------|--------|------------------|
| 18666A         | curtis@gmail.com | Brock      | Curtis    | 76 Chalmers Ave, Redfern 2119 | FLOODS       | 2000   | SUBMITTED        |
| 19966A         | curtis@gmail.com | Brock      | Curtis    | 76 Chalmers Ave, Redfern 2119 | BUSH_FIRE    | 550    | SUBMITTED        |
| 21966A         | curtis@gmail.com | Brock      | Curtis    | 76 Chalmers Ave, Redfern 2119 | HAIL_DAMAGE  | 250    | SUBMITTED        |
| 36559B         | blogg@gmail.com  | Joe        | Blogg     | 40 Bridge Road, Sydney, 2000  | BUSH_FIRE    | 300    | APPROVED         |
| 91137D         | duran@gmail.com  | Leandro    | Duran     | 199 George Street, Newtown, 2000 | HAIL_DAMAGE | 300    | PAYMENT_SUBMITTED |

Response code: 200 (OK); Time: 5417ms (5 s 417 ms); Content length: 1002 bytes (1 kB)

```

# UI

A chatbot interacting with the agent above and built using React is available in following repository:

https://github.com/achalise/langchainapp-ui

