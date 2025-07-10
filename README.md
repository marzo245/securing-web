# Securing Web (Spring Boot + MongoDB)

Este proyecto es una aplicación web segura construida con Spring Boot y MongoDB. Permite autenticación de usuarios almacenados en la base de datos y redirección tras login.

## Requisitos
- Java 17 o superior
- Maven
- MongoDB Atlas o local

## Instalación y ejecución

1. **Clona el repositorio y entra a la carpeta del proyecto:**
   ```
   cd securing-web
   ```

2. **Configura la conexión a MongoDB**
   Edita `src/main/resources/application.properties` con tu URI y base de datos:
   ```properties
   spring.data.mongodb.uri=mongodb+srv://<usuario>:<contraseña>@<host>/<db>
   spring.data.mongodb.database=<db>
   ```

3. **Agrega usuarios manualmente a la colección `usuarios` en MongoDB:**
   - El campo `password` debe tener el prefijo `{bcrypt}` si está hasheado, o `{noop}` si es texto plano (solo para pruebas).
   - Ejemplo:
     ```json
     {
       "username": "user1",
       "password": "{noop}password1",
       "role": "USER"
     }
     ```

4. **Ejecuta la aplicación:**
   - En Windows:
     ```
     mvnw spring-boot:run
     ```
   - O si tienes Maven instalado globalmente:
     ```
     mvn spring-boot:run
     ```

5. **Accede a la app:**
   - [http://localhost:8080/](http://localhost:8080/)
   - Login en `/login` (redirige a `/hello` tras autenticación)

## Estructura principal
- `src/main/java/com/example/securing_web/model/Model.java`: Modelo de usuario
- `src/main/java/com/example/securing_web/repository/MyRepository.java`: Repositorio MongoDB
- `src/main/java/com/example/securing_web/securingweb/WebSecurityConfig.java`: Configuración de seguridad
- `src/main/resources/templates/`: Vistas Thymeleaf (`login.html`, `hello.html`, etc.)

## Notas
- No hay registro de usuarios desde la web, solo autenticación.
- Si ves errores 404 en rutas extrañas, son peticiones automáticas del navegador y puedes ignorarlas.

---

¿Dudas? Modifica este README según tus necesidades.
