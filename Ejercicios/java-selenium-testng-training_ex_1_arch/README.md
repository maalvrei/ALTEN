# IMDB PROJECT - Ejercicio 1

IMDB Project Testing is a project to training on QA Automation using the following:
	Java
	Selenium
_____________________________________

## Condicionantes:

1. Los datos URL, email y username deben ser leidos de un fichero *.properties
2. Los datos del password y del tipo de ejecución deben ser pasados por variables de entorno, estas variables son:
- IMDB_PASS
- EXEC_TYPE : La variable EXEC_TYPE tiene que tener el valor local o auto, para local el driver será local y para auto, 
3. el driver se descargará automaticamente de la siguiente librería (https://github.com/bonigarcia/webdrivermanager)


## Para ejecutar los tests:

`mvn clean test`

