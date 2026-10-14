package analisis_ventas.datos;

import analisis_ventas.dominio.Articulo;
import analisis_ventas.dominio.ProductoTop;
import analisis_ventas.dominio.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static analisis_ventas.conexion.Conexion.getConexion;

public class VentaDAO implements IVentaDAO {


    @Override
    public List<Articulo> listarArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM articulo ORDER BY ID";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setProducto(rs.getString("producto"));
                articulo.setPrecio(rs.getDouble("precio"));
                articulos.add(articulo);
            }
        }catch (Exception e){
            System.out.println("Error al consultar");
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar conexion: "+e.getMessage());
            }
        }
        return articulos;
    }

    @Override
    public boolean realizarVentas(Venta venta) {
        PreparedStatement sp;
        Connection con = getConexion();
        try {
            var sql = "INSERT INTO ventas(id_articulo)" + "VALUES(?)";
            sp = con.prepareStatement(sql);
            sp.setInt(1, venta.getId_articulo());
            sp.execute();
            return true;
        } catch (Exception e) {
            System.out.println("error al insertarjjjj: " + e.getMessage());
        }
        return true;
    }

    @Override
    public List<ProductoTop> contarVentasAltas() {
        List<ProductoTop>  productoTop= new ArrayList<>();
        PreparedStatement sp;
        ResultSet rs;
        Connection con = getConexion();
        try{
//            var sql = "SELECT\n" +
//                    "    a.producto,\n" +
//                    "    COUNT(v.id_articulo) AS cantidad_vendida\n" +
//                    "FROM articulo a\n" +
//                    "INNER JOIN ventas v ON a.id_articulo = v.id_articulo\n" +
//                    "GROUP BY a.id_articulo, a.producto\n" +
//                    "ORDER BY cantidad_vendida DESC, a.producto ASC\n" +
//                    "LIMIT 3;";

            var sql = "SELECT a.producto, COUNT(v.id_articulo) AS cantidad_vendida"+
                    " FROM articulo AS a INNER JOIN ventas AS v ON a.id = v.id_articulo"+
                    " GROUP BY a.id, a.producto"+
                    " ORDER BY cantidad_vendida DESC LIMIT 3";

            sp = con.prepareStatement(sql);
            rs = sp.executeQuery();
            int puesto = 1;
            while (rs.next()) {
                String producto = rs.getString("producto");
                int cantidadVendida =
                        rs.getInt("cantidad_vendida");

                productoTop.add(new ProductoTop(
                        producto,
                        cantidadVendida,
                        puesto
                ));

                puesto++;
            }
            return productoTop;
        }catch (Exception e) {
            System.out.println("error al insertar: " + e.getMessage());
        }
        return productoTop;
    }

    @Override
    public double calcularPromedio(Scanner consola) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        try{
            var respuestaUser = Integer.parseInt(consola.nextLine());
            String sql = "SELECT COUNT(*) AS total_ventas " +
                    "FROM ventas WHERE id_articulo = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, respuestaUser);
            rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt("total_ventas");
                }


            return 0;
        }catch (Exception e) {
            System.out.println("error al insertar: " + e.getMessage());
        }
        return 0.0;
    }

    @Override
    public boolean calcularTotal() {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql =
                "SELECT " +
                        "SUM(a.precio) AS total_vendido, " +
                        "AVG(a.precio) AS promedio_por_producto, " +
                        "MAX(a.precio) AS venta_mas_alta, " +
                        "SUM(CASE WHEN a.precio >= 500 THEN 1 ELSE 0 END) " +
                        "AS ventas_mayores_500, " +
                        "(SELECT a2.producto " +
                        " FROM ventas v2 " +
                        " INNER JOIN articulo a2 " +
                        " ON a2.id = v2.id_articulo " +
                        " ORDER BY a2.precio DESC " +
                        " LIMIT 1) AS producto_mas_caro " +
                        "FROM ventas v " +
                        "INNER JOIN articulo a " +
                        "ON a.id = v.id_articulo";



        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {

                double total = rs.getDouble("total_vendido");
                double promedio =
                        rs.getDouble("promedio_por_producto");
                double ventaAlta = rs.getDouble("venta_mas_alta");
                int ventasAltas = rs.getInt("ventas_mayores_500");
                String producto =
                        rs.getString("producto_mas_caro");

                System.out.println("\n===== RESUMEN DE VENTAS =====");
                System.out.printf("Total vendido: $%,.2f%n", total);
                System.out.printf("Promedio por producto: $%,.2f%n",
                        promedio);

                if (producto != null) {
                    System.out.printf(
                            "Venta más alta: %s ($%,.2f)%n",
                            producto, ventaAlta
                    );
                } else {
                    System.out.println("Venta más alta: Sin ventas");
                }

                System.out.println(
                        "Ventas de $500 o más: " + ventasAltas
                );
            }

        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Código SQL: " + e.getErrorCode());
            System.err.println("Estado SQL: " + e.getSQLState());
            e.printStackTrace();
        }
        return false;
    }


}
