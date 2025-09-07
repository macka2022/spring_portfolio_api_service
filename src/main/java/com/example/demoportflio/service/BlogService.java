package com.example.demoportflio.service;

import com.example.demoportflio.model.Blog;

import java.util.List;

public interface BlogService {
     List<Blog> getAllBlogs();
     Blog getBlogById(Long id);
     Blog addBlog(Blog blog);
     Blog updateBlog(Blog blog);
     void deleteBlog(Blog blog);


}
