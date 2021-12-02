package com.example.mazegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.mazegame.model.Cell;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GameView extends View {

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Cell[][] cells;
    private Cell player, exit;
    private static final int COLS = 7, ROWS = 10;
    private static final float WALL_THICKNESS = 4;
    private float cellSize, hMargin, vMargin;
    private Paint wallPaint, playerPaint, exitPaint;
    private SecureRandom random;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        wallPaint = new Paint();
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStrokeWidth(WALL_THICKNESS);

        playerPaint = new Paint();
        playerPaint.setColor(Color.RED);

        exitPaint = new Paint();
        exitPaint.setColor(Color.BLUE);

        random = new SecureRandom();

        createMaze();
    }

    private void createMaze(){
        Stack<Cell> stack = new Stack<>();
        Cell current, next;

        cells = new Cell[COLS][ROWS];

        for (int i = 0; i < COLS; i++){
            for (int j = 0; j < ROWS; j++){
                cells[i][j] = new Cell(i, j);
            }
        }

        player = cells[0][0];
        exit = cells[COLS - 1][ROWS - 1];

        current = cells[0][0];
        current.visited = true;


        do {
            next = getNeighbour(current);
            if (next != null) {
                removeWall(current, next);
                stack.push(current);
                current = next;
                current.visited = true;
            } else {
                current = stack.pop();
            }
        } while (!stack.isEmpty());
    }


    private Cell getNeighbour(Cell current) {
        List<Cell> neighbours = new ArrayList<>();

        //left
        if(current.col > 0) {
            if (!cells[current.col - 1][current.row].visited) {
                neighbours.add(cells[current.col - 1][current.row]);
            }
        }

        //right
        if(current.col < COLS - 1) {
            if (!cells[current.col + 1][current.row].visited) {
                neighbours.add(cells[current.col + 1][current.row]);
            }
        }

        //top
        if(current.row > 0) {
            if (!cells[current.col][current.row - 1].visited) {
                neighbours.add(cells[current.col][current.row - 1]);
            }
        }

        //bottom
        if(current.row < ROWS - 1) {
            if (!cells[current.col][current.row + 1].visited) {
                neighbours.add(cells[current.col][current.row + 1]);
            }
        }

        if(neighbours.isEmpty()){
            return null;
        } else {
            return neighbours.get(random.nextInt(neighbours.size()));
        }
    }

    private void removeWall(Cell current, Cell next) {
        if(current.col == next.col){
            if(current.row == next.row + 1){
                current.topWall = false;
                next.bottomWall = false;
            } else {
                current.bottomWall = false;
                next.topWall = false;
            }
        } else {
            if(current.col == next.col + 1){
                current.leftWall = false;
                next.rightWall = false;
            } else {
                current.rightWall = false;
                next.leftWall = false;
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);

        int width = getWidth();
        int height = getHeight();

        if(width/height < COLS / ROWS){
            cellSize = width / (COLS + 1);
        } else {
            cellSize = height / (ROWS + 1);
        }

        hMargin = (width - COLS * cellSize) / 2;
        vMargin = (height - ROWS * cellSize) / 2;

        canvas.translate(hMargin, vMargin);

        for (int i = 0; i < COLS; i++){
            for (int j = 0; j < ROWS; j++){
                if(cells[i][j].topWall){
                    canvas.drawLine(
                            i * cellSize,
                            j * cellSize,
                            (i + 1) * cellSize,
                            j * cellSize,
                            wallPaint
                    );
                }

                if(cells[i][j].leftWall){
                    canvas.drawLine(
                            i * cellSize,
                            j * cellSize,
                            i * cellSize,
                            (j + 1) * cellSize,
                            wallPaint
                    );
                }

                if(cells[i][j].bottomWall){
                    canvas.drawLine(
                            i * cellSize,
                            (j + 1) * cellSize,
                            (i + 1)  * cellSize,
                            (j + 1) * cellSize,
                            wallPaint
                    );
                }

                if(cells[i][j].rightWall){
                    canvas.drawLine(
                            (i + 1) * cellSize,
                            j * cellSize,
                            (i + 1) * cellSize,
                            (j + 1) * cellSize,
                            wallPaint
                    );
                }
            }
        }

        float margin = cellSize / 10;

        canvas.drawRect(
                player.col * cellSize + margin,
                player.row * cellSize + margin,
                (player.col + 1) * cellSize - margin,
                (player.row + 1) * cellSize - margin,
                playerPaint
        );

        canvas.drawRect(
                exit.col * cellSize + margin,
                exit.row * cellSize + margin,
                (exit.col + 1) * cellSize - margin,
                (exit.row + 1) * cellSize - margin,
                exitPaint
        );
    }

    private void movePlayer(Direction direction){
        switch (direction){
            case UP:
                if(!player.topWall) {
                    player = cells[player.col][player.row - 1];
                }
                break;
            case DOWN:
                if(!player.bottomWall) {
                    player = cells[player.col][player.row + 1];
                }
                break;
            case LEFT:
                if(!player.leftWall) {
                    player = cells[player.col - 1][player.row];
                }
                break;
            case RIGHT:
                if(!player.rightWall) {
                    player = cells[player.col + 1][player.row];
                }
                break;
        }

        checkExit();
        invalidate();
    }

    private void checkExit(){
        if(player == exit){
            createMaze();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            return true;
        }

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            float x = event.getX();
            float y = event.getY();

            float playerCenterX = hMargin + (player.col + 0.5f) * cellSize;
            float playerCenterY = vMargin + (player.row + 0.5f) * cellSize;

            float dx = x - playerCenterX;
            float dy = y - playerCenterY;

            float absDx = Math.abs(dx);
            float absDy = Math.abs(dy);

            if(absDx > cellSize || absDy > cellSize){
                if (absDx > absDy){
                    //move in x-axis
                    if(dx > 0){
                        movePlayer(Direction.RIGHT);
                    } else {
                        movePlayer(Direction.LEFT);

                    }
                } else {
                    //move in y-axis
                    if(dy > 0){
                        movePlayer(Direction.DOWN);

                    } else {
                        movePlayer(Direction.UP);
                    }
                }
            }
        }

        return true;
    }
}
