import java.io.IOException;
import java.util.Arrays;

/*
 * Created by carlmccann2 on 23/03/15.
 *
 * Compiler Construction - COMP30330
 * Carl McCann  12508463
 * Part 2 of Assignment 2
 */

public class Parser {
    public int[][][] grammar = new int[10][5][4];           //
    public int[][] tokens = {{2,33},{4,0},{1,34},{0,0}};

    public int successCount = 0;
    public int backtrackCount = 0;

    public int S       = -1;
    public int Exp     = -2;
    public int Pexp    = -3;
    public int Term    = -4;
    public int Pterm   = -5;
    public int Factor  = -6;
    public int Fncall  = -7;
    public int Aref    = -8;
    public int Arglist = -9;
    public int Indices = -10;

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



    Parser() {
        //S
        grammar[0][0][0] = Exp;
        grammar[0][0][1] = endofline;
        //  Exp
        grammar[1][0][0] = Term;
        grammar[1][0][1] = Pexp;
        //  Exp'
        grammar[2][0][0] = plus;
        grammar[2][0][1] = Term;
        grammar[2][0][2] = Pexp;

        grammar[2][1][0] = minus;
        grammar[2][1][1] = Term;
        grammar[2][1][2] = Pexp;

        grammar[2][2][0] = epsilon;
        //  Term
        grammar[3][0][0] = Factor;
        grammar[3][0][1] = Pterm;
        //  Term'
        grammar[4][0][0] = mult;
        grammar[4][0][1] = Factor;
        grammar[4][0][2] = Pterm;

        grammar[4][1][0] = div;
        grammar[4][1][1] = Factor;
        grammar[4][1][2] = Pterm;

        grammar[4][2][0] = modulus;
        grammar[4][2][1] = Factor;
        grammar[4][2][2] = Pterm;

        grammar[4][3][0] = epsilon;
        //  Factor
        grammar[5][0][0] = num;

        grammar[5][1][0] = id;

        grammar[5][2][0] = Fncall;

        grammar[5][3][0] = Aref;

        grammar[5][4][0] = lpar;
        grammar[5][4][1] = Exp;
        grammar[5][4][2] = rpar;
        //  Fncall
        grammar[6][0][0] = id;
        grammar[6][0][1] = lpar;
        grammar[6][0][2] = Arglist;
        grammar[6][0][3] = rpar;
        //  Aref
        grammar[7][0][0] = id;
        grammar[7][0][1] = lsqr;
        grammar[7][0][2] = Indices;
        grammar[7][0][3] = rsqr;
        //  Arglist
        grammar[8][0][0] = Exp;

        grammar[8][1][0] = Exp;
        grammar[8][1][1] = comma;
        grammar[8][1][2] = Arglist;
        //  Indices
        grammar[9][0][0] = Exp;

        grammar[9][1][0] = Exp;
        grammar[9][1][1] = comma;
        grammar[9][1][2] = Indices;
    }

    public int[] BTRecursiveDescentParser(int nonTerminal, int startPos){
//        RDP(NT, STARTPOS) =
//                loop over those productions whose LHS is NT, call the current production P:


//        {let NEXTPOS = STARTPOS
//            loop over the symbols in the RHS of P, call the current symbol SYM:
//            if SYM is a terminal then
//            if the input stream symbol at NEXTPOS is SYM
//            then increment NEXTPOS
//            else finish this iteration of the outer "productions" loop


//            RDP takes two arguments:
//            - a nonterminal grammar symbol - a position in input token stream

//            else
//            if the first component of result of RDP(SYM, NEXTPOS) is ‘SUCCESS’
//            then set NEXTPOS to the second component of the result
//            else finish this iteration of the outer "productions" loop \*
//
//            all symbols in
//            the RHS of P were successfully matched *\ return a result with 1st=’SUCCESS', 2nd=NEXTPOS}

//            \* no P was found whose RHS could be fully matched *\ return a result with 1st=’FAILURE',
//            2nd is immaterial
//

        int[][] productions = grammar[-nonTerminal-1];
        int[] reply = new int[2];
        boolean match = false;
        int nextPos = startPos;

        for (int i = 0; i < productions.length; i++) {
            productions = grammar[-nonTerminal-1];
            backtrackCount++;

            for (int j = 0; j < productions[i].length; j++) {
                System.out.println("\nCurrent Token: " + tokens[nextPos][0]);
                System.out.println(Arrays.toString(productions[i]));

                if(productions[i][j] >= 0) {
                    System.out.println("Terminal found " + productions[i][j]);

                    if (tokens[nextPos][0] == productions[i][j]) {
                        System.out.println("Terminal matches stream " + productions[i][j]);
                        nextPos++;
                        successCount++;
                        match = true;
                    }
                    else{
                        match = false;
                        break;
                    }
                }
                else{
                    System.out.println("Non Terminal Found "  + productions[i][j]);
                    reply = BTRecursiveDescentParser(productions[i][j],nextPos);
                    if(reply[0] == 1){
                        nextPos = reply[1];
                        System.out.println("SUCCESS");
                        return reply;
                    }
                }
            }
        }
        return new int[]{0};
    }

    public static void main(String[] args)throws IOException{
        Parser p = new Parser();
        System.out.println(Arrays.toString(p.BTRecursiveDescentParser(-2, 0)));
        System.out.println("Backtrack Count: " + p.backtrackCount);
        System.out.println("Success Count: " + p.successCount);
    }
}

