package frogger;

public class ScoreManager {

    public static int getScoreAfterMove(int score, String currentSquare) {
        if (currentSquare == "road") {
            score += 2;
        } else if (currentSquare == "river") {
            score += 3;
        } else {
            score += 1;
        }
        return score;
    }
}
