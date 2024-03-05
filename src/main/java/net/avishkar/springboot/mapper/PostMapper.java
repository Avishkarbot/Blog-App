package net.avishkar.springboot.mapper;

import net.avishkar.springboot.dto.PostDto;
import net.avishkar.springboot.entity.Post;

import java.util.stream.Collectors;

public class PostMapper
{
    // map Post entity to PostDto
    public static PostDto mapToPostDto(Post post)
    {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .url(post.getUrl())
                .comments(post.getComments().stream()
                        .map((comment)->CommentMapper.mapToCommentDto(comment)).collect(Collectors.toSet()))
                .build();
    }

    // map postDto to Post entity
    public static Post mapToPost(PostDto postDto)
    {
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .shortDescription(postDto.getShortDescription())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn())
                .url(postDto.getUrl())
                .build();
    }
}
