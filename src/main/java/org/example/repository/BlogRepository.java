package org.example.repository;

import org.example.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findBySlug(String slug);



    @Modifying
    @Query("delete from Blog b where b.id in :ids")
    void deleteBlogsWithIds(@Param("ids") List<Integer> ids);


}
