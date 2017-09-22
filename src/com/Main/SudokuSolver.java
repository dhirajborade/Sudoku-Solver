package com.Main;

import java.util.HashSet;
import java.util.Set;

public final class SudokuSolver {

	private SudokuSolver() {
	}

	private static void printSolution(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " : ");
			}
			System.out.println();
		}
	}

	private static Set<Integer> getNumber(int[][] board, int row, int col) {
		final Set<Integer> intsToAvoid = new HashSet<Integer>();

		for (int i = 0; i < board[0].length; i++) {
			if (board[row][i] > 0) {
				intsToAvoid.add(board[row][i]);
			}
		}
		
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] > 0) {
				intsToAvoid.add(board[i][col]);
			}
		}

		int lowerRowIndex = (row / 3) * 3;
		int lowerColumnIndex = (col / 3) * 3;

		for (int i = lowerRowIndex; i < lowerRowIndex + 3; i++) {
			for (int j = lowerColumnIndex; j < lowerColumnIndex + 3; j++) {
				if (board[i][j] > 0) {
					intsToAvoid.add(board[i][j]);
				}
			}
		}

		final Set<Integer> candidateInts = new HashSet<Integer>();
		for (int i = 1; i <= board.length; i++) {
			if (!intsToAvoid.contains(i)) {
				candidateInts.add(i);
			}
		}
		return candidateInts;
	}

	private static boolean solveSudoku(int[][] board, int row, int col) {
		if (col == board[0].length) {
			row++;
			if (row == board.length) {
				printSolution(board);
				return true;
			}
			col = 0;
		}

		if (board[row][col] > 0) {
			return solveSudoku(board, row, col + 1);
		} else {
			for (int i : getNumber(board, row, col)) {
				board[row][col] = i;
				if (solveSudoku(board, row, col + 1))
					return true;
				board[row][col] = 0;
			}
		}
		return false;
	}

	public static boolean solve(int[][] sudoku) {
		return solveSudoku(sudoku, 0, 0);
	}

	public static void main(String[] args) {
		int[][] board = { 
				{ 3, 0, 6, 5, 0, 8, 4, 0, 0 }, 
				{ 5, 2, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, 
				{ 9, 0, 0, 8, 6, 3, 0, 0, 5 }, 
				{ 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 7, 4 }, 
				{ 0, 0, 5, 2, 0, 6, 3, 0, 0 }
			};

		System.out.println(solve(board));
	}
}