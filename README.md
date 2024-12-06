# Guía de instalacion

1. **Clonar el repositorio:**
<br/>
Se tiene que tener git instalado, si no se tiene, se puede descargar desde https://git-scm.com. Después se abre la terminal y
   se ejecuta:
```bash
git clone https://github.com/rafabelts/octoconta-backend
```
2. **Acceder al directorio del proyecto:**
<br/>
Una vez clonado el repositorio, se tiene que navegar al directorio del proyecto con el siguiente comando:

```bash
   cd octoconta-backend
```

3. **Ejecutar el archivo start-database.sh:**
<br/>
Este paso requiere Docker activado en el equipo. Si no se tiene, puedes descargarlo desde https://www.docker.com. Una vez Docker esté listo, ejecute el siguiente comando:

```bash
   ./start-database.sh
```
> Nota: Si estás en Windows y no tienes un entorno Bash, puedes usar Git Bash o WSL (Windows Subsystem for Linux Alternativamente, puedes ejecutar el script dentro de un contenedor de Docker si tu entorno no permite ejecutar scripts .sh directamente.
<br/>


4. **Configura la base de datos en application.properties:**
<br/>
Después de ejecutar start-database.sh, se generará una nueva contraseña para la base de datos. Debes actualizar esta contraseña en el archivo application.properties.
   - Abre src/main/resources/application.properties
   - Busca la línea: 
   <br/>
    ```spring.datasource.password=contraseña_generada_aqui```
   
   - Cambia el valor de contraseña_generada_aqui por la contraseña generada al ejecutar el script

5. **Ejecutar el proyecto:**
<br/>
Ahora, para arrancar el backend del proyecto, ejecuta el siguiente comando:
- En Windows (con PowerShell o Símbolo del sistema) o en otros sistemas, usa:
```bash
   ./gradlew bootRun
```

>  Nota para Windows: Si en PowerShell no puedes ejecutar ./gradlew, puede que necesites usar el comando: gradlew bootRun

6. **Verificar la ejecución:**
<br/>
Si todo está correctamente configurado, la aplicación debería arrancar sin problemas. Puedes verificar que está funcionando accediendo a la URL http://localhost:8080/api. La cual debe de mostrar lo siguiente:

![ejecucion](https://utfs.io/f/rNGOFT54IOxLYVt3zayqPi3ocMDfrlV2p8eTvaFOI9ZALxGt)