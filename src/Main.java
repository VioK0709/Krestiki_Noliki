import java.util.Random;

public class Main {
    public static final int SIZE = 5;
    public static final char CROSS = 'X';
    public static final char ZERO = 'O';
    public static final char EMPTY = '-';

    public static void main(String[] args) {
        char[][] field = new char[SIZE][SIZE];
        boolean isCrossTurn = true;
        int player = 0;

        while (true) {
            if ((player > SIZE * SIZE) || (player == 0)) {
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        field[i][j] = EMPTY;

                    }
                }
                player = 0;
            }
            player++;
            printField(field);
            System.out.println("Ходят " + (isCrossTurn ? "крестики" : "нолики") + "!");
            Random id = new Random();
            int r = id.nextInt(SIZE);
            int c = id.nextInt(SIZE);

            if (field[r][c] != EMPTY) {
                System.out.println("Сюда ходить нельзя");
                continue;
            }
            field[r][c] = isCrossTurn ? CROSS : ZERO;
            if (isWin(field, isCrossTurn ? CROSS : ZERO)) {
                printField(field);
                System.out.println("Победили " + (isCrossTurn ? "крестики" : "нолики"));

                break;
            } else {
                if (isCrossTurn) {
                    isCrossTurn = false;
                } else {
                    isCrossTurn = true;

                }
            }
        }
        System.out.println("Игра закончена!");
    }

    public static boolean isWin(char[][] field, char player) {
        boolean vert = true;
        boolean horiz = true;
        boolean diagFor = true;
        boolean diagRev = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                vert = (j == 0) || vert;
                horiz = (j == 0) || horiz;
                if (field[i][j] == player) horiz = true;
                if (field[i][j] != player) horiz = false;
                if (field[j][i] == player) vert = true;
                if (field[j][i] != player) vert = false;
                if (((i + j) == SIZE - 1) && (field[j][i] != player)) diagFor = false;
                if ((i == j) && (field[j][i] != player)) diagRev = false;
            }
        }
        return (diagFor || diagRev);
    }

    public static void printField(char[][] field) {
        for (char[] row : field) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}



