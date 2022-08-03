package com.cs2001.group34.repository;

import com.cs2001.group34.dto.GuideComment;
import com.cs2001.group34.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Integer> {

    @Query("select new com.cs2001.group34.dto.GuideComment(comment.user.username,comment.comment) from Comment comment where comment.guide.id =?1")
    List<GuideComment> allCommentsForGuide(Integer guideId);
}
