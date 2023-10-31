package org.example.controller;


import jakarta.transaction.Transactional;
import org.example.model.Author;
import org.example.model.Blog;
import org.example.repository.AuthorRepository;
import org.example.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class BlogController {
    @Autowired
    private final BlogRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public BlogController(BlogRepository repository) {
        this.blogRepository = repository;
    }

    /********************************
    **** GETTING DATA
    *******************************/

    @GetMapping("/blogs")
    @CrossOrigin
    public List<Blog> getAll() {
        return this.blogRepository.findAll();
    }

    @GetMapping("/blogs/{slug}")
    public Blog getBookById(@PathVariable String slug) {
        Blog blog = null;
        blog = this.blogRepository.findBySlug(slug);
        if (blog != null) {
            return blog;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog was not found");
        }
    }

    @GetMapping("/admin/{id}")
    @CrossOrigin
    public Blog getBlogById(@PathVariable int id) {
        Blog blog = null;
        blog = this.blogRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie was not found")
        );
        return blog;
    }


    @GetMapping("/admin/blogs")
    @CrossOrigin
    public List<Blog> getAllAdmins() {
        return this.blogRepository.findAll();
    }


    /********************************
     **** POSTING DATA
     *******************************/



    @PostMapping("/blogs")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public Blog blogCreated(@RequestBody Blog newBlog) throws SQLException {
        Author author = this.authorRepository.findByEmail(newBlog.getAuthor().getEmail());


        OffsetDateTime now = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String timestamp = now.format(formatter);


        if (author == null) {
            author = new Author();
            author.setEmail(newBlog.getAuthor().getEmail());
            author.setFirstName(newBlog.getAuthor().getFirstName());
            author.setLastName(newBlog.getAuthor().getLastName());



            author.setCreatedAt(timestamp);
            author.setUpdatedAt(timestamp);

            author = this.authorRepository.save(author);
        }

        newBlog.setCreatedAt(timestamp);
        newBlog.setUpdatedAt(timestamp);
        newBlog.setAuthor(author);

        return this.blogRepository.save(newBlog);
    }


    /********************************
     **** CHANGING (PUT) DATA
     *******************************/


    @PutMapping("/admin/edit/{id}")
    @CrossOrigin
    public ResponseEntity<Blog> updateMovie(@PathVariable int id, @RequestBody Blog blog) {
        Blog blogToUpdate = this.blogRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie was not found")
        );

        OffsetDateTime now = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String timestamp = now.format(formatter);

        blogToUpdate.setSlug(blog.getSlug());
        blogToUpdate.setBannerURL(blog.getBannerURL());
        blogToUpdate.setTitle(blog.getTitle());
        blogToUpdate.setSummary(blog.getSummary());
        blogToUpdate.setBlogIntroduction(blog.getBlogIntroduction());
        blogToUpdate.setBlogBody(blog.getBlogBody());
        blogToUpdate.setBlogConclusion(blog.getBlogConclusion());
        blogToUpdate.setSlug(blog.getSlug());
        blogToUpdate.setUpdatedAt(timestamp);

        //The code below checks the email of the author.
        //It will create a new author object with the filled in details if it doesn't exist
        Author author = this.authorRepository.findByEmail(blog.getAuthor().getEmail());

        if (author == null) {
            author = new Author();
            author.setEmail(blog.getAuthor().getEmail());
            author.setFirstName(blog.getAuthor().getFirstName());
            author.setLastName(blog.getAuthor().getLastName());

            author.setCreatedAt(timestamp);
            author.setUpdatedAt(timestamp);

            author = this.authorRepository.save(author);
        }

        blogToUpdate.setAuthor(author);


        return new ResponseEntity<>(this.blogRepository.save(blogToUpdate), HttpStatus.CREATED);
    }

    /********************************
     **** DELETE DATA
     *******************************/



    @DeleteMapping("/admin/edit")
    @CrossOrigin
    @Transactional
    public List<Blog> deleteBlogsWithIds(@RequestBody List<Integer> ids) {
        List<Blog> blogsToBeDeleted = new ArrayList<>();

        for (Integer id : ids) {
            Blog blog = blogRepository.findById(id).orElse(null);
            if (blog != null) {
                blogsToBeDeleted.add(blog);
            }
        }

        // Delete the blogs from the BlogRepository
        blogRepository.deleteAll(blogsToBeDeleted);

        return blogsToBeDeleted;

    }


}
