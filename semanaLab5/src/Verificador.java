
import java.util.Stack;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
*
* @author Joelson Morais - 55175
*
 */
public class Verificador{
/**Método Verificar as condições de simbolos para adiconarmos a pilha e retiramos e vermos qual condição se encaixa
* 
* @since 3/04/2020
* @version 1.0
* @author Joelson Morais - 55175
* @param escreve - objecto da class PrintWriter que escreve no arquivo 
* @param clouse - ArrayList que armazena todos os simbolos de fecho
* @param open -  ArrayList que armazena todos simbolos de abertura 
* @param line - String que lê a linha do arquivo
* @throws IllegalArgumentException if linha==null.
* @throws if(st==null)
*/
public static void veri(PrintWriter escreve,ArrayList<String> clouse, ArrayList<String> open,String line) throws FileNotFoundException{
	Stack<String> st = new Stack<String>();
	boolean verifica = true;
	String[] linha = line.split(" ");
	int i = 0;
	while(i < linha.length && verifica) { 
		if(open.contains(linha[i])) { 
			st.push(linha[i]);
		}
		
		if(clouse.contains(linha[i])) { 
			if(st.isEmpty()) {
			verifica = false;
			escreve.println(line + " ==> encontrei " + linha[i] + " extemporâneo"); 
		}else {
			String p = st.pop();
			if(open.indexOf(p) != clouse.indexOf(linha[i])) { 
				verifica = false;
				escreve.println(line + " ==> esperava " + clouse.get(open.indexOf(p)) + " encontrei " + linha[i]);
			} 
		
		} 
		}
		
		i++; 
	} 
	if(verifica){
		escreve.println(line + " ==> ok");
	}
	
	
}

static String[] sr;
public static void main(String[] args) throws FileNotFoundException {
	ArrayList<String> fe = new ArrayList<String>();
	ArrayList<String> ab = new ArrayList<String>();
	ArrayList<ParDeSimbolos> symbol = new ArrayList<ParDeSimbolos>();
	PrintWriter pw = new PrintWriter(new File(args[2]));
	
	Scanner sc = new Scanner (new File(args[0]));
	
	while(sc.hasNextLine()) {
		String line = sc.nextLine();
		sr = line.split(" ");
		ParDeSimbolos sy = new ParDeSimbolos(sr[0],sr[1]);
		ab.add(sy.getAberto());
		fe.add(sy.getFechado());
	}
	
	sc = new Scanner(new File(args[1]));
	while(sc.hasNextLine()) {
		String linha = sc.nextLine();
		sr = linha.split(" ");
		veri(pw, fe, ab,linha);
	}
	sc.close();
	pw.close();
}
}