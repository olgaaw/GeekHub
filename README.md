# GeekHub

<div align="center">
  <img src="https://github.com/user-attachments/assets/adf8cba2-af5f-486f-9eea-7dcdf1e07e43" alt="ghicon" width="150"/>
  <img src="https://github.com/user-attachments/assets/b068c847-b084-4b50-9c1c-ba061a1db55a" alt="ghlogo" width="400"/>
</div>

---

## 📌 Descripción

**GeekHub** es una red social web desarrollada con Spring Boot 3 y Angular 18, orientada a personas que comparten intereses relacionados con la cultura geek: videojuegos, anime, ciencia ficción, tecnología, cómics, etc. Permite a los usuarios crear publicaciones, seguir perfiles afines y compartir contenido dentro de una comunidad con gustos similares.

### 🎯 Funcionalidades principales

- **Login**: Iniciar sesión con cuenta existente o ir al registro.
- **Registro**: Formulario en dos pasos para crear una cuenta (credenciales y datos personales).
- **Activación de cuenta**: Validación por código enviado por email tras el registro.
- **Perfil de usuario**: Visualización y edición de datos personales y publicaciones.
- **Publicaciones**: Crear publicaciones con texto o imágenes desde la pantalla home.
- **Home**: Muestra publicaciones recientes de los perfiles que sigues.
- **Buscador avanzado**: Filtrar usuarios por intereses, ubicación, edad o género.
- **Gestión de intereses**: Como administrador puedes crear, editar y eliminar intereses.

---

## 🎨 Prototipos en Figma

- 🖥️ [Diseño Web (Figma)](https://www.figma.com/proto/A1Wx1f1BrY8gbem5pOfPvB/GeekHub-web?t=h3ESz7kHFjbndur6-1)  
- 📱 [Diseño Móvil (Figma)](https://www.figma.com/proto/OcFYE6LZ5E8aH8oqUtctge/GeekHub?node-id=0-1&t=h3ESz7kHFjbndur6-1)

---

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 17 + Spring Boot 3**
- **Spring Data JPA** – ORM con Hibernate
- **PostgreSQL** – Base de datos principal
- **Spring Security** – Autenticación y autorización
- **SendGrid** – Envío de correos electrónicos
- **Apache Tika** – Análisis de archivos subidos
- **Swagger / OpenAPI** – Documentación de la API
- **H2 Database** – Base de datos
- **Docker + Docker Compose** – Servicios

### Frontend
- **Angular 18**
- **Bootstrap 5** 
- **Angular Material**

---

## ⚙️ Ejecución del Proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/olgaaw/GeekHub
```
### 2. Compilar y levantar servicios
```bash
mvn clean install
docker-compose up -d --build
```
Esto iniciará los contenedores necesarios en Docker para la base de datos, backend y frontend.

### 3. Acceso a la base de datos 
  -  **PostgreSQL**
     * Imagen: postgres:16-alpine
     * Usuario: geekhub
     * Contraseña: 12345678
     * Nombre del contenedor: postgresql-spring-geekhub
     * Puerto mapeado: 5433 (local) → 5432 (contenedor)
  
- **pgAdmin** 
   * Imagen: dpage/pgadmin4
   * Nombre del contenedor: pgadmin-spring-geekhub
   * Correo de acceso: admin@admin.com
   * Contraseña: 1
   * Puerto mapeado: 5050 (local) → 80 (contenedor)

     
### 4. Acceso a la aplicación
* 🌐 Aplicación web: http://localhost:80

* 📘 Documentación de la API: http://localhost:8080/swagger-ui.html

Se adjunta una colección de postman para ir probando los diferentes endpoints de la API REST.

## 🔑 Usuarios principales para las pruebas
- **Role_ADMIN:**
    -  Usuario: admin
    -  Cotraseña: admin
- **Role_USER:**
    -  Usuario: user
    -  Cotraseña: 1234

## Dependencias
- En SQL (H2 Database y Spring Data JPA)
- Lombook
- Spring web
- Postgresql
- Sendgrid
- Tika
- Spring Security


