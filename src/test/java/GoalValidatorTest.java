import app.foot.controller.rest.Player;
import app.foot.controller.rest.PlayerScorer;
import app.foot.controller.validator.GoalValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

//TODO-1: complete these tests
public class GoalValidatorTest {
    GoalValidator subject = new GoalValidator();

    @Test
    void accept_ok() {
        GoalValidator validator = Mockito.mock(GoalValidator.class);
        PlayerScorer playerScorer = playerscorer(player1(), 20);

        validator.accept(playerScorer);
        Mockito.verify(validator).accept(playerScorer);
        Mockito.verify(validator, Mockito.times(1)).accept(Mockito.any(PlayerScorer.class));

        Assertions.assertFalse(playerScorer.getPlayer().getIsGuardian());
        Assertions.assertNotNull(playerScorer.getScoreTime());
        Assertions.assertFalse(playerScorer.getScoreTime() < 0 || playerScorer.getScoreTime() > 90);
    }

    //Mandatory attributes not provided : scoreTime
    @Test
    void accept_ko() {
        PlayerScorer playerScorer = playerscorer(player1(), null);

        Assertions.assertNull(playerScorer.getScoreTime());
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            subject.accept(playerScorer);
        });
    }

    @Test
    void when_guardian_throws_exception() {
        PlayerScorer playerScorer = playerscorer(player2(), 10);

        Assertions.assertTrue(playerScorer.getPlayer().getIsGuardian());
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            subject.accept(playerScorer);
        });
        Assertions.assertEquals("Player#2 is a guardian so they cannot score.", exception.getMessage());
    }

    @Test
    void when_score_time_greater_than_90_throws_exception() {
        PlayerScorer playerScorer = playerscorer(player1(), 100);

        Assertions.assertTrue(playerScorer.getScoreTime() > 90);
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            subject.accept(playerScorer);
        });
        Assertions.assertEquals("Player#player_1 cannot score before after minute 90.", exception.getMessage());
    }

    @Test
    void when_score_time_less_than_0_throws_exception() {
        PlayerScorer playerScorer = playerscorer(player1(), -10);

        Assertions.assertTrue(playerScorer.getScoreTime() < 0);
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            subject.accept(playerScorer);
        });
        Assertions.assertEquals("Player#1 cannot score before before minute 0.", exception.getMessage());
    }

    private static PlayerScorer playerscorer(Player player, Integer scoreTime) {
        return PlayerScorer.builder()
                .player(player)
                .scoreTime(scoreTime)
                .isOG(false)
                .build();
    }

    private static Player player1() {
        return Player.builder()
                .id(1)
                .name("player_1")
                .isGuardian(false)
                .build();
    }

    private static Player player2() {
        return Player.builder()
                .id(2)
                .name("player_2")
                .isGuardian(true)
                .build();
    }
}
