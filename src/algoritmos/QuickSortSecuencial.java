package algoritmos;

import java.util.Random;

public class QuickSortSecuencial {

	// Método para generar un vector de números aleatorios
	public static int[] generarVectorAleatorio(int tam) {
		int[] vec = new int[tam]; // Crea un vector de tamaño 'tam'
		Random rand = new Random(); // Instancia un objeto Random para generar números aleatorios
		for (int i = 0; i < tam; i++) {
			vec[i] = rand.nextInt(100); // Genera números aleatorios entre 0 y 99 y los asigna al vector
		}
		return vec; // Retorna el vector generado
	}

	// Método para iniciar la ordenación rápida
	public static void ordenacionRapida(int vec[]) {
		final int tam = vec.length; // Obtiene el tamaño del vector
		quickSort(vec, 0, tam - 1); // Llama al método quickSort para ordenar el vector completo
	}

	// Método quickSort que realiza la ordenación recursiva
	public static void quickSort(int vec[], int inicio, int fin) {
		if (inicio >= fin) // Condición de parada: si el subarreglo tiene uno o ningún elemento
			return;
		int pivote = vec[inicio]; // Selecciona el pivote como el primer elemento del subarreglo
		int elemIzq = inicio + 1; // Inicializa el índice del elemento izquierdo
		int elemDer = fin; // Inicializa el índice del elemento derecho
		while (elemIzq <= elemDer) { // Mientras los índices no se crucen
			while (elemIzq <= fin && vec[elemIzq] < pivote) { // Encuentra un elemento mayor o igual al pivote
				elemIzq++;
			}
			while (elemDer > inicio && vec[elemDer] >= pivote) { // Encuentra un elemento menor al pivote
				elemDer--;
			}
			if (elemIzq < elemDer) { // Si los índices no se han cruzado, intercambia los elementos
				int temp = vec[elemIzq];
				vec[elemIzq] = vec[elemDer];
				vec[elemDer] = temp;
			}
		}

		if (elemDer > inicio) { // Si el índice derecho es mayor que el inicio
			int temp = vec[inicio]; // Intercambia el pivote con el elemento en elemDer
			vec[inicio] = vec[elemDer];
			vec[elemDer] = temp;
		}
		quickSort(vec, inicio, elemDer - 1); // Ordena el subarreglo izquierdo
		quickSort(vec, elemDer + 1, fin); // Ordena el subarreglo derecho
	}

}
