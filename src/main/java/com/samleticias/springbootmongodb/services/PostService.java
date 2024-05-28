package com.samleticias.springbootmongodb.services;

import com.samleticias.springbootmongodb.domain.Post;
import com.samleticias.springbootmongodb.repositories.PostRepository;
import com.samleticias.springbootmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(String id) {
        Optional<Post> obj = postRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){
        //return postRepository.findByTitleContainingIgnoreCase(text);
        return postRepository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate,Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(text, minDate, maxDate);
    }
}
