package app_dietas.model;

public class Centro {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String horario;

    public Centro() {
    }

    public Centro(int id) {
        this.id = id;
    }

    public Centro(String nombre, String direccion, String telefono, String email, String horario) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.horario = horario;
    }

    public Centro(int id, String nombre, String direccion, String telefono, String email, String horario) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getHorario() {
        return horario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Centro{" +
                "id=" + id +
                ", nombre='" + nombre + "'" +
                ", direccion='" + direccion + "'" +
                ", telefono='" + telefono + "'" +
                ", email='" + email + "'" +
                ", horario='" + horario + "'" +
                '}';
    }
}
