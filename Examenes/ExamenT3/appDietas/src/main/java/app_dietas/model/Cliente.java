package app_dietas.model;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String fecha_nacimiento;
    private String telefono;
    private String email;
    private int idCentro;

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(int idCliente, String nombre, String fecha_nacimiento, String telefono, String email, int idCentro) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.email = email;
        this.idCentro = idCentro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + "'" +
                ", fecha_nacimiento='" + fecha_nacimiento + "'" +
                ", telefono='" + telefono + "'" +
                ", email='" + email + "'" +
                ", idCentro=" + idCentro +
                '}';
    }
}
