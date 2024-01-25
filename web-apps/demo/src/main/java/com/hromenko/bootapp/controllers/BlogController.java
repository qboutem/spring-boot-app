package com.hromenko.bootapp.controllers;

import com.hromenko.bootapp.models.Post;
import com.hromenko.bootapp.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/web")
public class BlogController {
    private final PostRepository postRepository;
    @Autowired
    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/blog")
    public String blog(Model model){
        Iterable<Post> posts= postRepository.findAll();
        model.addAttribute("posts",posts);
        return "blogPage";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        Post post=new Post(title, anons, full_text);
        postRepository.save(post);
        return "redirect:/web/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value="id") Long id, Model model){
        if(postRepository.existsById(id)){
            Optional<Post> post=postRepository.findById(id);
            ArrayList<Post> res=new ArrayList<>();
            post.ifPresent(res::add);
            model.addAttribute("post", res);
            return "blog-details";

        }
        return "redirect:/web/blog";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value="id") Long id, Model model){
        if(postRepository.existsById(id)){
            Optional<Post> post=postRepository.findById(id);
            ArrayList<Post> res=new ArrayList<>();
            post.ifPresent(res::add);
            model.addAttribute("post", res);
            return "blog-edit";

        }
        return "redirect:/web/blog";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogEditPost(@PathVariable(value="id") Long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setFull_text(full_text);
        post.setAnons(anons);
        postRepository.save(post);
        return "redirect:/web/blog";
    }

   // @RequestMapping(value ="/blog/{id}/remove", method={RequestMethod.POST, RequestMethod.GET})
    @PostMapping("/blog/{id}/remove")
    public String blogRemovePost(@PathVariable(value = "id") Long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/web/blog";
    }
}
