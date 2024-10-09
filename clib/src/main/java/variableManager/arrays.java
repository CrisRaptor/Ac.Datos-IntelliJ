package variableManager;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Libreria de funciones para trabajar con arrays de 1 dimension.
 *
 * @author CrisGC
 */
public class arrays {

    //Orden
    /**
     * Cambia la posicion de todos los valores.
     *
     * @param tabla Array a ser desordenado
     */
    static void desordenar(int[] tabla) {
        int aux, posicion;

        for (int i = 0; i < tabla.length; i++) {
            posicion = (int) (Math.random() * tabla.length);
            aux = tabla[i];
            tabla[i] = tabla[posicion];
            tabla[posicion] = aux;
        }
    }

    /**
     * Invierte las posiciones de los valores de una tabla, el valor en la
     * posicion 0 pasa a ultima posicion y viceversa.
     *
     * @param tabla
     */
    static void invertir(int[] tabla) {
        int aux;
        for (int i = 0; i < (tabla.length / 2); i++) {
            aux = tabla[tabla.length - 1 - i];
            tabla[tabla.length - 1 - i] = tabla[i];
            tabla[i] = aux;
        }
    }

    /**
     * Mueve todos los valores 1 posicion a la derecha.
     *
     * @param tabla
     */
    static void rotarDer(int[] tabla) {
        int reserva; //Almacena el digito sustituido
        int valor = tabla[0]; //Valor transferido

        //Rotaciones de posiciones 0 a la penultima posicion
        for (int i = 0; i < tabla.length - 1; i++) {
            reserva = tabla[i + 1];
            tabla[i + 1] = valor;
            valor = reserva;
        }
        tabla[0] = valor;
    }

    /**
     * Mueve todos los valores 1 posicion a la izquierda.
     *
     * @param tabla
     */
    static void rotarIzq(int[] tabla) {
        int reserva; //Almacena el digito sustituido
        int valor = tabla[tabla.length - 1]; //Valor transferido

        //Rotaciones de posiciones 0 a la penultima posicion
        for (int i = tabla.length - 1; i > 0; i--) {
            reserva = tabla[i - 1];
            tabla[i - 1] = valor;
            valor = reserva;
        }
        tabla[tabla.length - 1] = valor;
    }

    //Creacion
    /**
     * Crea un array usando 'scanner', solicita una longitud y tras esto
     * solicita los valores de 1 en 1.
     *
     * @return devuelve un int[] con los valores introducidos
     */
    static int[] crearTabla() {
        int[] tabla;
        int longitud;
        Scanner kin = new Scanner(System.in);

        //Crear la tabla
        do {
            System.out.print("Indica la cantidad de valores: ");
            longitud = kin.nextInt();
        } while (longitud < 0);

        tabla = new int[longitud];

        //Rellenar la tabla
        for (int i = 0; i < longitud; i++) {
            System.out.print("Indica el valor (" + (i + 1) + "): ");
            tabla[i] = kin.nextInt();
        }

        return tabla;
    }

    /**
     * Introduce valores aleatorios en todo el Array
     *
     * @param tabla array que se va a rellenar
     * @param max el maximo de los valores aleatorios
     * @param min el minimo de los valores aleatorios
     */
    static void rellenarTabla(int[] tabla, int max, int min) {
        for (int i = 0; i < tabla.length; i++) {
            tabla[i] = (int) (Math.random() * (max + 1 - min) + min);
        }
    }

    /**
     * Copia un array
     *
     * @param tabla array que se va a copiar
     * @return un int[] identico en valores y longitud
     */
    static int[] copiarTabla(int[] tabla) {
        int[] aux = new int[tabla.length];

        for (int i = 0; i < tabla.length; i++) {
            aux[i] = tabla[i];
        }

        return aux;
    }

    /**
     * Copia un array con una longitud modificada
     *
     * @param tabla array que se va a copiar
     * @param longitud tamaño del array devuelto
     * @return un int[] identico en valores con la longitud indicada
     */
    static int[] copiarTabla(int[] tabla, int longitud) {
        int[] aux = new int[longitud];
        int posicion = 0;

        while (posicion < tabla.length && posicion < longitud) {
            aux[posicion] = tabla[posicion];
        }

        return aux;
    }

    //Falta void copiarRangoTabla(int[] tabla, int tablaPos, int[] copia, int copiaPos, int longitud)
    //Eliminar
    /**
     * Borra un elemento y ajusta el tamaño del array
     *
     * @param tabla array modificado
     * @param posicion posicion eliminada
     * @return un int[] con todos los valores menos la posicion indicada y la
     * longitud ajustada
     */
    static int[] eliminarPosicion(int[] tabla, int posicion) {
        int[] aux;

        aux = new int[tabla.length - 1];
        //Copiamos los valores anteriores a la posicion
        System.arraycopy(tabla, 0, aux, 0, posicion);
        //Copiamos los valores posteriores a la posicion
        System.arraycopy(tabla, posicion + 1, aux, posicion, (aux.length - posicion));

        return aux;
    }

    /**
     * Elimina un valor exacto en un array ordenado
     *
     * @param tabla array ordenado para modificar
     * @param valor valor buscado
     * @return un array con todos los valores menos la posicion indicada y la
     * longitud ajustada
     */
    static int[] eliminarValorOrdenado(int[] tabla, int valor) {
        int[] aux;
        int posicion;

        posicion = Arrays.binarySearch(tabla, valor);
        if (posicion >= 0) {
            aux = new int[tabla.length - 1];
            //Copiamos los valores anteriores a la posicion
            System.arraycopy(tabla, 0, aux, 0, posicion);
            //Copiamos los valores posteriores a la posicion
            System.arraycopy(tabla, posicion + 1, aux, posicion, (aux.length - posicion));
        } else {
            aux = tabla;
        }

        return aux;
    }

    /**
     * Elimina todos los valores igaules o superiores a 'limite'
     *
     * @param tabla array sobre el que se elimina
     * @param limite valor inicial que se elimina (incluido)
     * @return una copia de 'tabla' sin los valores iguales o superiores a
     * limite y la longitud ajustada
     */
    static int[] eliminarMayores(int[] tabla, int limite) {
        int aux[] = new int[0];

        for (int valor : tabla) {
            if (valor <= limite) {
                aux = Arrays.copyOf(aux, (aux.length + 1));
                aux[aux.length - 1] = valor;
            }
        }
        return aux;
    }

    /**
     * Elimina todos los valores identicos de una tabla
     *
     * @param tabla array sobre el que se trabaja
     * @return una copia de 'tabla' sin los valores repetidos y la longitud
     * ajustada
     */
    static int[] eliminarRepetidos(int[] tabla) {
        int lista[] = new int[0];
        int pos;
        boolean encontrado;

        for (int buscado : tabla) {
            encontrado = false;
            pos = 0;
            while (pos < lista.length && !encontrado) {
                if (buscado == lista[pos]) {
                    encontrado = true;
                }
                pos++;
            }
            if (!encontrado) {
                lista = Arrays.copyOf(lista, (lista.length + 1));
                lista[lista.length - 1] = buscado;
            }
        }
        return lista;
    }

    //Almacenar
    /**
     * Almacena todos los valores de 'tabla' que tras sumar 'modificador'
     * resulten ser multiplos del valor 'multiplo' Por ejemplo, si el multiplo
     * es 2 y modificador 0, devuelve los valores pares encontrados, si el
     * modificador es 1 devuelve los valores impares.
     *
     * @param tabla array sobre el que se buscan los multiplos
     * @param multiplo valor divisor utilizado
     * @param modificador valor que se suma a cada valor de 'tabla' antes de
     * calcular si es un multiplo
     * @return devuelve un array con todos los valores que tras sumas
     * 'modificador' resultan multiplos del valor 'multiplo' y la longitud
     * ajustada.
     */
    static int[] almacenarMultiplos(int[] tabla, int multiplo, int modificador) {
        int temporal[] = new int[0];

        for (int valor : tabla) {
            if ((valor + modificador) % multiplo == 0) {
                temporal = Arrays.copyOf(temporal, (temporal.length + 1));
                temporal[temporal.length - 1] = valor;
            }
        }
        return temporal;
    }

    /**
     * Elimina los valores impares de un array.
     *
     * @param tabla array modificado
     * @return una copia de tabla sin los valores impares y la longitud ajustada
     */
    static int[] eliminarImpares(int[] tabla) {
        int temporal[] = new int[0];

        for (int valor : tabla) {
            if (valor % 2 == 0) {
                temporal = Arrays.copyOf(temporal, (temporal.length + 1));
                temporal[temporal.length - 1] = valor;
            }
        }
        return temporal;
    }

    /**
     * Elimina los valores pares de un array.
     *
     * @param base array modificado
     * @return una copia de tabla sin los valores pares y la longitud ajustada
     */
    static int[] eliminarPares(int[] base) {
        int temporal[] = new int[0];

        for (int valor : base) {
            if (valor % 2 == 1) {
                temporal = Arrays.copyOf(temporal, (temporal.length + 1));
                temporal[temporal.length - 1] = valor;
            }
        }
        return temporal;
    }

    /**
     * Inserta 'valor' en un array ordenado
     *
     * @param tabla array ordenado
     * @param valor valor a introducir
     * @return una copia de 'tabla' con el valor introducido y la longitud
     * ajustada
     */
    static int[] insercionOrdenada(int[] tabla, int valor) {
        int[] aux;
        int posicion;

        posicion = Arrays.binarySearch(tabla, valor);
        if (posicion < 0) {
            aux = new int[tabla.length + 1];
            posicion = -posicion - 1; //Calculamos la posicion donde deberia ir el valor
            //Copiamos los valores anteriores a la posicion
            System.arraycopy(tabla, 0, aux, 0, posicion);
            //Insertamos el valor
            aux[posicion] = valor;
            //Copiamos los valores posteriores a la posicion
            System.arraycopy(tabla, posicion, aux, posicion + 1, (tabla.length - posicion));
        } else {
            aux = tabla;
        }

        return aux;
    }

    /**
     * Inserta un valor al final de la tabla
     * @param tabla array
     * @param valor valor a introducir
     * @return una copia de 'tabla' con el valor introducido y longitud ajustada
     */
    static int[] insertarFin(int[] tabla, int valor) {
        tabla = Arrays.copyOf(tabla, tabla.length + 1);
        tabla[tabla.length - 1] = valor;
        return tabla;
    }

    //Operaciones
    /**
     * Compara un array ('respuesta') con otro array ('base').
     *
     * @param tabla array sobre el que se compara
     * @param respuesta array que comparamos
     * @return devuelve el numero de coincidencia entre los valores de la misma
     * posicion
     */
    static int compararArray(int[] tabla, int[] respuesta) {
        int aciertos = 0;

        System.out.print("[");
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] == respuesta[i]) {
                aciertos++;
            }
            //mostrarPista(base[i], respuesta[i]);
            if (i < (tabla.length - 1)) {
                System.out.print(",");
            }
        }
        System.out.println("]");

        return aciertos;
    }

    /**
     * Busca un 'valor' en el array 'tabla'
     *
     * @param tabla array donde buscamos
     * @param valor valor que buscamos
     * @return un array con todas los posiciones en las que se encuentra 'valor'
     */
    static int[] buscarIndices(int tabla[], int valor) {
        int[] aux = new int[0];

        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] == valor) {
                aux = Arrays.copyOf(aux, (aux.length + 1));
                aux[aux.length - 1] = i;
            }
        }

        return aux;
    }

    //Ordenacion
    /**
     * Ordena de menor a mayor una cadena con el metodo Burbuja
     *
     * @param array Tabla que se ordena
     */
    public static void burbuja(int[] array) {
        int aux, i = 0;
        boolean ordenado = false;

        while (i < array.length - 1 && !ordenado) {
            ordenado = true;

            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    ordenado = false;
                    aux = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = aux;
                }
            }

        }
    }

    /**
     * Ordena de menor a mayor una cadena con el metodo Burbuja menos eficiente
     *
     * @param array Tabla que se ordena
     */
    public static void burbuja2(int[] array) {
        int aux;

        for (int indice = 0; indice < array.length - 1; indice++) {
            for (int indice2 = 0; indice2 < array.length - indice - 1; indice2++) {
                if (array[indice2] > array[indice2 + 1]) {
                    aux = array[indice2];
                    array[indice2] = array[indice2 + 1];
                    array[indice2 + 1] = aux;
                }
            }
        }
    }

    /**
     * Ordena una tabla con el metodo de Seleccion
     *
     * @param array
     */
    public static void ordenacionSeleccion(int[] array) {
        int menor;
        int aux;
        int index = 0;

        for (int indice = 0; indice < array.length - 1; indice++) {
            menor = array[indice];

            for (int indice2 = indice + 1; indice2 < array.length; indice2++) {
                if (array[indice2] < menor) {
                    menor = array[indice2];
                    index = indice2;
                }
            }

            aux = array[indice];
            array[indice] = menor;
            array[index] = aux;
        }
    }

    /**
     * Inserta un valor de forma ordenada en una Array ya ordenado
     *
     * @param array Tabla ordenada
     */
    public static void ordenacionInsercion(int[] array) {
        int aux, j;

        for (int indice = 1; indice < array.length; indice++) {
            j = indice;
            aux = array[indice];

            while (j > 0 && aux < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }

            array[j] = aux;
        }
    }
}
