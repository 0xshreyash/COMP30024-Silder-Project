package com.teammaxine.strategies;

import aiproj.slider.Move;
import com.teammaxine.board.elements.Board;
import com.teammaxine.board.elements.CompressedBoard;
import com.teammaxine.board.helpers.BoardCompressor;
import com.teammaxine.board.helpers.LRUCache;
import com.teammaxine.board.helpers.State;
import com.teammaxine.board.scorers.BlockingScorer;

import java.util.*;

/**
 * Created by shreyashpatodia on 12/05/17.
 */
public class AlphaBetaCache {
    private char myPlayer;
    private char otherPlayer;
    private BlockingScorer scorer;
    private int maxDepth;
    private int nodes;
    private LRUCache<CompressedBoard, State> cache;

    private BoardCompressor compressor;

    public AlphaBetaCache(char player, BlockingScorer scorer, int maxDepth, int cacheCapacity) {
        this.myPlayer = player;
        this.otherPlayer = this.myPlayer == Board.CELL_HORIZONTAL ?
                Board.CELL_VERTICAL : Board.CELL_HORIZONTAL;
        this.scorer = scorer;
        this.maxDepth = maxDepth;
        this.compressor = new BoardCompressor();
        this.cache = new LRUCache<>(cacheCapacity);
    }

    public Move findMove(Board board, int depth) {
        Move bestMove = alphaBetaSearch(depth, board,
                Integer.MIN_VALUE, Integer.MAX_VALUE);
        return bestMove;
    }

    /**
     * Performs alpha beta search recursively
     * @param depth
     * @return
     */
    private Move alphaBetaSearch(int depth, Board board, double alpha, double beta) {
        Move bestMove = null;
        double bestVal = Integer.MIN_VALUE;

//       Board bestBoard = null;
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(myPlayer);
        for (Move move : legalMoves) {
            board.makeMove(move, myPlayer);
            State s;
            double val;
            if((s = cache.get(compressor.compress(board))) != null && s.getDepth() >= depth) {
                double lb, ub, score;
                if(alpha < (lb = s.getLowerBound())) {
                    alpha = lb;
                }
                else if(beta > (ub = s.getUpperBound())) {
                    beta = ub;
                }
                if(alpha > (score = s.getScore()) && beta <= score) {
                    val = score;
                    if(val > bestVal) {
                        bestVal = val;
                        bestMove = move;
                    }
                }
                if(alpha >= beta)
                    return bestMove;
            }
            nodes++;
            //System.out.println("--------------------");
            //System.out.println("With move :" + move);
            //System.out.println("New board\n" + newBoard);
            // Check if depth - 1 should be here or not
            val = minValue(board, alpha, beta, depth - 1);
            //System.out.println("Score for this move would be:" + val);
            //bestVal = Math.max(bestVal, val);
            if (val > bestVal) {
                //System.out.println("Making this the best move");
                bestVal = val;
                //System.out.println("With move :" + move);
                //System.out.println(val);
                bestMove = move;
            }
            CompressedBoard compressedBoard = compressor.compress(board);
            if(cache.get(compressedBoard) == null && depth <= 4) {
                cache.set(compressedBoard, new State(alpha, beta, depth, val));
            }
            else if((s = cache.get(compressedBoard)) != null && s.getDepth() < depth && (depth) <= 4) {
                cache.set(compressedBoard, new State(alpha, beta, depth, val));
            }
            //cache.add(compressor.compress(b))
            board.undoMove(move, myPlayer);
            alpha = Math.max(alpha, bestVal);
            if (alpha >= beta) {
                return bestMove;
            }
            //System.out.println("--------------------");
        }
        System.out.println("Nodes visited " + nodes);
        return bestMove;
    }

    private double maxValue(Board board, double alpha, double beta, int depth) {

        if (board.horizontalWon()) {
            if (this.myPlayer == 'H')
                return Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if (board.verticalWon()) {
            if (this.myPlayer == 'V')
                return Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if (depth == 0) {
            //System.out.println("--------------------");
            //System.out.println("Terminal state :");
            //System.out.println(board);
            double score = this.scorer.scoreBoard(board, myPlayer, (depth + 1) / 2);
            //System.out.println("Score is: " + score);
            //System.out.println("--------------------");
            return score;
        }

        ArrayList<? extends Move> legalMoves = board.getLegalMoves(myPlayer);


        // We are out of moves.
        if (legalMoves.size() == 0)
            // Maybe -1 maybe not?
            return minValue(board, alpha, beta, depth);
        State s;
        double bestVal = Integer.MIN_VALUE;
        for (Move move : legalMoves) {
            double val;
            if((s = cache.get(compressor.compress(board))) != null && s.getDepth() >= depth) {
                double lb, ub, score;
                if(alpha < (lb = s.getLowerBound())) {
                    alpha = lb;
                }
                else if(beta > (ub = s.getUpperBound())) {
                    beta = ub;
                }
                else if(alpha > (score = s.getScore()) && beta <= score) {
                    val = score;
                    if(val > bestVal) {
                        bestVal = val;
                    }
                }
                if(alpha >= beta)
                    break;
            }
            nodes++;
            board.makeMove(move, myPlayer);
            val = minValue(board, alpha, beta, depth - 1);
            bestVal = Math.max(bestVal, val);
            alpha = Math.max(bestVal, alpha);
            CompressedBoard compressedBoard = compressor.compress(board);
            if(cache.get(compressedBoard) == null && depth <= 4) {
                cache.set(compressedBoard, new State(alpha, beta, depth, val));
            }
            else if((s = cache.get(compressedBoard)) != null && s.getDepth() < depth && (depth) <= 4) {
                cache.set(compressedBoard, new State(alpha, beta, depth, val));
            }
            board.undoMove(move, myPlayer);
            if (alpha >= beta)
                break;
        }
        return bestVal;
    }

    private double minValue(Board board, double alpha, double beta, int depth) {
        //System.out.println("Min called");
        //System.out.println(board);

        if (board.horizontalWon()) {
            if (this.myPlayer == Board.CELL_HORIZONTAL)
                return Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if (board.verticalWon()) {
            if (this.myPlayer == Board.CELL_VERTICAL)
                return Integer.MAX_VALUE - maxDepth + depth;
            else
                return Integer.MIN_VALUE + maxDepth - depth;
        }
        if (depth == 0) {
            //System.out.println("--------------------");
            //System.out.println("Terminal state :");
            //System.out.println(board);
            double score = this.scorer.scoreBoard(board, otherPlayer, (depth + 1) / 2);
            //System.out.println("Score is: " + score);
            //System.out.println("--------------------");
            return score;
        }
        ArrayList<? extends Move> legalMoves = board.getLegalMoves(otherPlayer);
        // The other player is out of moves.
        if (legalMoves.size() == 0)
            // Maybe -1 maybe not?
            return maxValue(board, alpha, beta, depth);

        double bestVal = Integer.MAX_VALUE;
        //
        if (legalMoves.size() == 0)
            return Integer.MAX_VALUE;
        //Board newBoard = new Board(board);
        for (Move move : legalMoves) {
            State s;
            double val;
            if((s = cache.get(compressor.compress(board))) != null && s.getDepth() >= depth) {
                //System.out.println("Inside the cache :" + nodes);
                double lb, ub, score;
                if(alpha < (lb = s.getLowerBound())) {
                    alpha = lb;
                }
                else if(beta > (ub = s.getUpperBound())) {
                    beta = ub;
                }
                else if(alpha > (score = s.getScore()) && beta <= score) {
                    val = score;
                    if(val > bestVal) {
                        bestVal = val;
                    }
                }
                if(alpha >= beta)
                    return bestVal;
            }
            nodes++;
            board.makeMove(move, otherPlayer);
            val = maxValue(board, alpha, beta, depth - 1);
            bestVal = Math.min(bestVal, val);
            beta = Math.min(bestVal, beta);
            CompressedBoard compressedBoard = compressor.compress(board);
            if(cache.get(compressedBoard) == null && (depth) <= 4) {
                cache.set(compressedBoard, new State(alpha, beta, depth, val));
            }
            else if((s = cache.get(compressedBoard)) != null && s.getDepth() < depth && (depth) <= 4) {
                cache.set(compressedBoard, new State(alpha, beta, depth, val));
            }
            board.undoMove(move, otherPlayer);
            if (alpha >= beta)
                break;
        }

        return bestVal;
    }
}

