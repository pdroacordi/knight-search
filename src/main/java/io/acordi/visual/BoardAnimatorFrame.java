package io.acordi.visual;

import io.acordi.entities.Position;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BoardAnimatorFrame extends JFrame {

    private int boardSize;
    private int[][] board;
    private static final int TILE_SIZE = 80; // tamanho de cada quadrado

    public BoardAnimatorFrame(int boardSize) {
        this.boardSize = boardSize;
        this.board = new int[boardSize][boardSize];

        setTitle("Knight's Tour Animation");
        setSize(boardSize * TILE_SIZE + 16, boardSize * TILE_SIZE + 39); // margem do JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void animate(List<Position> path) throws InterruptedException {
        int step = 1;

        for (Position pos : path) {
            board[pos.getX()][pos.getY()] = step++;
            repaint();
            Thread.sleep(300); // delay entre os movimentos
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int x = j * TILE_SIZE;
                int y = i * TILE_SIZE + 30; // compensar barra superior

                // cor das casas do tabuleiro
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(x, y, TILE_SIZE, TILE_SIZE);

                // desenhar número do passo
                if (board[i][j] > 0) {
                    g.setColor(Color.RED);
                    g.setFont(new Font("Arial", Font.BOLD, 24));
                    g.drawString(String.valueOf(board[i][j]), x + TILE_SIZE / 2 - 8, y + TILE_SIZE / 2 + 8);
                }
            }
        }
    }
}
