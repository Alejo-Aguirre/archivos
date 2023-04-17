package co.uniquindio.banco.controller;

import co.uniquindio.banco.model.Cuenta;
import co.uniquindio.banco.model.Titular;
import co.uniquindio.banco.model.Transaccion;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class ControllerBanco {

    private ArrayList<Cuenta> cuentas;
    private ArrayList<Titular> titulares;

    public ControllerBanco() {
        cuentas = new ArrayList<Cuenta>();
        titulares = new ArrayList<Titular>();
        cargarCuentas();
    }

    public void crearTitular(String id, String nombre) {
        Titular titular = new Titular(id, nombre);
    }

    public void crearCuenta(String numCuenta, Titular titular, String tipoCuenta, double saldo) {
        Cuenta cuenta = new Cuenta(numCuenta, titular, tipoCuenta, saldo);
        cuentas.add(cuenta);
    }

    public void realizarTransaccion(Cuenta cuenta, String tipoTransaccion, double valor) {
        cuenta.realizarTransaccion(tipoTransaccion, valor);
    }

    public void guardarDatos() {
        try {
            FileOutputStream fos = new FileOutputStream("cuentas.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cuentas);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarCuentas() {
        try {
            FileInputStream fis = new FileInputStream("cuentas.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            cuentas = (ArrayList<Cuenta>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Titular obtenerTitular(String id) {
        for (Titular titular : titulares) {
            if (titular.getId().equals(id)) {
                return titular;
            }
        }
        return null; // Si no se encuentra el titular con el ID proporcionado
    }

    public Cuenta obtenerCuenta(String numCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumCuenta().equals(numCuenta)) {
                return cuenta;
            }
        }
        return null; // Si no se encuentra la cuenta, retorna null
    }

    public void generarReporteCuenta() {
        String numCuenta = JOptionPane.showInputDialog("Ingrese el número de cuenta para generar el reporte:");
        Cuenta cuenta = obtenerCuenta(numCuenta);
        if (cuenta == null) {
            JOptionPane.showMessageDialog(null, "La cuenta no existe");
            return;
        }
        String ruta = JOptionPane.showInputDialog("Ingrese la ruta y el nombre del archivo de texto para generar el reporte:");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ruta));
            writer.write("Reporte de cuenta\n");
            writer.write("Número de cuenta: " + cuenta.getNumCuenta() + "\n");
            writer.write("Titular: " + cuenta.getTitular().getNombre() + "\n");
            writer.write("Tipo de cuenta: " + cuenta.getTipoCuenta() + "\n");
            writer.write("Saldo: " + cuenta.getSaldo() + "\n");
            writer.write("Historial de transacciones:\n");
            for (Transaccion transaccion : cuenta.getHistorial()) {
                writer.write("Fecha: " + transaccion.getFecha() + ", Hora: " + transaccion.getFecha() + ", Tipo: " + transaccion.getTipoTransaccion() + ", Valor: " + transaccion.getValor() + ", Saldo final: " + transaccion.getSaldoFinal() + "\n");
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "El reporte se ha generado exitosamente en la ruta especificada");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al generar el reporte: " + e.getMessage());
        }
    }

    public void generarReporteCuenta2() {
        // Obtener la cuenta por su número
        String numCuenta = JOptionPane.showInputDialog("Ingrese el número de cuenta:");
        Cuenta cuenta = obtenerCuenta(numCuenta);
        if (cuenta == null) {
            JOptionPane.showMessageDialog(null, "La cuenta no existe");
            return;
        }

        // Solicitar la ruta donde se guardará el archivo
        String ruta = JOptionPane.showInputDialog("Ingrese la ruta donde se guardará el archivo:");
        File archivo = new File(ruta);
        if (!archivo.exists() || !archivo.isDirectory()) {
            JOptionPane.showMessageDialog(null, "La ruta no existe o no es una carpeta");
            return;
        }

        // Generar el nombre del archivo utilizando el número de cuenta
        String nombreArchivo = "reporte_" + numCuenta + ".txt";
        File archivoReporte = new File(ruta, nombreArchivo);

        // Escribir el reporte en el archivo
        try (PrintWriter writer = new PrintWriter(archivoReporte)) {
            writer.println("Reporte de cuenta");
            writer.println("------------------");
            writer.println("Número de cuenta: " + cuenta.getNumCuenta());
            writer.println("Titular: " + cuenta.getTitular().getNombre());
            writer.println("Tipo de cuenta: " + cuenta.getTipoCuenta());
            writer.println("Saldo actual: " + cuenta.getSaldo());
            writer.println();
            writer.println("Historial de transacciones:");
            writer.println("--------------------------");
            writer.println("Fecha\t\tTipo\tValor\tSaldo");

            ArrayList<Transaccion> transacciones = cuenta.getHistorial();
            for (Transaccion transaccion : transacciones) {
                writer.println(transaccion.getFecha() + "\t" + transaccion.getTipoTransaccion() +
                        "\t" + transaccion.getValor() + "\t" + transaccion.getSaldoFinal());
            }

            JOptionPane.showMessageDialog(null, "Reporte generado exitosamente");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage());
        }
    }



    public ArrayList<Cuenta> getCuentas(){
        return cuentas;
    }

    public ArrayList<Titular> getTitulares(){
        return titulares;
    }
}
