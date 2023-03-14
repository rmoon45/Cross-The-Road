package frogger;

import java.util.List;

public class ScoreManager {

    public static int getScoreAfterMove(int score, String currentSquare, boolean scoreChange) {
        if (scoreChange) {
            if (currentSquare == "road") {
                score += 2;
            } else if (currentSquare == "river") {
                score += 3;
            } else {
                score += 1;
            }
            return score;

        }
        return score;
    }

    public static String getTileCorrespondingToPosition(int playerPosition, List<String> map) {
        return map.get(map.size() - playerPosition - 1);
    }
}
