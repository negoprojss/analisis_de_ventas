package analisis_ventas.datos;

import analisis_ventas.dominio.Articulo;
import analisis_ventas.dominio.ProductoTop;
import analisis_ventas.dominio.Venta;

import java.util.List;
import java.util.Scanner;

public interface IVentaDAO {
    List<Articulo> listarArticulos();
    boolean realizarVentas(Venta venta);
    List<ProductoTop> contarVentasAltas();
    double calcularPromedio(Scanner id_articulo);
    boolean calcularTotal();
}
