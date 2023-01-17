package app.foot.controller;

import app.foot.model.Match;
import app.foot.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MatchController {
    private final MatchService service;

    @GetMapping("/matches")
    public List<Match> getMatches() {
        return service.getMatches();
    }

    @GetMapping("/matches/{id}")
    public Match get(@PathVariable(name = "id") Integer id) {
        return service.getMatch(id);
    }
}
