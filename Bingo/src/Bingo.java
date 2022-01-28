import java.util.Random;
import java.util.Scanner;

public class Bingo {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num = 0;

		System.out.println(" /$$$$$$$ /$$$$$$/$$   /$$ /$$$$$$  /$$$$$$       \r\n"
				+ "| $$__  $|_  $$_| $$$ | $$/$$__  $$/$$__  $$      \r\n"
				+ "| $$  \\ $$ | $$ | $$$$| $| $$  \\__| $$  \\ $$      \r\n"
				+ "| $$$$$$$  | $$ | $$ $$ $| $$ /$$$| $$  | $$      \r\n"
				+ "| $$__  $$ | $$ | $$  $$$| $$|_  $| $$  | $$      \r\n"
				+ "| $$  \\ $$ | $$ | $$\\  $$| $$  \\ $| $$  | $$      \r\n"
				+ "| $$$$$$$//$$$$$| $$ \\  $|  $$$$$$|  $$$$$$/      \r\n"
				+ "|_______/|______|__/  \\__/\\______/ \\______/   \n    ");

		int elegir = 0;
		int dinero = 1000;
		String siOno = "none";
		boolean repetir = true;
		String jugarrep = "none";
		
		do {
			try {
				System.out.println("Elige el modo de juego: \n[1] Jugador VS IA \n[2] jugador VS jugador");
				elegir = Integer.parseInt(sc.next());
			} catch (Exception e) {
				System.out.println("Caracter inv�lido, vuelve a elegir \n");
			}
		} while (elegir != 1 && elegir != 2);

		sc.nextLine();
		
		if (elegir == 1) {
			String carton[][][] = new String[2][3][5];

			while(repetir) {
			do {
				try {
					System.out.println("Precio del cart�n: 10� \nSaldo actual: "+dinero);
					System.out.println("Quieres comprar un cart�n? (si/no)");
					siOno = sc.nextLine().toLowerCase();
				} catch (Exception e) {
					System.out.println("Caracter inv�lido, vuelve a elegir \n");
				}
			} while (!siOno.equals("si") && !siOno.equals("no"));
			
			if(siOno.equals("si")) {
				dinero = dinero - 10;
				System.out.println("Perfecto, eres el JUGADOR 1");
				try {
					Thread.sleep(2*2000);
				} catch (Exception e) {
					System.out.println(e);
				}
				
				rellenar(carton);
				bingo(carton, true, dinero);
				
			}else if(siOno.equals("no")) {
				System.out.println("No podemos dejaros jugar, fuera de aqu�!");
				break;
			}
				do {
					try {
						System.out.println("Quieres volver a jugar? (si/no)");
						jugarrep = sc.nextLine().toLowerCase();
					} catch (Exception e) {
						System.out.println("Caracter inv�lido, vuelve a elegir \n");
					}
				} while (!siOno.equals("si") && !siOno.equals("no"));
				
				if(jugarrep.equals("si")) {
					repetir = true;
				}else {
					repetir = false;
				}
				
				
			}
			System.out.println("Hasta pronto!!!");
			
			
		}else {
			num = cuantos();
			String carton[][][] = new String[num][3][5];

						
			while(repetir) {
			do {
				try {
					System.out.println("Precio del cart�n: 10� \nSaldo actual: "+dinero);
					System.out.println("Quieres comprar cartones (si/no)");
					siOno = sc.nextLine().toLowerCase();
				} catch (Exception e) {
					System.out.println("Caracter inv�lido, vuelve a elegir \n");
				}
			} while (!siOno.equals("si") && !siOno.equals("no"));
			
			if(siOno.equals("si")) {
				dinero = dinero - (10*carton.length);

				rellenar(carton);
				bingo(carton, false, dinero);
				
			}else if(siOno.equals("no")) {
				System.out.println("No podemos dejaros jugar, fuera de aqu�!");
				break;
				}
				
				do {
					try {
						System.out.println("Quereis volver a jugar? (si/no)");
						jugarrep = sc.nextLine().toLowerCase();
					} catch (Exception e) {
						System.out.println("Caracter inv�lido, vuelve a elegir \n");
					}
				} while (!siOno.equals("si") && !siOno.equals("no"));
				
				if(jugarrep.equals("si")) {
					repetir = true;
				}else {
					repetir = false;
				}
			
			}
			System.out.println("Hasta pronto");
		}
		
	}

	public static int cuantos() {
		Scanner teclado = new Scanner(System.in);

		int numero = 0;
		do {
			try {
				System.out.println("Inserta el numero de Jugadores: (max 6)");
				numero = Integer.parseInt(teclado.next());
			} catch (Exception e) {

			}
		} while (numero < 1 || numero > 6);

		return numero;

	}

	public static String[][][] rellenar(String mapa[][][]) {
		Random aleatorio = new Random();
		String temporal = "none";

		for (int i = 0; i < mapa.length; i++) {
			for (int z = 0; z < mapa[i].length; z++) {
				for (int j = 0; j < mapa[i][z].length; j++) {

					do {
						temporal = String.valueOf(aleatorio.nextInt(90) + 1);
					} while (comprobarRepString(mapa, temporal));

					mapa[i][z][j] = temporal;
				}
			}
		}
		return mapa;
	}

	public static void mostrar(String mapa[][][]) {

		for (int i = 0; i < mapa.length; i++) {
			System.out.println();
			System.out.print("JUGADOR " + (i + 1));
			System.out.println();
			for (int z = 0; z < mapa[i].length; z++) {
				System.out.println();
				for (int k = 0; k < mapa[i][z].length; k++) {
					System.out.print(mapa[i][z][k] + " ");

				}
			}
			System.out.println();
		}
	}

	public static void bingo(String mapa[][][], boolean ia, int dinero) {
		Random aleatorio = new Random();

		int temp = 0;
		int vector[] = new int[90];
		boolean repetido = true;
		boolean fin = false;
		boolean comprobar = false;
		boolean encontrada = false;
		int ganador = 1;
		
		System.out.println();
		mostrar(mapa);

		for (int i = 0; i < vector.length; i++) {
			repetido = true;

			while (repetido == true) {
				temp = aleatorio.nextInt(90) + 1;
				repetido = false;
				for (int z = 0; z < vector.length; z++) {
					if (temp == vector[z]) {
						repetido = true;
					}
				}
				if (repetido == false) {
					vector[i] = temp;
					for (int x = 0; x < mapa.length; x++) {
						comprobar = linea(mapa[x]);

						if (comprobar == true && encontrada == false) {
							System.out.println("LINEA DEL JUGADOR " + (x + 1));
							encontrada = true;
							if(ganador == (x+1)) {
								dinero = dinero + 20;
							}
							try {
								Thread.sleep(2*2000);
							} catch (Exception e) {
								System.out.println(e);
							}
						}

						if (lleno(mapa[x]) == true) {
							fin = true;
							System.out.println("BINGO, EL JUGADOR " + (x + 1) + " HA GANADO");
							if(ganador == (x+1)) {
								dinero = dinero + 30;
							}
							i = vector.length;
						}
					}
					if (fin == false) {
						try {
							Thread.sleep(1 * 500);
						} catch (Exception e) {
							System.out.println(e);
						}
						System.out.print(vector[i] + " ");

						for (int z = 0; z < mapa.length; z++) {

							for (int x = 0; x < mapa[z].length; x++) {
								for (int k = 0; k < mapa[z][x].length; k++) {
									if (Integer.parseInt(mapa[z][x][k]) == temp) {
										mapa[z][x][k] = "-1";
										System.out.println("\n");
										mostrar(mapa);
										System.out.println("\nSe ha encontrado el numero " + temp
												+ ", seguimos para BINGO \n");
										try {
											Thread.sleep(1*500);
										} catch (Exception e) {
											System.out.println(e);
										}
									}
								}
							}
						}

					}
				}
			}
		}
	}

	public static boolean lleno(String vector[][]) {
		boolean lleno = false;
		int contador = 0;

		for (int i = 0; i < vector.length; i++) {
			for (int z = 0; z < vector[i].length; z++) {
				if (vector[i][z].equals("-1")) {
					contador++;
				}
			}
		}
		if (contador == 15) {
			lleno = true;
		} else {
			lleno = false;
		}
		return lleno;
	}

	public static boolean linea(String[][] carton) {

		int contador = 0;

		for (int z = 0; z < carton.length; z++) {
			contador = 0;
			for (int j = 0; j < carton[z].length; j++) {
				if (carton[z][j].equals("-1")) {
					contador++;
				}
			}
			if (contador == 5) {
				return true;
			}
		}
		return false;

	}

	public static boolean comprobarRepString(String vector[][][], String temporal) {
		for (int i = 0; i < vector.length; i++) {
			for (int z = 0; z < vector[i].length; z++) {
				for (int j = 0; j < vector[i][z].length; j++) {
					if (vector[i][z][j] != null && vector[i][z][j].equals(temporal)) {
						return true;
					}

				}
			}
		}
		return false;
	}
}
