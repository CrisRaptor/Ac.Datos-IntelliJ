package variableManager;

/**
 * Libreria de funciones para operaciones con int de 1.
 * 
 * @author CrisGC
 */
public class numbers {

    /*Averigua si num es capicua
    Devuelve el boolean*/
    static boolean capicua(int num) {
        int longitud = 0, digito;
        //Calcula la longitud del valor
        for (int i = num; i > 0; i /= 10) {
            longitud++;
        }

        while (longitud / 2 > 0) {
            //Sacar el primer digito de la izquierda
            digito = (int) ((num / (Math.pow(10, longitud - 1))) % 10);
            //Comparar con el ultimo digito
            if (digito != (num % 10)) {
                return false;
            }
            //Borrar ambos digitos
            num -= digito * Math.pow(10, longitud - 1);
            num /= 10;
            //Reajustar la longitud
            longitud -= 2;
        }
        return true;
    }

    /*Calcula si num es primo
    Devuelve el boolean*/
    static boolean primo(int num) {
        for (int j = 2; j < num; j++) {
            if (num % j == 0) {
                return false;
            }
        }
        return true;
    }

    /*Devuelve el siguiente primo*/
    static int sPrimo(int num) {
        do {
            num++;
        } while (!primo(num));
        return num;
    }

    /*Eleva sin usar Math.pow
    Devuelve (int)el resultado*/
    static int potencia(int num, int potencia) {
        final int numBase = num;
        for (int i = 1; i < potencia; i++) {
            num *= numBase;
        }
        return num;
    }

    /*Cuenta la cantidad de digitos de un numero (int)
    Devuelve la cantidad*/
    static int digitos(int num) {
        int longitud = 0;
        //Calcula la longitud del valor
        for (int i = num; i > 0; i /= 10) {
            longitud++;
        }
        return longitud;
    }

    /*Invierte el orden numero
    Devuelve el resultado*/
    static int voltea(int num) {
        //En caso de 1 digito no hace falta
        if (num < 10) {
            return num;
        }
        //Volteamos el numero
        int volteado = 0;
        while (num > 0) {
            volteado = num % 10 + volteado * 10;
            num /= 10;
        }
        return volteado;
    }

    /*Busca la posicion 
    Devuelve el digito*/
    static int buscaDigito(int num, int posicion) {
        return (int) (num / (int) Math.pow(10, digitos(num) - posicion - 1)) % 10;
    }

    /*Busca un digito (int)
    Devuelve la posicion del primer encuentro*/
    static int buscaPosicion(int num, int digito) {
        for (int i = 0; i < digitos(num); i++) {
            if (digito == buscaDigito(num, i)) {
                return i;
            }
        }
        return -1;
    }

    /*Borra una cantidad de digitos de un numero (int) por la derecha
    Devuelve el resultado*/
    static int quitarD(int num, int cant) {

        return num /= potencia(10, cant);
    }

    /*Borra una cantidad de digitos de un numero (int) por la derecha
    Devuelve el resultado*/
    static int quitarI(int num, int cant) {
        int aux = voltea(num);
        aux /= potencia(10, cant);
        return voltea(aux);
    }

    /*Añade uno u varios digitos a la derecha
    Devuelve el resultado*/
    static int pegarD(int num, int digitos) {
        return num * potencia(10, digitos(digitos)) + digitos;
    }

    /*Añade uno u varios digitos a la izquierda
    Devuelve el resultado*/
    static int pegarI(int num, int digitos) {
        int aux = voltea(num);
        aux = aux * potencia(10, digitos(digitos)) + digitos;
        return voltea(aux);
    }

    /*Devuelve el numero entre 2 posiciones*/
    static int trozo(int num, int posInicial, int posFinal) {
        int divisor = (int) Math.pow(10, digitos(num) - posFinal);
        return (num / divisor) % (int) Math.pow(10, posFinal - posInicial + 1);
    }

    static int random(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}