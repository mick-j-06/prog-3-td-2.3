package app.foot.service;

import app.foot.exception.BadRequestException;
import app.foot.model.Match;
import app.foot.model.PlayerScorer;
import app.foot.repository.MatchRepository;
import app.foot.repository.PlayerScoreRepository;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.mapper.PlayerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PlayerScoreService {
    private PlayerScoreRepository playerScoreRepository;
    private PlayerMapper playerMapper;
    private MatchRepository matchRepository;
    private MatchService matchService;

    public synchronized Match saveAllByMatchId(Integer matchId, List<PlayerScorer> playerScorerList) throws InterruptedException {
        if (matchRepository.existsById(matchId)) {
            MatchEntity matchEntity = matchRepository.getReferenceById(matchId);
            Thread thread = new Thread(() -> {
                playerScoreRepository.saveAll(
                        playerScorerList.stream()
                                .map(playerScorer -> playerMapper.toDomain(matchEntity, playerScorer))
                                .toList()
                );
            });
            thread.start();
            Thread.sleep(500);
            return matchService.getMatch(matchId);
        } else {
            throw new BadRequestException("Match Id: " + matchId + " does not exist");
        }
    }
}
