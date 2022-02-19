package test;
import game.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        ArrayList<Move> moves;
        String reply = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the name of player 1?");
        String player1 = sc.nextLine();
        System.out.println("What is the name of player2");
        String player2 = sc.nextLine();
        Game game = new Game(player1, player2);
        game.showBoard(System.out);

        do {
            System.out.println("What command would you like?");
            reply = sc.nextLine();

            switch (reply) {
                case "new game":
                    game = new Game(player1, player2);
                    game.showBoard(System.out);
                    break;
                case "print":
                    String secReply = sc.next();
                    if (secReply.equalsIgnoreCase("status")) {
                        game.showBoard(System.out);
                        System.out.println("List of moves: " + game);
                    } else {
                        System.out.println("Sorry! Invalid Command! Please try again!");
                    }
                    break;
                case "move":
                    System.out.print("Make a move: ");
                    String move = sc.next();
                    boolean moveValid = move.matches("[A-Ha-h1-8]*");
                    if (moveValid) {
                        game.move(new Move(move));
                        game.showBoard(System.out);
                    } else {
                        System.out.println("Sorry! Invalid move! Please try again!");
                    }
                    if (game.isWhiteTurn()) {
                        System.out.println(player1 + " moves: " + move);
                    } else {
                        System.out.println(player2 + " moves: " + move);
                    }
                    break;
                case "cp":
                    System.out.print("Make a capture: ");
                    String cap = sc.next();
                    boolean capValid = cap.matches("A-Ha-h1-8");
                    if (capValid) {
                        game.move(new Move(cap));
                        game.showBoard(System.out);
                    } else {
                        System.out.println("Illegal Move! Please try again!");
                    }
                    if (game.isWhiteTurn()) {
                        System.out.println(player1 + " captures: " + cap);
                    } else {
                        System.out.println(player2 + " captures: " + cap);
                    }
                    break;

                case "undo":
                    if (game == null) {
                        System.out.println("You must start the game!");
                        continue;
                    }
                    Game alt = new Game(game.getPlayer1(), game.getPlayer2());
                    moves = game.getMoves();
                    if (moves.size() == 0) {
                        System.out.println("No moves have been made yet!");
                        continue;
                    }
                    moves.remove(game.getMoves().size() - 1);

                    for (int i = 0; i < moves.size(); i++) {
                        alt.move(moves.get(i));
                    }
                    game = alt;
                    game.showBoard(System.out);
                    break;
                case "quit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Illegal command! Please try again!");
                    break;
            }
        }  while (!reply.equalsIgnoreCase("quit"));
    }
}