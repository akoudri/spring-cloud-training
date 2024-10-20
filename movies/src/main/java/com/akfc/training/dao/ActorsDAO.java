package com.akfc.training.dao;

import com.akfc.training.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorsDAO extends JpaRepository<Actor, Long> {
}
