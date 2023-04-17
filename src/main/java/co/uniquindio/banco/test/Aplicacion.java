package co.uniquindio.banco.test;

import co.uniquindio.banco.controller.ControllerBanco;
import co.uniquindio.banco.model.Cuenta;
import co.uniquindio.banco.model.Titular;

import javax.swing.*;

public class Aplicacion {

    public static void main(String[] args) {
        ControllerBanco controller = new ControllerBanco();
        controller.getCuentas().clear();//para que queme una sola vez los datos iniciales
        Titular titular1 = new Titular("100.999.888", "Juan Perez");
        controller.crearCuenta("123", titular1, "Ahorros", 900000);
        controller.realizarTransaccion(controller.getCuentas().get(0), "DEPOSITO", 500);
        controller.realizarTransaccion(controller.getCuentas().get(0), "RETIRO", 33);
        controller.guardarDatos();

        System.out.println(controller.getCuentas());

        // Pedir si quieren generar reporte
        while (true) {
            controller.generarReporteCuenta2();
            // Preguntar si el usuario desea realizar otro reporte
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea realizar otro reporte?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.NO_OPTION) {
                break;
            }
        }

        //MEJORAR CON UN MENU PARA CREAR O GENERAR EL REPORTE

        // Pedir datos del titular
        String idTitular = JOptionPane.showInputDialog(null, "Ingrese el ID del titular:");
        String nombreTitular = JOptionPane.showInputDialog(null, "Ingrese el nombre del titular:");

        // Crear titular
        controller.crearTitular(idTitular, nombreTitular);

        // Pedir datos de la cuenta
        String numCuenta = JOptionPane.showInputDialog(null, "Ingrese el número de cuenta:");
        String tipoCuenta = JOptionPane.showInputDialog(null, "Ingrese el tipo de cuenta (Ahorros o Corriente):");
        double saldoInicial = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el saldo inicial de la cuenta:"));

        // Obtener titular creado
        Titular titular = controller.obtenerTitular(idTitular);

        // Crear cuenta
        controller.crearCuenta(numCuenta, titular, tipoCuenta, saldoInicial);

        // Pedir transacciones hasta que el usuario decida salir
        while (true) {
            String tipoTransaccion = JOptionPane.showInputDialog(null, "Ingrese el tipo de transacción (DEPOSITO o RETIRO):");
            double valorTransaccion = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el valor de la transacción:"));

            // Obtener cuenta creada
            Cuenta cuenta = controller.obtenerCuenta(numCuenta);

            // Realizar transacción
            controller.realizarTransaccion(cuenta, tipoTransaccion, valorTransaccion);

            // Preguntar si el usuario desea realizar otra transacción
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea realizar otra transacción?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.NO_OPTION) {
                break;
            }
        }


        // Guardar datos
        controller.guardarDatos();



    }
}
