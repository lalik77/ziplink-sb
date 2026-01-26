package com.mami.ziplink.repository;

import com.mami.ziplink.models.UrlMapping;
import com.mami.ziplink.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping,Long> {
  UrlMapping findByShortUrl(String shortUrl);
  List<UrlMapping> findByUser(User user);

}
