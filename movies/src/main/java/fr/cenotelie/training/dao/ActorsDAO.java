package fr.cenotelie.training.dao;

import fr.cenotelie.training.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorsDAO extends JpaRepository<Actor, Long> {
}
