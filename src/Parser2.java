import java.io.IOException;
import java.util.Arrays;

/*
 * Created by carlmccann2 on 11/04/15.
 *
 * Compiler Construction - COMP30330
 * Carl McCann  12508463
 * Part 4,5,6 of Assignment 2
 */

public class Parser2 {
    public int[][][] grammar = new int[11][3][3];
    public int[][] tokens = {{2,33},{4,0},{1,34},{0,0}};

    public int successCount = 0;
    public int backtrackCount = 0;

    public int S       = -1;
    public int Exp     = -2;
    public int Pexp    = -3;
    public int A       = -4;
    public int Term   = -5;
    public int Pterm  = -6;
    public int B      = -7;
    public int Factor    = -8;
    public int Pfactor = -9;
    public int Arglist = -10;
    public int C       = -11;

    public int endofline = 0;
    public int num       = 1;
    public int id        = 2;
    public int plus      = 3;
    public int minus     = 4;
    public int mult      = 5;
    public int div       = 6;
    public int modulus   = 7;
    public int lpar      = 8;
    public int rpar      = 9;
    public int lsqr      = 10;
    public int rsqr      = 11;
    public int comma     = 12;
    public int epsilon   = 13;



    Parser2() {

        //S
        grammar[0][0][0] = Exp;
        grammar[0][0][1] = endofline;
        //  Exp
        grammar[1][0][0] = Term;
        grammar[1][0][1] = Pexp;
        //  Exp'
        grammar[2][0][0] = A;
        grammar[2][0][1] = Exp;
        grammar[2][1][0] = epsilon;

        //  A
        grammar[3][0][0] = plus;
        grammar[3][1][0] = minus;

        //  Term
        grammar[4][0][0] = Factor;
        grammar[4][0][1] = Pterm;

        //  Term'
        grammar[5][0][0] = B;
        grammar[5][0][1] = Term;
        grammar[5][1][0] = epsilon;

        //  B
        grammar[6][0][0] = mult;
        grammar[6][1][0] = div;
        grammar[6][2][0] = modulus;

        //  Factor
        grammar[7][0][0] = num;
        grammar[7][1][0] = id;
        grammar[7][1][1] = Pfactor;
        grammar[7][2][0] = lpar;
        grammar[7][2][1] = Exp;
        grammar[7][2][2] = rpar;


        //  Factor'
        grammar[8][0][0] = lpar;
        grammar[8][0][1] = Arglist;
        grammar[8][0][2] = rpar;
        grammar[8][1][0] = lsqr;
        grammar[8][1][1] = Arglist;
        grammar[8][1][2] = rsqr;
        grammar[8][2][0] = epsilon;

        //  Arglist
        grammar[9][0][0] = comma;
        grammar[9][0][1] = Arglist;
        grammar[9][1][0] = epsilon;

        //  C
        grammar[10][0][0] = Exp;
        grammar[10][0][1] = Arglist;


            }

    public static void predictiveParser(){

    }





    public static void main(String[] args)throws IOException {
        Parser2 p = new Parser2();


    }





}



