public class JugadorNoEncontradoException extends RuntimeException {
    public JugadorNoEncontradoException(String message) {
        super(message);
    }

    public JugadorNoEncontradoException(int id) {
        super("No se encontro al jugador" + id);
    }

}
