package com.example.bestalbam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bestalbam.model.Photo;


@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{
    List<Photo> findByFilepath(String filepath);
    List<Photo> findByStatus(int status);

    @Query(value = "SELECT * FROM photos  WHERE filepath = :filepath", nativeQuery = true)
    List<Photo> findByFilepathSQL(@Param("filepath") String filepath);
}
