package it.unibz.ddd.service;

public class RepositoryFactory {
    private static StudentRepository repository;

    public static StudentRepository fromStudent() {
        return repository;
    }

    public static void configure(StudentRepository repo) {
        repository = repo;
    }
}
