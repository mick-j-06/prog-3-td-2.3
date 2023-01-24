import app.foot.model.Player;
import app.foot.model.PlayerScorer;
import app.foot.repository.MatchRepository;
import app.foot.repository.PlayerRepository;
import app.foot.repository.entity.MatchEntity;
import app.foot.repository.entity.PlayerEntity;
import app.foot.repository.entity.PlayerScoreEntity;
import app.foot.repository.entity.TeamEntity;
import app.foot.repository.mapper.PlayerMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

//TODO-2: complete these tests
public class PlayerMapperTest {
    private MatchRepository matchRepository = Mockito.mock(MatchRepository.class);
    private PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
    private PlayerMapper subject = new PlayerMapper(matchRepository, playerRepository);

    @Test
    void player_to_domain_ok() {
        PlayerEntity playerEntity = playerEntity();
        Player expected = Player.builder()
                .id(playerEntity.getId())
                .name(playerEntity.getName())
                .isGuardian(playerEntity.isGuardian())
                .teamName(playerEntity.getTeam().getName())
                .build();
        Player actual = subject.toDomain(playerEntity);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void player_scorer_to_domain_ok() {
        PlayerScorer expected = PlayerScorer.builder()
                .player(player(playerEntity()))
                .isOwnGoal(false)
                .build();
        PlayerScorer actual = subject.toDomain(PlayerScoreEntity.builder()
                .player(playerEntity())
                .build());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void player_scorer_to_entity_ok() {
        Mockito.when(playerRepository.findById(Mockito.eq(1))).thenReturn(Optional.of(playerEntity()));
        Mockito.when(matchRepository.findById(Mockito.eq(1))).thenReturn(Optional.of(matchEntity()));

        PlayerScoreEntity expected = PlayerScoreEntity.builder()
                .player(playerEntity())
                .match(matchEntity())
                .build();

        PlayerScoreEntity actual = subject.toEntity(
                1,
                PlayerScorer.builder()
                        .player(player(playerEntity()))
                        .isOwnGoal(false)
                        .build()
        );

        Assertions.assertEquals(expected, actual);
    }

    private static MatchEntity matchEntity() {
        return MatchEntity.builder()
                .id(1)
                .scorers(List.of())
                .teamA(teamEntity_1())
                .teamB(teamEntity_2())
                .build();
    }

    private static Player player(PlayerEntity playerEntity) {
        return Player.builder()
                .id(playerEntity.getId())
                .name(playerEntity.getName())
                .isGuardian(playerEntity.isGuardian())
                .teamName(playerEntity.getTeam().getName())
                .build();
    }

    private static PlayerEntity playerEntity() {
        return PlayerEntity.builder()
                .id(1)
                .name("player_1")
                .guardian(false)
                .team(teamEntity_1())
                .build();
    }

    private static TeamEntity teamEntity_1() {
        return TeamEntity.builder()
                .id(1)
                .name("team_1")
                .build();
    }

    private static TeamEntity teamEntity_2() {
        return TeamEntity.builder()
                .id(2)
                .name("team_2")
                .build();
    }
}
