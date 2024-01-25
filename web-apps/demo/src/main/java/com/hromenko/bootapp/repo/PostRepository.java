package com.hromenko.bootapp.repo;

import com.hromenko.bootapp.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PostRepository extends CrudRepository<Post, Long> {
}
