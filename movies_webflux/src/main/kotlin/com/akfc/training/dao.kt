package com.akfc.training

import jakarta.transaction.Transactional
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
@Transactional
interface MoviesDAO : ReactiveCrudRepository<Movie, Long>

@Repository
@Transactional
interface ActorsDAO : ReactiveCrudRepository<Actor, Long>
