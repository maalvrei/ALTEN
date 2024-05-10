# IMDB PROJECT Ejercicio Final

Es un ejercicio para hacerlo en Grupo en donde se creará una Rama llamada Grupo I ( por ejemplo) y cada miembro del equipo tendrá que realizar una prueba.

## Forma de evaluar

- Las pruebas están ya escritas en formato BDD y tiene un Doc con el paso a paso. 
- Cada miembro del equipo seleccionará una prueba y la desarrollará. 
- Se evaluará el trabajo en grupo, si han sabido reutilizar los pageObject comunes o partes comunes correctamente, etc.



## Pruebas a desarrollar

Las pruebas a desarrollar serán ( features: src/test/resources/cucumber/ y docs en /doc)


| test id | feature| doc |
| ------ | ------ | ------|
| test1 | imdb_actor_search.feature | imdb_filmography.docx  |
| test2 | imdb_film_page.feature | pics_count.docx  |
| test3 | imdb_film_search.feature | IMDB_film_search.docx  |
| test4 | imdb_oscar.feature | imdb_male_oscar.docx |

### Forma de entregarlo

La forma de entregarlo será realizando una rama GRUPO_I_usuario_id del test a la rama GRUPO_I ( siendo I el grupo de sus practicas por ejemplo.)

ejemplo de rama:

GRUPO_I_usuariogitlab_test1

De esa rama se hace un merge request a Grupo_I de manera que cuando todos los miembros del equipo lo han hecho se evaluará.

_____________________________________


## Run All tests

`mvn clean test`

##  Reporting

execution reports: target/cucumber-reports/report.html
