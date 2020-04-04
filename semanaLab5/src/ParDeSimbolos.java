import java.util.Stack;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


/**
 *
 * @author Joelson Morais - 55175
 */
public class ParDeSimbolos{
    
    String[] simbolos = new String[2];
    
    public ParDeSimbolos(String ab, String fe) {
        simbolos[0] = ab;
        simbolos[1] = fe;
    }

    public String getAberto() {
        return simbolos[0];
    }

    public String getFechado() {
        return simbolos[1];
    }
}
