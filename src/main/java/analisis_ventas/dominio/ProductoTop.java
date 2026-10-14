package analisis_ventas.dominio;

public class ProductoTop {

    private String producto;
    private int cantidadVendida;
    private int puesto;

    public ProductoTop(String producto, int cantidadVendida, int puesto) {
        this.producto = producto;
        this.cantidadVendida = cantidadVendida;
        this.puesto = puesto;
    }

    public ProductoTop() {
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public int getPuesto() {
        return puesto;
    }

    @Override
    public String toString() {
        return "Puesto: " + puesto
                + " | Producto: " + producto
                + " | Vendidos: " + cantidadVendida;
    }
}
