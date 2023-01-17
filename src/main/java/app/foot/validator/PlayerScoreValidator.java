package app.foot.validator;

import app.foot.exception.BadRequestException;
import app.foot.model.PlayerScorer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
public class PlayerScoreValidator implements Consumer<PlayerScorer>  {
    @Override
    public void accept(PlayerScorer playerScorer) {
        if (playerScorer.getPlayer().getIsGuardian()) {
            throw new BadRequestException("Guardian can not register goal");
        }
        else if (playerScorer.getMinute() < 0 || playerScorer.getMinute() > 90) {
            throw new BadRequestException("Invalid time");
        }
    }

    public void accept(List<PlayerScorer> playerScorerList) {
        playerScorerList.forEach(this::accept);
    }
}
