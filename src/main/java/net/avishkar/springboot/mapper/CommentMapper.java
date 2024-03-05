package net.avishkar.springboot.mapper;

import net.avishkar.springboot.dto.CommentDto;
import net.avishkar.springboot.entity.Comment;

public class CommentMapper
{
    // convert Comment Entity to CommentDto
    public static CommentDto mapToCommentDto(Comment comment)
    {
       return CommentDto.builder().id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createdOn(comment.getCreatedOn())
                .updateOn(comment.getUpdatedOn())
                .build();
    }

    // convert CommentDto to Comment Entity
    public static Comment mapToComment(CommentDto commentDto)
    {
        return Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .content(commentDto.getContent())
                .createdOn(commentDto.getCreatedOn())
                .updatedOn(commentDto.getUpdateOn())
                .build();
    }
}
