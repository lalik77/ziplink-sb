package com.mami.ziplink.repository;

import com.mami.ziplink.models.ClickEvent;
import com.mami.ziplink.models.UrlMapping;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvent, Long> {
  List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping mapping, LocalDateTime startDate,
                                                       LocalDateTime endTime);

  List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMappings, LocalDateTime startDate,
                                                         LocalDateTime endDate);
}
