package com.dropship.uploadshoppeart.repo;

import com.dropship.uploadshoppeart.dto.ArticleSellyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleSellyRepo extends JpaRepository<ArticleSellyDto,Integer> {
  public List<ArticleSellyDto> findArticleSellyDtosByArticleType(String articleType);
}
