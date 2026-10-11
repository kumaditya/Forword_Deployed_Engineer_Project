package in.sp.main.controller;

import org.springframework.web.bind.annotation.*;

import in.sp.main.model.MovieMatch;
import in.sp.main.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/search")
	public List<MovieMatch> search(@RequestParam String query) {
		return movieService.search(query);
	}

	@GetMapping("/{title}/similar")
	public List<MovieMatch> similarMovies(@PathVariable String title) {
		return movieService.similarMovies(title);
	}
}