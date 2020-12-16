package arbolesBinarios.model;

import javafx.scene.control.TextField;

public class Arbol {
    Nodo raiz = null;
    private Nodo Nodoactual;
    private String datos = "";
    private String datos2 = "";
    public boolean auxIzq = false;
    public boolean auxDer = false;
    public boolean auxRaiz = false;
    private TextField textField;

    public Nodo getNodo(){
        return Nodoactual;
    }

    public boolean tieneraiz() {
        if (raiz == null)
            return false;
        else
            return true;
    }

    public Arbol alta(int dat) {
        double x = 0.0, y = 0.0;
        if (!tieneraiz()) {
            Nodo nuevo = new Nodo(dat, 290, 15);
            raiz = nuevo;
            Nodoactual = nuevo;
            auxRaiz = true;
        } else {
            boolean izq;
            Nodo actual = raiz;
            while (true) {
                x = 0.0;
                y = 0.0;
                if (actual.dato < dat)
                    izq = false;
                else
                    izq = true;
                if (!izq) {
                    if (actual.der == null) {

                        x = actual.x + 70;
                        y = actual.y + 50;

                        Nodo nuevo = new Nodo(dat, x, y);
                        nuevo.nodoAnterior = actual;
                        Nodoactual = nuevo;
                        actual.der = nuevo;
                        auxDer = true;
                        auxIzq = false;
                        break;
                    } else
                        actual = actual.der;
                } else {
                    if (actual.izq == null) {

                        x = actual.x - 70;
                        y = actual.y + 50;

                        Nodo nuevo = new Nodo(dat, x, y);
                        nuevo.nodoAnterior = actual;
                        Nodoactual = nuevo;
                        actual.izq = nuevo;
                        auxIzq = true;
                        auxDer = false;
                        break;
                    } else
                        actual = actual.izq;
                }
            }
        }
        return this;
    }

    public String imP(){
        datos2 = "";
        return ayudantePreorden(raiz);
    }

    public String ayudantePreorden(Nodo dat){
        if(dat==null)
            return null;
        datos = String.valueOf(dat.dato);
        datos2 = datos2 + " " +  datos + ",";
        ayudantePreorden(dat.izq);
        ayudantePreorden(dat.der);
        return datos2;
    }

    public String imI(){
        datos2 = "";
        return imprimir_inorden(raiz);
    }

    public String imprimir_inorden(Nodo dat){
        if(dat!=null){
            imprimir_inorden(dat.izq);
            datos = String.valueOf(dat.dato);
            datos2 = datos2 + " " +  datos + ",";
            imprimir_inorden(dat.der);
        }
        return datos2;
    }

    public String imPo(){
        datos2 = "";
        return ayudantePostorden(raiz);
    }

    public String ayudantePostorden(Nodo dat){
        if(dat==null)
            return null;
        ayudantePreorden(dat.izq);
        ayudantePreorden(dat.der);
        datos = String.valueOf(dat.dato);
        datos2 = datos2 + " " +  datos + ",";
        return datos2;
    }

    public boolean borrar(int d){
        Nodo auxiliar = raiz;
        Nodo padre = raiz;
        boolean esHijoIzq = true;
        while(auxiliar.dato != d){
            padre = auxiliar;
            if(d < auxiliar.dato){
                esHijoIzq = true;
                auxiliar = auxiliar.izq;
            } else {
                esHijoIzq = false;
                auxiliar = auxiliar.der;
            }

            if(auxiliar == null){
                return false;
            }
        }
        if(auxiliar.izq == null && auxiliar.der == null){
            if(auxiliar == raiz){
                raiz = null;
            } else if(esHijoIzq){
                padre.izq = null;
            } else {
                padre.der = null;
            }
        } else if(auxiliar.der == null){
            if(auxiliar == raiz){
                raiz = auxiliar.izq;
            } else if(esHijoIzq){
                padre.izq = auxiliar.izq;
            } else {
                padre.der = auxiliar.izq;
            }
        }else if(auxiliar.izq == null){
            if(auxiliar == raiz){
                raiz = auxiliar.der;
            } else if(esHijoIzq){
                padre.izq = auxiliar.der;
            } else {
                padre.der = auxiliar.der;
            }
        } else {
            Nodo reemplazo = obtenerReemplazo(auxiliar);
            if(auxiliar == raiz){
                raiz = reemplazo;
            }else if(esHijoIzq){
                padre.izq = reemplazo;
            } else {
                padre.der = reemplazo;
            }
            reemplazo.izq = auxiliar.izq;
        }
        return true;
    }
    public Nodo obtenerReemplazo(Nodo reemp){
        Nodo reemplazarPadre = reemp;
        Nodo reemplazo = reemp;
        Nodo auxiliar = reemp.der;

        while(auxiliar != null){
            reemplazarPadre = reemplazo;
            reemplazo = auxiliar;
            auxiliar = auxiliar.izq;
        }
        if(reemplazo != reemp.der){
            reemplazarPadre.izq = reemplazo.der;
            reemplazo.der = reemp.der;
        }
        return reemplazo;
    }


    public boolean baja(int dat){
        Nodo actual = raiz, anterior=raiz, temp;
        boolean encontrado=false;
        if(actual==null){
            textField.setText("El arbol esta vacio");
        }else if(actual.dato==dat){
            raiz=null;
            encontrado=true;
        }
        else{
            while (true) {
                if(actual!=null){
                    if(actual.dato==dat){
                        if(anterior.dato>dat){
                            anterior.izq = null;
                        }else{
                            anterior.der = null;
                        }
                        if(actual.izq!=null || actual.der!=null){
                            borrarRamas(actual);
                            actual=null;
                        }
                        encontrado=true;
                        break;
                    }else if(actual.dato<dat){
                        temp=actual;
                        actual=actual.der;
                        anterior=temp;
                    }else if(actual.dato>dat){
                        temp=actual;
                        actual=actual.izq;
                        anterior=temp;
                    }
                }else
                    break;
            }
        }
        return encontrado;
    }

    private void borrarRamas(Nodo nodo){
        Nodo actual=nodo, izq, der;
        if(actual==null)
            textField.setText(("El nodo es nulo"));
        else{
            izq=nodo.izq;
            der=nodo.der;
            if(izq!=null){
                alta(izq.dato);
                borrarRamas(izq);
            }
            if(der!=null){
                alta(der.dato);
                borrarRamas(der);
            }
        }
    }
}
