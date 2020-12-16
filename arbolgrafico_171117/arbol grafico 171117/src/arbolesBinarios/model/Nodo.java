package arbolesBinarios.model;

public class Nodo{

        public int dato;
        public Nodo izq;
        public Nodo der;
        public double x;
        public double y;
        public Nodo nodoAnterior;

        public Nodo(int dato, double x, double y){
            this.dato = dato;
            this.izq = null;
            this.der = null;
            this.x = x;
            this.y = y;
        }

        public int getDato() {
            return dato;
        }


        public Nodo getIzq() {
            return izq;
        }


        public Nodo getDer() {
            return der;
        }

}


