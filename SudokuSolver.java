public class SudokuSolver {
    static final int GRID_SIZE = 9;
    static final int EMPTY_SLOT = 0;

    public static void main(String[] args) {
        int[][] Grid = {
                {5, 0, 0, 0, 0, 0, 2, 3, 0},
                {0, 3, 0, 4, 0, 2, 1, 0, 0},
                {0, 0, 0, 7, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 7, 0, 0, 0, 0},
                {0, 0, 0, 0, 5, 0, 6, 0, 0},
                {8, 0, 0, 2, 0, 6, 0, 0, 4},
                {0, 0, 9, 0, 0, 0, 0, 0, 8},
                {0, 5, 0, 3, 0, 1, 4, 0, 0},
                {0, 0, 0, 0, 6, 0, 0, 0, 0}
        };

        sudokuSolver(Grid);
        printBoard(Grid);
    }

    public static boolean sudokuSolver(int[][] Grid) {
        for(int row = 0; row < GRID_SIZE; row++) {
            for(int col = 0; col < GRID_SIZE; col++) {
                if(Grid[row][col] == EMPTY_SLOT) {
                    for(int Guess = 1; Guess <= 9; Guess++) {
                        if(checkValidSpot(Grid, Guess, row, col)) {
                            Grid[row][col] = Guess;

                            if(sudokuSolver(Grid)) return true;
                            else Grid[row][col] = EMPTY_SLOT;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkValidSpot(int[][] Grid, int Guess, int row, int col) {
        return checkRow(Grid, Guess, row)
                && checkCol(Grid, Guess, col)
                && checkBlock(Grid, Guess, row / 3, col / 3);
    }

    public static boolean checkRow(int[][] Grid, int Guess, int row) {
        for(int j = 0; j < GRID_SIZE; j++) {
            if(Guess == Grid[row][j]) return false;
        }
        return true;
    }

    public static boolean checkCol(int[][] Grid, int Guess, int col) {
        for(int i = 0; i < GRID_SIZE; i++) {
            if(Guess == Grid[i][col]) return false;
        }
        return true;
    }

    public static boolean checkBlock(int[][] Grid, int Guess, int blockI, int blockJ) {
        for(int i = blockI * 3; i < blockI * 3 + 3; i++) {
            for(int j = blockJ * 3; j < blockJ * 3 + 3; j++) {
                if(Guess == Grid[i][j]) return false;
            }
        }
        return true;
    }

    public static void printBoard(int[][] Grid) {
        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++) {
                if(j % 3 == 0 && j != 0) {
                    System.out.printf("%s%d", "|", Grid[i][j]);
                } else System.out.printf("%d", Grid[i][j]);
            }
            System.out.printf("\n");
            if((i == 2 || i == 5) && i != 0) {
                for(int k = 0; k < 11; k++) {
                    System.out.printf("%s", "-");
                }
                System.out.printf("\n");
            }
        }
    }
}