# Code test Rick Morty
Este es un proyecto que se ha desarrollado como ejercicio técnico. En ella se solicita la creación de dos pantallas:
 - Pantalla en forma de listado de personajes.
 - Pantalla para ver la última localización conocida de un personaje.
 - Diseñar una interfaz propia sin limitaciones.
Además, como añadidos se recomienda:
 - Setear un personaje como favorito.
 - Listado de personajes paginados (listado con scroll infinito)
 - Test unitarios y test de interfaz.
  
Los datos a mostrar se obtendrían de la API https://rickandmortyapi.com

 ## Tecnologías usadas
 Desarrollada la prueba en lenguaje Kotlin. Las librerías usadas aparte de las de androidx como Paging 3 son:

#### Consumo de apis
 - Gson
 - Retrofit
 - Okhttp
#### Injeccion de dependencias
 - Dagger2
#### Testing
 - Mockito
#### Debug
 - Chucker para mostrar los logs de las peticiones HTTP.
 - LeakCanary para prevenir memory leaks.
#### Otras
  - Glide para mostrar las imágenes.

 Además de kotlin se han usado las librerías para hacer uso de las coroutinas con flow.

 ## Arquitectura
 Se ha decidido realizar la arquitectura MVVM ya que con esta arquitectura se puede testear muy bien la aplicación. Para ello se han organizado las carpetas divididas en una carpeta de common(se utiliza para guardar todo lo que es común para todas las capas) y luego una carpeta para cada capa (data, domain, presentation). Dentro de cada capa se divide en features, de esta manera hace más fácil la escalabilidad si en un futuro se quisiese ampliar las funcionalidades. 

  ## Testing
Se ha desarrolla testing de la capa de datos, dominio y presentacion, distribuidas por carpetas igual que lo comentado en el punto anterior.

Por falta de tiempo no se ha podido realizar una cobertura de test acorde para cubrir todos los casos posibles de cada apartado, aun así, se ha desarrollado diferentes test para mostrar el testing en diferentes capas de la arquitectura.

  ## Mejoras
Como mejoras incluiría:
 - Cacheo de la información en una base de datos sqlite a través de Room, con esta arquitecutura sería fácil añadirlo.
 - Añadir más testing unitario para cubrir todos los posibles errores.
 - Añadir más funcionalidades, por ejemplo, añadir filtrado en el listado de personajes.
 - Añadir UI testing.
 - Añadir workflow de CI para lanzar la batida de test en cada PR de cualquier branch hacia develop o hacia master.

  ## Comentarios
El desarrollo se ha centrado en gran parte en la creación de una arquitectura que sea muy escalable y fácil para agregar nuevas funcionalidades.
No se hace un control exhaustivo de los diferentes tipos de errores que pueda ocasionar la API porque no están listados en la misma, aunque está el sistema está preparado para añadir de forma fácil los posibles errores una vez que se especificasen.