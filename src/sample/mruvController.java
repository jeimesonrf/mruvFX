package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.HashMap;

public class mruvController {
    public TextField finPosEdt;
    public TextField iniPosEdt;
    public TextField iniVelEdt;
    public TextField timEdt;
    public TextField accEdt;
    public static int countErr = 0;
    public static int op = 0;
    public Label lblResult;

    @FXML
    protected void btnCalcularAction(ActionEvent actionEvent) {
        countErr = 0;
        double finPos = 0, iniPos = 0, iniVel = 0, time = 0, acelera = 0, result = 0;
        String unidade = "", textoFinal= "";
        DecimalFormat df = new DecimalFormat("##.##");
        finPos = getValor(finPosEdt, "fp");
        iniPos = getValor(iniPosEdt, "ip");
        iniVel = getValor(iniVelEdt, "iv");
        time = getValor(timEdt, "ti");
        acelera = getValor(accEdt, "ac");
        result = -777.77;
        if (countErr > 1) {
            JOptionPane.showMessageDialog(null,"Você deve deixar somente um espaço vazio");
        } else if (countErr == 0){
            JOptionPane.showMessageDialog(null, "Você deve preencher 4 espaços");
        } else  {
            switch(op){
                case 0 :
                    result = iniPos+iniVel*time+0.5*acelera*time*time;
                    unidade = "m";
                    break;
                case 1:
                    result = finPos - iniVel*time - 0.5*acelera*time*time;
                    unidade = "m";
                    break;
                case 2:
                    result = (finPos - iniPos - 0.5*acelera*time*time)/time;
                    unidade = "m/s";
                    break;
                case 4:
                    result = (2*(finPos-iniPos-iniVel*time))/(time*time);
                    unidade = "m/s2";
                    break;
                case 3:
                    double result2;
                    Bhaskara bhask = new Bhaskara(0.5*acelera, iniVel,(iniPos-finPos));
                    result = bhask.resp1;
                    result2 = bhask.resp2;
                    System.out.println(bhask.resp1+" "+bhask.resp2);
                    if (result >= 0) textoFinal = "t1 = "+df.format(result)+" s";

                    if ((result >= 0) && (result2 >= 0)) textoFinal+=" e ";

                    if (result2 >= 0) textoFinal = "t2 = "+df.format(result2)+" s";


                    break;
            }
            if (textoFinal.isBlank()){
                System.out.println("aqui "+99);
                textoFinal=df.format(result)+" "+unidade;
            }
            lblResult.setText("Resultado: "+textoFinal);
        }

    }

    protected double getValor(TextField t, String s){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("fp", 0);
        map.put("ip", 1);
        map.put("iv", 2);
        map.put("ti", 3);
        map.put("ac", 4);
        double v = 0;
        try {
            v = Double.parseDouble(t.getText().replace(",","."));
        } catch ( Exception e) {
            v = -777.77;
            System.out.println("Deve ser calculado: "+s);
            countErr++;
            op = map.get(s);
        }
        return v;
    }

    protected boolean testValue(double d){
        if ( d == -777.77) return true;
        else return false;
    }

    public void btnAjudaAction(ActionEvent actionEvent) throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ajuda.fxml"));
        primaryStage.setTitle("Ajuda");
        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

    public void edtTime(ActionEvent actionEvent) {
        System.out.println(timEdt.getText());
    }
}
