# MultipleIntents
Aplicación que permite pasar de una actividad a otra, enviando datos

Descripción
Para este desafío debes crear una nueva aplicación en Android Studio la cual permita pasar
de una actividad a otra, adjuntando algunos datos.
La primera actividad debe contener un elemento imageView con el logo de desafío latam,
un textView indicando “Sección de carga de imágenes”, otro imageView de
aproximadamente 400 x 400 dp de alto y ancho, con un padding de 30dp, además un botón
que indique “Cargar Imagen”.
Al presionar el botón se debe realizar un intent hacia la cámara del dispositivo, tomar la foto
y esta sea recibida en la actividad, siendo asignada al imageview correspondiente.
Por último debe haber un botón que indique “Siguiente” y realice otro intent con tres
parámetros hacia la segunda actividad(debe pasar dos datos), pero antes validando que se
haya cargado una imagen desde la cámara, en caso contrario no debe permitir la acción de
ir a la segunda actividad.
El primer dato del parámetro a pasar en el intent debe ser un booleano, que indique true si se
cargó la imagen; y el segundo dato del parámetro debe ser un string cuyo valor es el de la
dirección web de Desafío Latam.
La segunda actividad debe contener un imageview con el logo de Desafío Latam, un botón
con la acción “Abrir Web” que realice un intent hacia el navegador con el parámetro recibido
desde la primera actividad. Por último crear un segundo botón “Compartir la Información”
que realice un intent de share (ACTION_SEND) con un mensaje que contenga la información
de ambos parámetros recibidos por esta actividad.

Instrucciones
1. Crear un nuevo proyecto con una actividad vacía, seleccionando lenguaje java y
versión mínima de Android API 23. Asegúrate de tener instalado un emulador de
teléfono.
2. Crear dos layouts con las vistas solicitadas, uno para la “MainActivity” y otro para la
“ResultActivity”.
3. Crear las acciones necesarias en la “MainActivity” para tomar las fotos con la
cámara, recibirlas desde el onActivityResult. Esto basado en la información que nos
brinda android para lograrlo en la siguiente página:
https://developer.android.com/training/camera/photobasics .
4. Crear un método que ejecute un intent para iniciar la segunda actividad con dos
parámetros predefinidos, el booleano que indica se carga una imagen, y el string de
valor “ http://www.desafiolatam.com ”. Recuerda validar si esta imagen existe antes
de continuar con el botón “siguiente”.
5. En la segunda actividad “ResultActivity” se deberán leer los parámetros recibidos por
el intent que la invoco, para ser guardados en variables de clase.
6. En la segunda actividad “ResultActivity” se han de crear dos nuevos métodos que
realicen acciones intent, el primero para abrir el navegador con la url recibida por
parámetro, y el segundo para realizar un intent de compartir el mensaje con el String
de la URL.
7. En la segunda actividad “ResultActivity” crear el método onActivityResult y mostrar
un mensaje Toast, dependiendo de la acción ejecutada validando su requestCode, ya
sea abrir navegador o compartir la información.
