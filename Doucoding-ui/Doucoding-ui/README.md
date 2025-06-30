# Angular 19 - Aplicación de Gestión de Usuarios

Este proyecto es una aplicación Angular 19 funcional que incluye login simulado, listado de usuarios y estructura modular con datos mock.

## 🚀 Requisitos

- Node.js 18 o superior
- Angular CLI 19

## 🛠 Instalación

1. Asegúrate de tener Angular CLI instalado:
   ```
   npm install -g @angular/cli@19
   ```

2. Crea un nuevo proyecto Angular (si no lo has hecho):
   ```
   ng new usuarios-app
   cd usuarios-app
   ```

3. Copia los archivos de este paquete dentro del proyecto:
   - Reemplaza los archivos `app.module.ts`, `app-routing.module.ts`, etc.
   - Coloca `users.json` en `src/assets/mocks/`

4. Instala dependencias:
   ```
   npm install
   ```

## ▶️ Ejecución

```
ng serve
```

Abre tu navegador en:
```
http://localhost:4200
```

## 📦 Estructura incluida

- `app.module.ts`: módulo principal de Angular
- `app-routing.module.ts`: rutas del login y lista de usuarios
- `LoginComponent`: login funcional con almacenamiento en localStorage
- `UserListComponent`: tabla con datos mock
- `PopupComponent`: estructura básica para crear/editar usuarios
- `user.service.ts`: servicio que carga usuarios desde JSON
- `user.model.ts`: interfaz del usuario
- `users.json`: mock de usuarios

---

💡 Este proyecto puede ser ampliado con validaciones, popup dinámico, CRUD completo, y conexión real con backend (Spring Boot).
