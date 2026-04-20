package com.example.ancient_architecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ancient_architecture.model.Architecture;

public interface ArchitectureRepository extends JpaRepository<Architecture, Long> {

    
    List<Architecture> findByEnglishNameContainingIgnoreCase(String keyword);


@Query("SELECT a FROM Architecture a WHERE " +
       "LOWER(a.chineseName)         LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.englishName)        LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.pinyinName)         LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.description)        LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.dynasty)            LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.buildingType)       LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.yearBuild)          LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.historicalSignificance) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.location)           LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.architectureStyle)   LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.constructionMaterials) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
       "LOWER(a.briefDescription)   LIKE LOWER(CONCAT('%', :query, '%'))")
List<Architecture> searchByKeyword(@Param("query") String query);


    
    @Query("SELECT a FROM Architecture a WHERE " +
           "LOWER(a.chineseName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(a.englishName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Architecture> searchForSuggestions(String query);
   

}
