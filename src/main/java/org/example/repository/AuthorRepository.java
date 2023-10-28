package org.example.repository;

import org.example.model.Author;
import org.example.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByEmail(String email);
}
