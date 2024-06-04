package example.app.langchain.langchainapp.service;

public record Customer(String correlationId, String email, String firstName, String lastName, String address, String claimType, long amount, String status){}
