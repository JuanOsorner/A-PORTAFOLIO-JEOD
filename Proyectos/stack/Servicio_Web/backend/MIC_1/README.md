## ServicioWeb - Java

### Introducción

Este servicio de backend que he creado es inicialmente diseñado para practicar
un diseño de arquitectura basado en bloques modulares. La idea es dividir para
vencer para que el monolito sea escalable y pueda consumir servicios y ser
consumido por otros servicios. Este proyecto está dividido en seis bloques cada uno
con su respectiva responsabilidad, y claramente con un patron de configuración basado
en Maven que contiene: Java (Nuestra app), Resources (Donde manejamos la configuración
a la base de datos), y webapp (Donde tenemos la configuración de seguridad de la API)

#### Nota: No vamos a usar Frameworks por el momento, pues el desarrollo de este proyecto es meramente para entender porque funciona lo que funciona en un servidor backend java 

### Dependencias

Para este proyecto vamos a utilizar las siguientes dependencias(Ir a pom.xml):

Para conectarson a MySql por medio de XAMPP (Desarrollo) vamos a utilizar la siguiente dependencia:

    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.3.0</version>
    </dependency>

Para ahorrarnos escribir mucho codigo repetitivo en los bloques de datos y transferencia
(Getters y setters) vamos a utilizar 

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
        <scope>provided</scope>
    </dependency>

Para manejar las peticiones y respuestas HTTP como proteger la API (Middlewares) vamos a utilizar:

    <dependency>
        <groupId>jakarta.servlet</groupId>
        <artifactId>jakarta.servlet-api</artifactId>
        <version>6.0.0</version>
        <scope>provided</scope>
    </dependency>

Para procesar Json

    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.17.0</version>
    </dependency>

### Bloque de datos 

Este bloque tiene la unica responsabilidad de comunicarse con la base de
datos. Mi idea es dividir esto es dos bloques:

#### 1. Bloque de modelos.

Este bloque es el encargado de manejar la integridad de los datos de la base de datos.
Aquí construimos como se ve un usuario en la base de datos.

Por ejemplo: 

    package data.models;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ModeloUsuario {

    private Long id;
    private String name;
    private String email;
    private String password;
    }

#### NOTA: Vease la aplicación de la dependencia lombok

##### 2. Bloque de repositorios

Este bloque es nuestro inventario de querys, por ejemplo si se quiere crear una consulta complicada
a la base de datos que va a ser usada en el bloque de comandos se hace aquí para separar la logica de datos
con la logica de negocio.

Por ejemplo: 

    @Override
    public void guardar(ModeloUsuario user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
        }
    }

Sobrescribimos una interface y creamos un metodo de guardar usuario basico (Mas adelante en cuanto
como crezca la base de datos y el proyecto podriamos usar nuevamente el metodo guardar con una consulta
más compleja)

### Bloque de transferencia de datos