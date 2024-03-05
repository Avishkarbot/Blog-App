package net.avishkar.springboot.service;

import net.avishkar.springboot.dto.PostDto;

import java.util.List;

public interface PostService
{
    List<PostDto> findAllPosts();
    List<PostDto> findPostsByUser();
    void createPost(PostDto postDto);
    PostDto findPostById(Long postId);
    void updatePost(PostDto postDto);
    void deletPost(Long postId);

    PostDto findPostByUrl(String postUrl);

    List<PostDto> searchPosts(String query);
}
