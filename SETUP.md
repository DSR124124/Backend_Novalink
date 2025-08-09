# 🔧 Configuración del Entorno - Backend NovaLink

## 🚨 Configuración de Seguridad (IMPORTANTE)

### 1. Variables de Entorno

**⚠️ NUNCA commitear credenciales reales al repositorio**

1. Copiar el archivo de ejemplo:
   ```bash
   cp .env.example .env
   ```

2. Editar `.env` con tus credenciales reales:
   ```bash
   # Ejemplo de configuración local
   SPRING_PROFILES_ACTIVE=dev
   DATABASE_PASSWORD=tu_password_real
   JWT_SECRET=tu_clave_jwt_segura_de_256_bits
   ```

### 2. Generación de JWT Secret

**Generar una clave JWT segura:**

```bash
# Opción 1: OpenSSL
openssl rand -hex 64

# Opción 2: Online (usar solo para desarrollo)
# https://generate-secret.vercel.app/64
```

### 3. Configuración por Entornos

#### 🟢 Desarrollo (`dev`)
```bash
export SPRING_PROFILES_ACTIVE=dev
export DATABASE_PASSWORD=dev_password
export JWT_SECRET=tu_clave_dev
```

#### 🟡 Testing (`test`)
```bash
export SPRING_PROFILES_ACTIVE=test
# Tests usan H2 en memoria, no necesita configuración adicional
```

#### 🔴 Producción (`prod`)
```bash
export SPRING_PROFILES_ACTIVE=prod
export DATABASE_URL=jdbc:postgresql://servidor-prod:5432/BdNovalink
export DATABASE_USERNAME=novalink_user
export DATABASE_PASSWORD=password_super_seguro
export JWT_SECRET=clave_jwt_produccion_ultra_segura
export JPA_DDL_AUTO=validate
```

## 🗄️ Configuración de Base de Datos

### PostgreSQL Local

1. Instalar PostgreSQL
2. Crear base de datos:
   ```sql
   CREATE DATABASE BdNovalink;
   CREATE USER novalink_user WITH PASSWORD 'tu_password';
   GRANT ALL PRIVILEGES ON DATABASE BdNovalink TO novalink_user;
   ```

3. Configurar variables:
   ```bash
   DATABASE_URL=jdbc:postgresql://localhost:5432/BdNovalink
   DATABASE_USERNAME=novalink_user
   DATABASE_PASSWORD=tu_password
   ```

## 🚀 Ejecución

### Desarrollo
```bash
# Con variables de entorno
export SPRING_PROFILES_ACTIVE=dev
mvn spring-boot:run

# O con archivo .env (requiere plugin)
mvn spring-boot:run
```

### Testing
```bash
mvn test -Dspring.profiles.active=test
```

### Producción
```bash
# Compilar
mvn clean package

# Ejecutar con variables de entorno
export SPRING_PROFILES_ACTIVE=prod
java -jar target/Backend_NovaLink-0.0.1-SNAPSHOT.jar
```

## 🔐 Checklist de Seguridad

- [ ] `.env` está en `.gitignore`
- [ ] Credenciales reales NO están en el código
- [ ] JWT secret tiene al menos 256 bits
- [ ] Perfiles de entorno configurados correctamente
- [ ] Base de datos de producción usa credenciales seguras
- [ ] DDL auto está en `validate` para producción

## 🛠️ Variables de Entorno Requeridas

| Variable | Descripción | Ejemplo | Requerido |
|----------|-------------|---------|-----------|
| `SPRING_PROFILES_ACTIVE` | Perfil activo | `dev`, `test`, `prod` | Sí |
| `DATABASE_URL` | URL de base de datos | `jdbc:postgresql://...` | Sí |
| `DATABASE_USERNAME` | Usuario de BD | `postgres` | Sí |
| `DATABASE_PASSWORD` | Contraseña de BD | `password123` | Sí |
| `JWT_SECRET` | Clave JWT | `clave-256-bits` | Sí |
| `SERVER_PORT` | Puerto del servidor | `8080` | No |
| `JPA_DDL_AUTO` | Estrategia DDL | `update`, `validate` | No |

## ❌ Lo que NO hacer

- ❌ Commitear archivos `.env`
- ❌ Hardcodear passwords en el código
- ❌ Usar `ddl-auto=create` en producción
- ❌ Exponer JWT secrets
- ❌ Usar contraseñas débiles
