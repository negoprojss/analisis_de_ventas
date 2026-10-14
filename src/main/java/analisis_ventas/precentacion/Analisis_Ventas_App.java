package analisis_ventas.precentacion;

import analisis_ventas.datos.VentaDAO;
import analisis_ventas.dominio.Venta;

import javax.print.DocFlavor;
import java.util.Scanner;

public class Analisis_Ventas_App {
    public static void main(String[] args) {
        AnalisisVentasApp();
    }

    private static void AnalisisVentasApp(){
        var salida = false;
        var ventaDAO = new VentaDAO();
        Scanner consola = new Scanner(System.in);
        try {
            while (!salida){
                var opcion = motrarMenu(consola);
                salida = ejecutarOpciones(opcion,consola,ventaDAO);
            }
        }catch (Exception e){
            System.out.println("Error Venta_Analisis_App encontrado: "+e.getMessage());
            e.printStackTrace();
        }

    }

    private static int motrarMenu(Scanner consola) {
        System.out.print("""
                Menu:
                0.Listar Cliente
                1.Realiza Venta
                2.Contar Ventas Altas
                3.Calcular Promedio Ventas
                4.Calcular Total Ventas
                5.Salir
                Elige una opcion:""");
        // Leemos y retornamos la opcion seleccionada
        return consola.nextInt();
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, VentaDAO ventaDAO) {
        var salir = false;
        switch (opcion){
            case 0 ->listarArticulos(ventaDAO);
            case 1 ->realizarVentas(ventaDAO, consola);
            case 2 ->contarVentasAltas(ventaDAO,consola);
            case 3 ->calcularPromedio(ventaDAO,consola);
            case 4 ->calcularTotal(ventaDAO);
            case 5 -> {
                System.out.println("**** Regresa Pronto! ****");
                salir= true;
            }
        }
        return salir;
    }

    private  static boolean listarArticulos(VentaDAO ventaDAO){
        System.out.println("++ lista de Articulos ++");
        var clientes = ventaDAO.listarArticulos();
        clientes.forEach(System.out::println);
        return true;
    }

    private static boolean realizarVentas(VentaDAO ventaDAO,Scanner consola){
        System.out.println("escribe el id: ");
        var id_articulo = consola.nextInt();

        var nuevaVenta = new Venta(id_articulo);
        var agregado = ventaDAO.realizarVentas(nuevaVenta);
        if (agregado)
            System.out.println("se agrego: "+nuevaVenta);
        else
            System.out.println("nose puedo agregar: "+ventaDAO);
        return true;
    }

    private static  boolean contarVentasAltas(VentaDAO ventaDAO, Scanner consola){
        System.out.println("++ productos mas vendidos ++");
        var clientes = ventaDAO.contarVentasAltas();
        clientes.forEach(System.out::println);
        return true;
    }

    private static  boolean calcularPromedio(VentaDAO ventaDAO, Scanner consola){
        System.out.println("escribe el id: ");
        var buscado = ventaDAO.calcularPromedio(consola);
        if (buscado != 0.0)
            System.out.println("se agrego: "+buscado);
        else
            System.out.println("nose puedo encontrar: "+buscado);
        return true;
    }

    private static  boolean calcularTotal(VentaDAO ventaDAO){
        var calcularTotal = ventaDAO.calcularTotal();
        if (calcularTotal)
            System.out.println("Exito !!!!!!!");
        else
            System.out.println("nose puedo ser ;( ");
        return true;
    }

}
