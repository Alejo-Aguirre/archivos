package co.uniquindio.banco.model;

import java.io.Serializable;
import java.util.Date;

public class Transaccion implements Serializable {
    private double saldoInicial;
    private String tipoTransaccion;
    private double valor;
    private Date fecha;
    private double saldoFinal;

    public Transaccion(double saldoInicial, String tipoTransaccion, double valor, Date fecha, double saldoFinal) {
        this.saldoInicial = saldoInicial;
        this.tipoTransaccion = tipoTransaccion;
        this.valor = valor;
        this.fecha = fecha;
        this.saldoFinal = saldoFinal;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public double getValor() {
        return valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    @Override
    public String toString() {
        return "\n"+"Transaccion{" +
                "saldoInicial=" + saldoInicial +
                ", tipoTransaccion='" + tipoTransaccion + '\'' +
                ", valor=" + valor +
                ", fecha=" + fecha +
                ", saldoFinal=" + saldoFinal +
                '}';
    }
}
