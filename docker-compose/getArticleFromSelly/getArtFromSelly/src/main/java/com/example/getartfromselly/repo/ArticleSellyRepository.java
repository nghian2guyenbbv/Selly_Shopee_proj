package com.example.getartfromselly.repo;

import com.example.getartfromselly.entity.ArticleSellyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleSellyRepository extends JpaRepository<ArticleSellyDto, Integer> {
}
