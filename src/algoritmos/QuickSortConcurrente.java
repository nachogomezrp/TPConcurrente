package algoritmos;

import java.util.Random;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class QuickSortConcurrente {

	private static final int UMBRAL = 10000; // Umbral para cambiar a ordenación secuencial

	// Método para generar un vector de números aleatorios
	public static int[] generarVectorAleatorio(int tam) {
		int[] vec = new int[tam]; // Crea un vector de tamaño definido previamente en tam
		Random rand = new Random();
		for (int i = 0; i < tam; i++) {
			vec[i] = rand.nextInt(100); // Genera numeros aleatorios entre 0 y 99
		}
		return vec; // Devuelve el vector generado
	}

	public static void ordenacionRapida(int vec[]) {
		ForkJoinPool pool = new ForkJoinPool(); // Crea un pool de hilos
		pool.invoke(new QuickSortTask(vec, 0, vec.length - 1)); // Invoca la tarea de ordenación rápida
	}

	public static class QuickSortTask extends RecursiveAction {
		private int[] vec; // Vector a ordenar
		private int inicio, fin; // Índices de inicio y fin

		public QuickSortTask(int[] vec, int inicio, int fin) {
			this.vec = vec; // Asigna el vector
			this.inicio = inicio; // Asigna el índice de inicio
			this.fin = fin; // Asigna el índice de fin
		}

		@Override
		protected void compute() {
			if (fin - inicio < UMBRAL) { // Si el tamaño del subvector es menor al umbral, ordena secuencialmente
				QuickSortSecuencial.quickSort(vec, inicio, fin);
				return; // Termina la ejecución de esta tarea
			}

			int pivote = vec[inicio]; // Define el pivote como el primer elemento del subvector
			int elemIzq = inicio + 1; // Elemento izquierdo comienza justo después del pivote
			int elemDer = fin; // Elemento derecho comienza al final del subvector
			while (elemIzq <= elemDer) { // Mientras no se crucen los índices
				while (elemIzq <= fin && vec[elemIzq] < pivote) { // Busca elemento mayor o igual al pivote
					elemIzq++;
				}
				while (elemDer > inicio && vec[elemDer] >= pivote) { // Busca elemento menor al pivote
					elemDer--;
				}
				if (elemIzq < elemDer) { // Si no se han cruzado
					int temp = vec[elemIzq]; // Intercambia elementos
					vec[elemIzq] = vec[elemDer];
					vec[elemDer] = temp;
				}
			}

			if (elemDer > inicio) { // Si el índice derecho es mayor que el índice de inicio
				int temp = vec[inicio]; // Intercambia el pivote con el elemento en el índice derecho
				vec[inicio] = vec[elemDer];
				vec[elemDer] = temp;
			}

			// Crea tareas para los subvectores izquierdo y derecho
			QuickSortTask tareaIzq = new QuickSortTask(vec, inicio, elemDer - 1);
			QuickSortTask tareaDer = new QuickSortTask(vec, elemDer + 1, fin);

			invokeAll(tareaIzq, tareaDer); // Ejecuta ambas tareas de forma concurrente
		}
	}

}
