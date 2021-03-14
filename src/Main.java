import java.util.ArrayList;

public class Main { // AFD = (Q, Σ, δ, q0, F)
	/*
	 //	L1 = {w in Σ* | w é binário e termina em '01'}
	 * L2 = {w ∈ Σ* | w é um binário e a MT faz um incremento dele. Exemplo 5+1=6, tal que em
binário fica 101 + 1 = 110} - Lógica de colocar a fita até o final do número, depois trocar
	 * L3 = {w ∈ Σ* | w é um binário múltiplo de 5 e a MT diz se aceita (caso verdade) ou rejeita
(caso não seja múltiplo de 5) }
	 */
	public static void main(String[] args) {
		ArrayList<String> randomBinaryNumbers = generateBinaryNumbers(10);

		for(String binary: randomBinaryNumbers) {
			System.out.println("==================================");
			System.out.println("Número utilizado: [" + binary + "]");
			System.out.print("L1: ");
			L1(binary);

//			System.out.println("L2");
//			L2(binary);
//
//			System.out.println("L3");
//			L3(binary);

//			System.out.print("L4: ");
//			L4(binary);
//			System.out.println("============================");
		}

//		L4("101 ");

	}

	public static void L1(String str) {
		//	L1 = {w in Σ* | w é binário e termina em '01'}
		State q0 = new State("q0");
		State q1 = new State("q1");
		State q2 = new State("q2");

		q2.setFinal();

		q0.addTransition(q0, '1');
		q0.addTransition(q1, '0');
		q1.addTransition(q2, '1');
		q1.addTransition(q1, '0');
		q2.addTransition(q1, '0');
		q2.addTransition(q0, '1');

		AFD afd = new AFD(q0, str, false);
		verify(afd.run());
	}

	public static void L2(String str) {
		// Precisa da lógica de ida e volta
		// Escreve na fita, depois vai verificando se for 1 ou 0 e criar um estado que 'aconteceu' a subtração

	}

	public static void L3(String str) {
		// Precisa da lógica de ida e volta
		// Pode fazer uma lógica de adicionar em um AFD ou nem?
		// Pode ir escrevendo o valor na fita e depois ir subtraindo de 5 enquanto tiver 3 digitos na fita e for maior ou igual a '101'
	}

	public static void L4(String str) {
		// Precisa da lógica da fita
		// Vai escrevendo na fita o valor lido da esquerda pra direita e depois vai escrevendo o valor da fita enquanto vai removendo
		State qRead = new State("qRead");
		State qWrite = new State("qWrite");
		qWrite.setFinal();

		arroba(qRead, qRead, "01");
		qRead.addTransition(qWrite, ' ');
		arroba(qWrite, qWrite, "01");

		AFD afd = new AFD(qRead, str, false);
		verify(afd.run());
	}

//	public static void L3() {
//		State q0 = new State("q0");
//		State q1 = new State("q1");
//		State q2 = new State("q2");
//		State q3 = new State("q3");
//		State q4 = new State("q4");
//		State q5 = new State("q5");
//		State qn = new State("qn");
//		State qfor = new State("qfor");
//		State qfloat = new State("qfloat");
//		qfor.setFinal();
//		qfloat.setFinal();
//
//		q0.addTransition(q1, 'f');
//		arroba(q0, qn, "loatr");
//
//		q1.addTransition(q2, 'l');
//		q1.addTransition(q5, 'o');
//		arroba(q1, qn, "fatr");
//
//		q2.addTransition(q3, 'o');
//		arroba(q2, qn, "flatr");
//
//		q3.addTransition(q4, 'a');
//		arroba(q3, qn, "flotr");
//
//		q4.addTransition(qfloat, 't');
//		arroba(q4, qn, "floar");
//
//		q5.addTransition(qfor, 'r');
//		arroba(q5, qn, "float");
//
//		arroba(qfor, qn, "floatr");
//		arroba(qfloat, qn, "floatr");
//		arroba(qn, qn, "floatr");
//
//		String w = "fora";
//		AFD afd = new AFD(q0, w);
//		verify(afd.run(), w);
//	}

	public static void verify(boolean b) {
//		if(b) {
//			System.out.println("Reconheceu: " + w);
//			return;
//		}
//		System.out.println("Nao reconheceu: " + w);

		System.out.println(b ? "Reconheceu" : "Não reconheceu" + "\n");

	}

	public static int convertBinaryStringToDecimal(String str) {
		int count = 0;

		for(int i = 0; i < str.length(); i++) {
			count += str.charAt(str.length() - i - 1) == '1' ? Math.pow(2, i) : 0;
		}

		return count;
	}

	public static ArrayList<String> generateBinaryNumbers(int amount) {
		ArrayList<String> generatedBinaryNumbers = new ArrayList<>();

		for(int i = 0; i < amount; i++) {
			int tamanho = Math.max(3, (int)Math.round((Math.random() * 15)));

			String num = "";
			for(int j = 0; j < tamanho; j++) {
				boolean isPar = ((int)(Math.random() * 10)) % 2 == 0;
				num += isPar ? "1" : "0";
			}
			generatedBinaryNumbers.add(num);
		}

		return generatedBinaryNumbers;
	}

	public static void arroba(State q, State qn, String w) {
		for(char c : w.toCharArray())
			q.addTransition(qn, c);
	}
}