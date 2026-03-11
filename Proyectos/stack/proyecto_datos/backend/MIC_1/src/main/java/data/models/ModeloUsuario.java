package data.entities;

// Hacemos uso de la dependencia lombok para no escribir getters y setters
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloUsuario {
    // Los datos que va a llevar un usuario normal
    private Long id;
    private String name;
    private String email;
    private String password;
}