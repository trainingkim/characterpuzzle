import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel {

    private int[][] _cells = {
            {0, 4, 5},
            {2, 6, 1},
            {7, 3, 8}
    };

    private int[][] _precells = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}
    };
    private Image _image; 
    private Toolkit toolkit;
    private int cellSize;
    private int blankCell;
    private int blankCellCol; 
    private int blankCellRow; 
    private int counter;
    private boolean bingoState;
    ImagePanel() {
        toolkit = getToolkit();
        _image = toolkit.getImage("image1.png");
        cellSize = 200;
        blankCellCol = 2;
        blankCellRow = 2; 
        blankCell = _cells[blankCellRow][blankCellCol];
        counter = 0;
        bingoState = checkBingoState();
    }

    private int getCellSize() { return cellSize; }
    private boolean getBingoState() { return bingoState; }
    private boolean checkBingoState() {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                if (_cells[row][col] != _precells[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNeighborToBlankCell(int row, int col) {
        if (col + 1 == blankCellCol && row == blankCellRow) {
            return true;
        }
        if (col - 1 == blankCellCol && row == blankCellRow) {
            return true;
        }
        if (col == blankCellCol && row + 1 == blankCellRow) {
            return true;
        }
        if (col == blankCellCol && row - 1 == blankCellRow) {
            return true;
        }
        return false;
    }
    private void moveToBlank(int row, int col) {
        int oldBlankCell = _cells[blankCellRow][blankCellCol];
        int newBlankCell = _cells[row][col];
        _cells[blankCellRow][blankCellCol] = newBlankCell;
        _cells[row][col] = oldBlankCell;
        blankCellRow = row;
        blankCellCol = col;
        bingoState = checkBingoState();
    }
    private void increaseCounter() {
        counter++;
    }
    public boolean processInput(int x, int y) {
        if (!getBingoState()) {
            int cellSize = getCellSize();
            int col = x / cellSize;
            int row = y / cellSize;
            if (isNeighborToBlankCell(row, col)) {
                moveToBlank(row, col);
                repaint();
                increaseCounter();
                return true;
            }
        }
        return false;
    }
    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                int x = col * cellSize;
                int y = row * cellSize;
                if (_cells[row][col] != blankCell || bingoState) {
                    int imageRow = _cells[row][col] / 3;
                    int imageCol = _cells[row][col] % 3;
                    int ix1 = imageCol * _image.getWidth(this) / 3;
                    int iy1 = imageRow * _image.getHeight(this) / 3;
                    int ix2 = ix1 + _image.getWidth(this) / 3;
                    int iy2 = iy1 + _image.getHeight(this) / 3;
                    g.drawImage(_image, x, y, x + cellSize, y + cellSize, ix1, iy1, ix2, iy2, this);
                }
            }
        }
        {
            g.setFont(new Font("TimesRoman", Font.BOLD, 70));
            g.setColor(Color.MAGENTA);
            g.drawString("점수", 600 + 20, 100);
            String counterString = String.format("%d", counter);
            g.drawString(counterString, 600 + 20, 200);
        }

        if (bingoState) {
            g.setFont(new Font("TimesRoman", Font.BOLD, 70));
            g.setColor(Color.GREEN);
            g.drawString("완성", 600 + 20, 300);
        }
    }
}
