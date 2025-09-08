package macropriaxis.db;

public class SesionActual {
    private static Usuario usuarioLogueado;

    public static void setUsuario(Usuario usuario) {
        usuarioLogueado = usuario;
    }

    public static Usuario getUsuario() {
        return usuarioLogueado;
    }
} 