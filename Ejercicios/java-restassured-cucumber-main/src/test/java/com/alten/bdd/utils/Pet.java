package com.alten.bdd.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class Pet {

    private String name;
    private long id;
    private ArrayList<HashMap<String,String>> tags;


    /** Cada objeto Pet tiene entre sus atributos una lista de Maps. Si observamos la estructura
     * JSON de lo que devuelve la API vemos que los tags son una lista de diccionarios:
     * "tags": [
     *     {
     *       "id": 0,
     *       "name": "string"
     *     }
     * Por lo que en el constructor lo mapeamos así con el tag de cada Example en el Step */
    public Pet(String name,String tags) {
        HashMap<String, String> tags_map = new HashMap<>();
        tags_map.put("name",tags);
        ArrayList<HashMap<String,String>> tags_list = new ArrayList<>();
        tags_list.add(tags_map);
        this.tags = tags_list;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** Como se nos pide que eliminemos los tags en los steps del PUT, he creado el método removeTags.
     * El id lo ponemos a 0, pero podríamos no tocarlo porque automáticamente es 0 ya que en el constructor no lo indicamos
     * y la API lo pone siempre. El tag "name" lo dejamos en "string", que es el valor que la API crea por defecto.
     * El tag id no podemos eliminarlo por que se automatiza en la api para que siempre esté, pero el del name sí. Aunque aquí, en
     * lugar de eliminarlo lo ponemos al valor por defecto en la API, podemos eliminarlo perfectamente con .remove("name"). Sólo habría
     * que hacer algunas confirmaciones más en en método .equals() para poder implementarlo con .remove(), aunque personalmente he
     * decidido dejarlo en su valor por defecto porque como la API lo crea automáticamente lo he visto más natural. */

    public void removeTags() {
        tags.get(0).put("name","string");
        tags.get(0).put("id","0");
    }

    public long getId() {
        return id;
    }

    /**Para conseguir el valor del tag "name" de cada Pet tenemos que llegar a la posicion
     0 de la lista de tags con tags.get(0), que se corresponde con el map, es decir, con cada
     tag individual. Ya después comparamos. Lo mismo para el tag id. Aunque no lo creemos o especifiquemos por valor,
    la API lo añade automáticamente, por eso lo comparamos también.*/
    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pet pet = (Pet) obj;
        boolean equalsNameTag = tags.get(0).get("name").equals(pet.tags.get(0).get("name"));
        boolean equalsIdTag = tags.get(0).get("id").equals(pet.tags.get(0).get("id"));
        return equalsNameTag && equalsIdTag;
    }

}
