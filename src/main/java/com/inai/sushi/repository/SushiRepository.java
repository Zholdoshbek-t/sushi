package com.inai.sushi.repository;

import com.inai.sushi.entity.Sushi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SushiRepository extends JpaRepository<Sushi, Long> {
    Optional<Sushi> findByName(String name);
}
