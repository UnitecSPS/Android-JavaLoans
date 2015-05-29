package lab.unitec.android.javaloans;

/**
 * Created by Docente 17082011 on 27/02/2015.
 */
public class Loan {
    public String cliente;
    public double balance;

    public Loan(String c, double bal){
        cliente = c;
        balance = bal;
    }

    public double pay(double m){
        if(m > balance){
            m = balance;
        }
        balance -= m;
        return m;
    }
}
