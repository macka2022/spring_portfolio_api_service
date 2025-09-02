package com.example.demoportflio.service;

import com.example.demoportflio.model.Blog;

import java.util.List;

public interface BlogService {
    public List<Blog> getAllBlogs();
    public Blog getBlogById(Long id);
    public Blog addBlog(Blog blog);
    public Blog updateBlog(Blog blog);
    public void deleteBlog(Blog blog);


}
