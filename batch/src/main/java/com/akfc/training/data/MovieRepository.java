package com.akfc.training.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Long> {}