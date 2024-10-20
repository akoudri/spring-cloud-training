package fr.cenotelie.training.movies.dao;

import fr.cenotelie.training.movies.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorsDAO extends JpaRepository<Actor, Long> {
}
