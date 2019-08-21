package pl.szczepaniak.school.server.schoolserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.szczepaniak.school.server.schoolserver.model.Post;
import pl.szczepaniak.school.server.schoolserver.model.User;
import pl.szczepaniak.school.server.schoolserver.repository.PostRepository;
import pl.szczepaniak.school.server.schoolserver.repository.UserRepository;

import java.util.Optional;

public abstract class AbstractController {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected PostRepository postRepository;

    protected User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Current user doesn't match given id");
        }

        return user;
    }

    protected Post getPostById(Long id){

        Optional<Post> post = postRepository.findById(id);
        if (post.get() == null) {
            throw new ResourceNotFoundException("Current post doesn't match given id");
        }
        return post.get();
    }
}
