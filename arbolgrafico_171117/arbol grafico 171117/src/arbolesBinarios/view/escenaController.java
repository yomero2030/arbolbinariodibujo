package arbolesBinarios.view;

import arbolesBinarios.model.Arbol;
import arbolesBinarios.model.Nodo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class escenaController implements  Initializable {
        @FXML
        private Canvas pizarron;
        @FXML
        private Label Datos;
        @FXML
        private Label Recorridos;
        @FXML
        private TextField tAnanir;
        @FXML
        private TextField tEliminar;
        @FXML
        private TextField tBuscar;
        @FXML
        private TextArea tArea;
        @FXML
        private Button bAgregar;
        @FXML
        private Button bEliminar;
        @FXML
        private Button bBuscar;
        @FXML
        private Button bInorden;
        @FXML
        private Button bPostorden;
        @FXML
        private Button bPreorden;

        private GraphicsContext gc;
        private Arbol arbol = new Arbol();
        private Nodo nodoRaiz;

        public void imprimirArbol(Nodo node) {
            gc.setStroke(Color.LIGHTSLATEGREY);
            if (node.nodoAnterior != null) {
                gc.strokeLine(node.nodoAnterior.x + 15, node.nodoAnterior.y + 10, node.x + 15, node.y);//
                gc.fillText(String.valueOf(node.nodoAnterior.dato), node.nodoAnterior.x + 10, node.nodoAnterior.y + 20);
            }

            gc.setFill(Color.LIGHTSLATEGREY);
            gc.setLineWidth(2);


            gc.fillOval(node.x, node.y, 30, 30);
            gc.setFill(Color.WHITE);
            gc.fillText(String.valueOf(node.dato), node.x + 10, node.y + 20);
            if (node.izq != null) {
                imprimirArbol(node.izq);
            }

            if (node.der != null) {
                imprimirArbol(node.der);
            }
        }

        @FXML
        public void botonAnadir(ActionEvent event) {
            try {
                arbol.alta(Integer.parseInt(tAnanir.getText()));
                tAnanir.setText("");
                if (nodoRaiz == null) {
                    nodoRaiz = arbol.getNodo();
                }
                System.out.println(nodoRaiz);
                imprimirArbol(nodoRaiz);
            } catch (NumberFormatException ex) {
                tAnanir.setText("Ingrese un dato valido");
                tAnanir.selectAll();
                tAnanir.requestFocus();
            }
        }

        @FXML
        public void botonEliminar(ActionEvent event) {
            try {
                arbol.borrar(Integer.parseInt(tEliminar.getText()));
                tEliminar.setText("");
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, 600, 400);
                imprimirArbol(nodoRaiz);
            } catch (NumberFormatException exception) {
                tEliminar.setText("Ingrese un dato valido");
                tEliminar.selectAll();
                tEliminar.requestFocus();
            }
        }



        @FXML
        public void text(MouseEvent mouseEvent) {
            tAnanir.selectAll();
            tAnanir.requestFocus();
        }

        @FXML
        public void botonInorden(ActionEvent event) {
            tArea.setText(arbol.imI());
        }

        @FXML
        public void botonPreorden(ActionEvent event) {
            tArea.setText(arbol.imP());
        }

        @FXML
        public void botonPostorden(ActionEvent event) {
            tArea.setText(arbol.imPo());
        }

        @Override
        public void initialize(URL url, ResourceBundle rb){
            gc = pizarron.getGraphicsContext2D();
        }

}
