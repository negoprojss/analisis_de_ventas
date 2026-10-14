package analisis_ventas.dominio;

import java.util.Objects;

public class Venta {
    private int id;
    private int id_articulo;

    public Venta() {
    }

    public Venta(int id, int id_articulo) {
        this.id = id;
        this.id_articulo = id_articulo;
    }

    public Venta(int id_articulo) {
        this.id_articulo = id_articulo;
    }


    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", id_articulo='" + id_articulo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return id == venta.id && Objects.equals(id_articulo, venta.id_articulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_articulo);
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
