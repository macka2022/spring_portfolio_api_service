package com.example.demoportflio.controller;


import com.example.demoportflio.model.Blog;

import com.example.demoportflio.response.ResponseHandler;
import com.example.demoportflio.service.BlogService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/blog")
public class BlogController {

  private final  BlogService  blogService;

    public BlogController(final BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addBlog(@Valid  @RequestBody Blog blog) {
            return ResponseHandler.responseBuilder("Blog Ajouté", HttpStatus.OK, blogService.addBlog(blog) );
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listBlog() {
            return  ResponseHandler.responseBuilder("Liste des Blogs", HttpStatus.OK, blogService.getAllBlogs());
    }


    @GetMapping("/list/{id}")
    public ResponseEntity<Object> listBlog(@PathVariable Long id) {
            return ResponseHandler.responseBuilder("Blog ", HttpStatus.OK, blogService.getBlogById(id)) ;
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateBlog(@Valid @RequestBody Blog blog) {
            return  ResponseHandler.responseBuilder("Blog modifié",HttpStatus.OK, blogService.updateBlog(blog) );
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteBlog(@PathVariable Long id) {
        return ResponseHandler.responseBuilder("Blog Suprimé", HttpStatus.OK, blogService.getBlogById(id));
    }

}
