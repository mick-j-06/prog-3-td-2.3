package app.foot.repository.mapper;

import app.foot.model.Player;
import app.foot.model.PlayerScorer;
import app.foot.repository.MatchRepository;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player toDomain(PlayerEntity entity) {
        return Player.builder()
                .id(entity.getId())
                .name(entity.getName())
                .isGuardian(entity.isGuardian())
                .build();
    }

    public PlayerScorer toDomain(PlayerScoreEntity entity) {
        return PlayerScorer.builder()
                .player(toDomain(entity.getPlayer()))
                .minute(entity.getMinute())
                .isOwnGoal(entity.isOwnGoal())
                .build();
    }

    public PlayerEntity toDomain(Player player) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(player.getId());
        playerEntity.setName(player.getName());
        playerEntity.setGuardian(player.getIsGuardian());
        return playerEntity;
    }

    public PlayerScoreEntity toDomain(MatchEntity matchEntity, PlayerScorer playerScorer) {
        PlayerScoreEntity playerScoreEntity = new PlayerScoreEntity();
        playerScoreEntity.setPlayer(this.toDomain(playerScorer.getPlayer()));
        playerScoreEntity.setMinute(playerScorer.getMinute());
        playerScoreEntity.setOwnGoal(playerScorer.getIsOwnGoal());
        playerScoreEntity.setMatch(matchEntity);
        return playerScoreEntity;
    }
}
