package sample;

import javax.swing.*;

public class Bhaskara {

    protected double a;
    protected double b;
    protected double c;
    protected double delta;
    protected double resp1;
    protected double resp2;

    public Bhaskara(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
        this.delta = this.delta();
        if (!calculo() ) {JOptionPane.showMessageDialog(null,"Algo errado! Reveja os seus dados");
            System.out.println("Algo errado! Reveja os seus dados");}

    }
    public boolean isBhaskara(){
        if (this.a == 0) return false;
        else return true;
    }

    private boolean calculo(){
        if (this.delta < 0) return false;
        else {
            if ( this.a == 0 ) return false;
            else {
                this.resp1 = (-this.b + Math.sqrt(this.delta)) / (2 * this.a);
                this.resp2 = (-this.b - Math.sqrt(this.delta)) / (2 * this.a);
                return true;
            }
        }
    }

    protected double delta(){
        return b*b - 4 * a * c;
    }

}
