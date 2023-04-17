package co.uniquindio.banco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Cuenta implements Serializable {

    private String numCuenta;
    private Titular titular;
    private String tipoCuenta;
    private double saldo;
    private ArrayList<Transaccion> historial;

    public Cuenta(String numCuenta, Titular titular, String tipoCuenta, double saldo) {
        this.numCuenta = numCuenta;
        this.titular = titular;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        historial = new ArrayList<Transaccion>();
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public Titular getTitular() {
        return titular;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<Transaccion> getHistorial() {
        return historial;
    }

    public void realizarTransaccion(String tipoTransaccion, double valor) {
        double saldoInicial = saldo;
        if (tipoTransaccion.equals("DEPOSITO")) {
            saldo += valor;
        } else if (tipoTransaccion.equals("RETIRO")) {
            saldo -= valor;
        }
        Date fecha = new Date();
        Transaccion transaccion = new Transaccion(saldoInicial, tipoTransaccion, valor, fecha, saldo);
        historial.add(transaccion);
    }

    @Override
    public String toString() {
        return "\n"+"Cuenta{" +
                "numCuenta='" + numCuenta + '\'' +
                ", titular=" + titular +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", saldo=" + saldo +
                ", historial=" + historial +
                '}';
    }
}
