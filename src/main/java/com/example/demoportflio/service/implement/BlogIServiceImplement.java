package com.example.demoportflio.service.implement;

import com.example.demoportflio.exception.user.ApiExecptionHandler;
import com.example.demoportflio.model.Blog;
import com.example.demoportflio.model.Section;
import com.example.demoportflio.repository.BlogRepository;
import com.example.demoportflio.repository.SectionRepository;
import com.example.demoportflio.service.BlogService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogIServiceImplement implements BlogService {

    private final BlogRepository blogRepository;
    private final SectionRepository sectionRepository;

    public BlogIServiceImplement(BlogRepository blogRepository, SectionRepository sectionRepository) {
        this.blogRepository = blogRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<Blog> getAllBlogs() {

        return blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Section avec id " + id + " non trouvée"));
    }

    @Override
    public Blog addBlog(Blog blog) {
        Long BlogId = blog.getSection().getId(); // extraire l'id du user (seulement l'id est envoyé dans le JSON)


        Section BlogFromDb =sectionRepository.findById(BlogId)
                .orElseThrow(() -> new RuntimeException("Section introuvable  avec l'ID : " + BlogId));


        blog.setSection(BlogFromDb);
        return blogRepository.save(blog);

    }

    @Override
    public Blog updateBlog(Blog blog) {
        Long BlogId = blog.getSection().getId(); // extraire l'id du user (seulement l'id est envoyé dans le JSON)


        Section BlogFromDb =sectionRepository.findById(BlogId)
                .orElseThrow(() -> new RuntimeException("Section introuvable  avec l'ID : " + BlogId));


        blog.setSection(BlogFromDb);
        return blogRepository.save(blog);
    }

    @Override
    public void  deleteBlog(Blog blog) {
        if (!blogRepository.existsById(   blog.getId()))  {
            throw new ApiExecptionHandler.UserNotFoundException("blog avec id " + blog.getId() + " n'existe pas");
        }
      blogRepository.delete(blog);

    }
}
