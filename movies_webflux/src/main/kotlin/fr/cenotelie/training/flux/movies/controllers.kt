package fr.cenotelie.training.flux.movies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/movies")
class MovieController {

    @Autowired
    lateinit var dao: MoviesDAO

    @GetMapping
    fun all() : Flux<Movie> = dao.findAll()
}