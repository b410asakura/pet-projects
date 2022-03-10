package com.b410asakura.firstSpringProject.repo;

import com.b410asakura.firstSpringProject.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
