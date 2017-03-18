/**
 * Created by ShreyashPatodia on 18/03/17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.InputStreamReader;


public class Board {

    public static void main(String []args) throws IOException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        Integer N = Integer.parseInt(buffer.readLine());

        //System.out.println(N);

        char boardArray[][] = new char[N][N];




        for(int row = 0; row < N; row++) {

            String line = buffer.readLine();
            line = line.replaceAll("\\s","");
            boardArray[row] = line.toCharArray();
        }

        /*for(int row = 0; row < N; row++) {
            for(int column = 0; column < N; column++) {
                System.out.println(boardArray[row][column]);
            }
        }*/

    }
}
