import models.PlayerHands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static final String TEXT_PATH = "src/main/java/test.txt";
    public static int player1Wins = 0;
    public static int totalGames = 0;
    public static void main(String[] args) throws IOException {

        Path path = Paths.get(TEXT_PATH);
        Files.lines(path).forEach(line -> {
            PlayerHands playerHands = new PlayerHands(line.split(" "));
            if (playerHands.playerOneWon()) {
                player1Wins++;
                System.out.println(line);
            }
            totalGames++;
        });

        System.out.printf("Player one won %d out of %d games",player1Wins,totalGames);
    }
}
