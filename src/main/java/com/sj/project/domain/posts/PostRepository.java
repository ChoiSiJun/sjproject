package com.sj.project.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts,Long> {
}
