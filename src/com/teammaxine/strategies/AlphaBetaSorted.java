package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.scorers.BlockingScorer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by shreyashpatodia on 12/05/17.
 */
public class AlphaBetaSorted implements Strategy{
    private char myPlayer;
    private char otherPlayer;
    private BlockingScorer scorer;
    private int maxDepth;
    //private MontyPythonScorer montyScorer;

    public AlphaBetaSorted(char player, BlockingScorer scorer, int maxDepth) {
        this.myPlayer = player;
        this.otherPlayer = this.myPlayer == Board.CELL_HORIZONTAL?
                Board.CELL_VERTICAL: Board.CELL_HORIZONTAL;
        this.scorer = scorer;
        this.maxDepth = maxDepth;
        //montyScorer = new MontyPythonScorer();
    }

    public Move findMove(Board board, int depth) {
        Move bestMove = alphaBetaSearch(depth, board,
                Integer.MIN_VALUE, Integer.MAX_VALUE);
        return bestMove;
    }

    /**gb
     * Performs alpha beta search recursively
     * @param depth
     * @return
     */
    private Move alphaBetaSearch(int depth, Board board, double alpha, double beta) {
        ArrayList<? extends Move> legalMoves = this.sortMoves(board, this.myPlayer);
        Move bestMove = null;
        double bestVal = Integer.MIN_VALUE;
        //System.out.println("++++++++++++++++++++");
        //System.out.println(board);
        //System.out.println("     possibles     ");
        Board newBoard = new Board(board);
//        Board bestBoard = null;
        for(Move move : legalMoves) {
            newBoard.makeMove(move, myPlayer);
            //System.out.println("--------------------");
            //System.out.println("With move :" + move);
            //System.out.println("New board\n" + newBoard);
            // Check if depth - 1 should be here or not
            double val = minValue(newBoard, alpha, beta, depth - 1);
            //System.out.println("Score for this move would be:" + val);
            //bestVal = Math.max(bestVal, val);
            if(val > bestVal || (val == bestVal && bestMove == null)) {
                //System.out.println("Making this the best move");
                bestVal = val;
                //System.out.println("With move :" + move);
                //System.out.println(val);
                bestMove = move;
            }
            newBoard.undoMove(move, myPlayer);
            alpha = Math.max(alpha, bestVal);
            if(alpha >= beta) {
                return bestMove;
            }
            //System.out.println("--------------------");
        }
        //System.out.println("++++++++++++++++++++");
        return bestMove;
    }

    private double maxValue(Board board, double alpha, double beta, int depth) {

        ArrayList<? extends Move> legalMoves = this.sortMoves(board, this.myPlayer);
        if(board.horizontalWon()) {
            if(this.myPlayer == 'H')
                return Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if(board.verticalWon()) {
            if(this.myPlayer == 'V')
                return  Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if(depth == 0) {
            //System.out.println("--------------------");
            //System.out.println("Terminal state :");
            //System.out.println(board);
            double score = this.scorer.scoreBoard(board, myPlayer, (depth + 1)/2);
            //System.out.println("Score is: " + score);
            //System.out.println("--------------------");
            return score;
        }
        double bestVal = Integer.MIN_VALUE;
        // We are out of moves.
        if(legalMoves.size() == 0)
            // Maybe -1 maybe not?
            return minValue(board, alpha, beta, depth);

        for(Move move : legalMoves) {
            board.makeMove(move, myPlayer);
            bestVal = Math.max(bestVal, minValue(board, alpha, beta, depth - 1));
            alpha = Math.max(bestVal, alpha);
            board.undoMove(move, myPlayer);
            if (alpha >= beta)
                break;
        }
        return bestVal;
    }

    private double minValue(Board board, double alpha, double beta, int depth) {
        //System.out.println("Min called");
        //System.out.println(board);
        ArrayList<? extends Move> legalMoves = this.sortMoves(board, otherPlayer);
        if(board.horizontalWon()) {
            if(this.myPlayer == 'H')
                return Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if(board.verticalWon()) {
            if(this.myPlayer == 'V')
                return  Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if(depth == 0) {
            //System.out.println("--------------------");
            //System.out.println("Terminal state :");
            //System.out.println(board);
            double score = this.scorer.scoreBoard(board, otherPlayer, (depth + 1)/2);
            //System.out.println("Score is: " + score);
            //System.out.println("--------------------");
            return score;
        }

        // The other player is out of moves.
        if(legalMoves.size() == 0)
            // Maybe -1 maybe not?
            return maxValue(board, alpha, beta, depth);

        double bestVal = Integer.MAX_VALUE;
        //
        if(legalMoves.size() == 0)
            return Integer.MAX_VALUE;
        //Board newBoard = new Board(board);
        for(Move move : legalMoves) {
            board.makeMove(move, otherPlayer);
            bestVal = Math.min(bestVal, maxValue(board, alpha, beta, depth - 1));
            beta = Math.min(bestVal, beta);
            board.undoMove(move, otherPlayer);
            if(alpha >= beta)
                break;
        }
        return bestVal;
    }

    public ArrayList<Move> sortMoves(Board board, char player) {
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(player);
        ArrayList<MoveValuePair> moveValuePairs = new ArrayList<>();

        for(Move move : legalMoves) {
            board.makeMove(move, player);
            double score = evaluateBoard(board, player);
            if(player == this.myPlayer)
                moveValuePairs.add(new MoveValuePair(move, score));
            else
                moveValuePairs.add(new MoveValuePair(move, -score));
            board.undoMove(move, player);
        }

        Collections.sort(moveValuePairs, new MoveComparator());
        ArrayList<Move> orderedMoves = new ArrayList<>();

        for(MoveValuePair pair : moveValuePairs) {
            orderedMoves.add(pair.move);
        }
        return orderedMoves;
    }

    public double evaluateBoard(Board board, char player) {
        return this.scorer.scoreBoard(board, player);
    }

    public class MoveValuePair {
        Move move;
        double score;

        public MoveValuePair(Move move, Double score) {
            this.move = move;
            this.score = score;
        }

    }

    public class MoveComparator implements Comparator<MoveValuePair> {

        public int compare(MoveValuePair one, MoveValuePair two) {
            return -Double.compare(one.score, two.score);
        }
    }
}
