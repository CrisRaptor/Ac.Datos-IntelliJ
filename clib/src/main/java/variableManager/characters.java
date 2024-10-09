package variableManager;

/**
 *
 * @author CrisGC
 */
public class characters {
    /**
     * Cambia todos los caracteres a su posicion opuesta
     * @param cadena
     * @return una String invertido
     */
    static String invertir(String cadena){
        String cadenaInvertida = "";
        for (int i = cadena.length(); i > 0; i--) {
            cadenaInvertida += cadena.charAt(i-1);
        }
        return cadenaInvertida;
    }
    
    /**
     * Cuenta la cantidad de SpaceChar de una cadena
     * @param cadena
     * @return devuelve la cantidad de SpaceChar 
     */
    static int contarEspacios(String cadena){
        int cont = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isSpaceChar(cadena.charAt(i))) {
                cont++;
            }
        }
        return cont;
    }
    
    /**
     * Cuenta la cantidad de veces que un String aparece en Otro
     * @param base String donde se busca la cadena
     * @param palabra String buscado en la otra cadena
     * @return Numero de apariciones de 'palabra' en 'base'
     */
    static int apariciones(String base, String palabra) {
        int cant = 0, pos = 0;

        for (int i = 0; i < base.length(); i++) {
            if (base.charAt(i) == palabra.charAt(0)) {
                while (pos < palabra.length() && base.charAt(i + pos) == palabra.charAt(pos)) {
                    pos++;
                }
                if (pos == palabra.length()) {
                    cant++;
                }
            }
        }
        return cant;
    }
    
    /**
     * Comprueba si la cadena se encuentra en otra cadena
     * @param base String donde se busca la cadena
     * @param palabra String buscado en la otra cadena
     * @return Boolean, si el String de 'palabra' se encuentra en 'base'
     */
    static boolean aparece(String base, String palabra) {
        int posInicial = 0, pos = 0;
        boolean aparece = false;

        while (posInicial < base.length() && !aparece) {
            
            if (base.charAt(posInicial) == palabra.charAt(0)) {
                while (pos < palabra.length() && base.charAt(posInicial + pos) == palabra.charAt(pos)) {
                    pos++;
                }
                if (pos == palabra.length()) {
                    aparece = true;
                }
            }
            posInicial++;
        }
        return aparece;
    }
}
