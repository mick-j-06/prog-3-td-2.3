package app.foot.controller;

import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.service.PlayerScoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PlayerScoreController {
    private PlayerScoreService playerScoreService;

    @PostMapping("/matches/{matchId}/goals")
    public Match postGoals(
            @PathVariable Integer matchId, @RequestBody List<PlayerScorer> playerScorerList) throws InterruptedException {
        return playerScoreService.saveAllByMatchId(matchId, playerScorerList);
    }
}
