package ui;

import model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        System.out.println("Bienvenido al juego.");
        System.out.print("¿Cuántos jugadores van a jugar? ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Por favor, ingresa sus nombres");
        String[] namesPlayers = new String[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            namesPlayers[i]=scanner.nextLine();
        }
        game.initializeGame(numPlayers, namesPlayers);
        game.playGame();
    }


}