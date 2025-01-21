# Desafío Foro API REST

![GitHub repo size](https://img.shields.io/github/repo-size/xNitox/Desafio-ApResti-Foro)
![GitHub contributors](https://img.shields.io/github/contributors/xNitox/Desafio-ApResti-Foro)
![GitHub stars](https://img.shields.io/github/stars/xNitox/Desafio-ApResti-Foro?style=social)
![GitHub forks](https://img.shields.io/github/forks/xNitox/Desafio-ApResti-Foro?style=social)
![GitHub issues](https://img.shields.io/github/issues/xNitox/Desafio-ApResti-Foro)


## **Descripción del Proyecto**

El Desafío Foro API REST es un proyecto backend desarrollado como parte de un desafío propuesto por Alura. Este sistema permite gestionar un foro con las funcionalidades básicas de creación, lectura, actualización y eliminación de tópicos, implementando autenticación con JWT para garantizar la seguridad de la aplicación.

---

## **Funcionalidades**

- **Listado de tópicos:** Obtén todos los tópicos creados por los usuarios registrados.
- **Creación de tópicos:** Los usuarios autenticados pueden crear nuevos tópicos.
- **Eliminación de tópicos:** Permite borrar tópicos específicos mediante su ID.
- **Actualización de tópicos:** Modifica el contenido de un tópico existente.
- **Autenticación:** Usa tokens JWT para validar y proteger las operaciones del foro.

---

## **Tecnologías Utilizadas**

- **Java:** Lenguaje principal para el desarrollo del backend.
- **Spring Boot:** Framework para simplificar la creación de APIs RESTful.
- **JWT (JSON Web Token):** Para la autenticación y autorización.
- **Base de Datos:** Puedes usar H2, MySQL u otra base de datos de tu preferencia.

---

## **Instalación**

Sigue estos pasos para ejecutar el proyecto localmente:

1. Clona el repositorio:
   ```sh
   git clone https://github.com/xNitox/Desafio-ApResti-Foro.git
   ```

2. Navega al directorio del proyecto:
   ```sh
   cd Desafio-ApResti-Foro
   ```

3. Configura las propiedades del proyecto en el archivo `application.properties`, incluyendo la conexión a la base de datos y el `api.security.secret`.

4. Ejecuta el proyecto:
   ```sh
   ./mvnw spring-boot:run
   ```

---

## **Endpoints Principales**

- **GET /topicos:** Lista todos los tópicos.
- **POST /login:** Genera un token JWT para autenticarte.
- **POST /topicos:** Crea un nuevo tópico (requiere token).
- **PUT /topicos/{id}:** Actualiza un tópico existente (requiere token).
- **DELETE /topicos/{id}:** Elimina un tópico por su ID (requiere token).

---

## **Estructura del Proyecto**

- **TokenService:** Gestiona la creación y validación de JWT.
- **Controladores:** Manejan las peticiones HTTP.
- **Repositorios:** Acceso a la base de datos.
- **Configuraciones de Seguridad:** Implementadas con Spring Security.

---

## **Contacto**

- 📧 nibaldoji306@gmail.com
- 🌐 [Mi LinkedIn](https://www.linkedin.com/)
