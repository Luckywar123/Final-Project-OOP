import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JPanel;


public class Main extends JPanel { 

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int size;
    private int nbTiles;
    private int dimension;
    private static final Color PRIMARY_COLOR = new Color(192,192,192);
    private static final Color FOREGROUND_COLOR = new Color(255,0,0);
    private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);  
    private static final Random RANDOM = new Random();
    private int[] tiles;
    private int tileSize;
    private int blankPos;
    private int margin;
    private int gridSize;
    private boolean gameOver; 

    public Main(final int size, final int dim, final int mar) {
        this.size = size;
        dimension = dim;
        margin = mar;

        nbTiles = size * size - 1; 
        tiles = new int[size * size];

        gridSize = (dim - 2 * margin);
        tileSize = gridSize / size;

        setPreferredSize(new Dimension(dimension, dimension + margin));
        setBackground(BACKGROUND_COLOR);
        setForeground(FOREGROUND_COLOR);
        setFont(new Font("SansSerif", Font.BOLD, 60));

        gameOver = true;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                
                if (gameOver) {
                    newGame();
                } else {
                    final int ex = e.getX() - margin;
                    final int ey = e.getY() - margin;

                    if (ex < 0 || ex > gridSize || ey < 0 || ey > gridSize)
                        return;

                    final int c1 = ex / tileSize;
                    final int r1 = ey / tileSize;

                    final int c2 = blankPos % size;
                    final int r2 = blankPos / size;

                    final int clickPos = r1 * size + c1;

                    int dir = 0;

                    if (c1 == c2 && Math.abs(r1 - r2) > 0)
                        dir = (r1 - r2) > 0 ? size : -size;
                    else if (r1 == r2 && Math.abs(c1 - c2) > 0)
                        dir = (c1 - c2) > 0 ? 1 : -1;

                    if (dir != 0) {
                        do {
                            final int newBlankPos = blankPos + dir;
                            tiles[blankPos] = tiles[newBlankPos];
                            blankPos = newBlankPos;
                        } while (blankPos != clickPos);

                        tiles[blankPos] = 0;
                    }

                    gameOver = isSolved();
                }

                repaint();
            }
        });

        newGame();
    }

    private void newGame() {
        do {
            reset(); 
            shuffle(); 
        } while (!isSolvable());

        gameOver = false;
    }

    private void reset() {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = (i + 1) % tiles.length;
        }

        blankPos = tiles.length - 1;
    }

    private void shuffle() {
        int n = nbTiles;

        while (n > 1) {
            final int r = RANDOM.nextInt(n--);
            final int tmp = tiles[r];
            tiles[r] = tiles[n];
            tiles[n] = tmp;
        }
    }

    private boolean isSolvable() {
        int countInversions = 0;

        for (int i = 0; i < nbTiles; i++) {
            for (int j = 0; j < i; j++) {
                if (tiles[j] > tiles[i])
                    countInversions++;
            }
        }

        return countInversions % 2 == 0;
    }

    private boolean isSolved() {
        if (tiles[tiles.length - 1] != 0) 
            return false;

        for (int i = nbTiles - 1; i >= 0; i--) {
            if (tiles[i] != i + 1)
                return false;
        }

        return true;
    }

    private void drawGrid(final Graphics2D g) {
        for (int i = 0; i < tiles.length; i++) {
            final int r = i / size;
            final int c = i % size;
            final int x = margin + c * tileSize;
            final int y = margin + r * tileSize;

            if (tiles[i] == 0) {
                if (gameOver) {
                    g.setColor(PRIMARY_COLOR);
                    g.setFont(getFont().deriveFont(Font.BOLD, 20));
                    drawCenteredString(g, "Complete", x, y);
                }

                continue;
            }

            g.setColor(getForeground());
            g.fillRoundRect(x, y, tileSize, tileSize, 25, 25);
            g.setColor(PRIMARY_COLOR);
            g.drawRoundRect(x, y, tileSize, tileSize, 25, 25);
            g.setColor(Color.WHITE);

            drawCenteredString(g, String.valueOf(tiles[i]), x, y);
        }
    }

    private void drawStartMessage(final Graphics2D g) {
        if (gameOver) {
            g.setFont(getFont().deriveFont(Font.BOLD, 20));
            g.setColor(PRIMARY_COLOR);
            final String s = "New Game";
            g.drawString(s, (getWidth() - g.getFontMetrics().stringWidth(s)) / 2, getHeight() - margin);
        }
    }

    private void drawCenteredString(final Graphics2D g, final String s, final int x, final int y) {
        final FontMetrics fm = g.getFontMetrics();
        final int asc = fm.getAscent();
        final int desc = fm.getDescent();
        g.drawString(s, x + (tileSize - fm.stringWidth(s)) / 2, y + (asc + (tileSize - (asc + desc)) / 2));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g2D);
        drawStartMessage(g2D);
    }

  
}

