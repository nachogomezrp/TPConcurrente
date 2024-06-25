package algoritmos;

public class Test {

    public static void main(String[] args) {
        int tam = 1000000; // Especifica el tamaño del array aquí

        // Generar el array aleatorio
        int[] vecOriginal = QuickSortConcurrente.generarVectorAleatorio(tam);

        // Copiar el array para cada método de ordenación
        int[] vecSecuencial = vecOriginal.clone();
        int[] vecConcurrente = vecOriginal.clone();

        // Prueba con QuickSort Secuencial
        System.out.println("Probando QuickSort Secuencial con tamanio de array: " + tam);
        long inicioSecuencial = System.currentTimeMillis();
        QuickSortSecuencial.ordenacionRapida(vecSecuencial);
        long finSecuencial = System.currentTimeMillis();
        System.out.println("Tiempo de ordenacion secuencial: " + (finSecuencial - inicioSecuencial) + " ms");

        // Prueba con QuickSort Concurrente
        System.out.println("Probando QuickSort Concurrente con tamanio de array: " + tam);
        long inicioConcurrente = System.currentTimeMillis();
        QuickSortConcurrente.ordenacionRapida(vecConcurrente);
        long finConcurrente = System.currentTimeMillis();
        System.out.println("Tiempo de ordenacion concurrente: " + (finConcurrente - inicioConcurrente) + " ms");
        
        //Verificar que ambos métodos produjeron el mismo resultado
        boolean igual = true;
        for (int i = 0; i < tam; i++) {
            if (vecSecuencial[i] != vecConcurrente[i]) {
                igual = false;
                break;
            }
        }
        System.out.println("Ambos metodos produjeron el mismo resultado? " + (igual ? "Si" : "No"));

        
    }
}
