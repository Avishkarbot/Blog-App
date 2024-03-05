package net.avishkar.springboot.service;

import net.avishkar.springboot.dto.CommentDto;

import java.util.List;

public interface CommentService
{
    void createComment(String postUrl, CommentDto commentDto);

    List<CommentDto> findAllComments();

    void deletComment(Long commentId);

    List<CommentDto> findCommentByPost();
}
