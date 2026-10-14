package analisis_ventas.dominio;

import java.util.Objects;

public class Articulo {

    public int id;
    public String producto;
    public double precio;

    public Articulo() {
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", producto='" + producto + '\'' +
                ", precio=" + precio +
                '}';
    }

    public Articulo(int id, String producto, double precio) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Articulo articulo = (Articulo) o;
        return id == articulo.id && Double.compare(precio, articulo.precio) == 0 && Objects.equals(producto, articulo.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producto, precio);
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
