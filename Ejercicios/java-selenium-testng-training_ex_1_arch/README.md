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

Con el comando `mvn clean test` en consola, o bien creando una configuración en nuestro IDE. En IntelliJ, por ejempo, vamos a Run/Debug configurations, en la parte superior derecha. Hacemos click en "Edit configurations" y haciendo click sobre "Maven" nos aparecerá a la izquierda un texto "Add new run configuration...". Clickamos y en "Name" ponemos el nombre que le queramos dar, por ejemplo, "Test Heroku". El "Working directory" por defecto será el del proyecto que tengamos abierto. Lo más importante es en "Run" colocar "clean test -DsuiteXmlFile=TestHeroku.xml". La versión de Java será también por defecto la del proyecto.

