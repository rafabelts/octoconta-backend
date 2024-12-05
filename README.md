# Guía de instalacion
1.	Git clone https://github.com/rafabelts/octoconta-backend
2.	Ejecutar el archivo start-database.sh (se tiene que tener docker activado)
3.	En application.properties:
    a.	Cambiar la contraseña a la generada al ejecutar el archivo start-database.sh (spring.datasource.password)
4.	Ejecutar ./gradlew bootRun

