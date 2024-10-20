package fr.cenotelie.training.flux.movies

import jakarta.persistence.*
import java.time.LocalDate

enum class Genre {
    Action, Adventure, Comedy, Crime, Drama, Fantasy, Horror, Mystery, Thriller
}

@Entity
data class Actor (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val firstname: String,
    val email: String?,
    val birthdate: LocalDate?,
    /*@ManyToMany(mappedBy = "actors")
    val movies: List<Any>*/
)

@Entity
data class Movie (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val title: String,
    val releaseDate: LocalDate,
    /*@ManyToMany
    @JoinTable(
        name = "Acting",
        joinColumns = [JoinColumn(name = "actor_id")],
        inverseJoinColumns = [JoinColumn(name = "movie_id")]
    )
    val actors: List<Actor>*/
)