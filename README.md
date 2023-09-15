# PROYECTO FINAL RestAssured

##Santiago Becerra Almancy (64647)

###Acerca del proyecto
----
El proyecto fue realizado en Intellij para la ejecución automatizada de tests para el siguiente API: [RestAssured](https://dummy.restapiexample.com/). Se realizó un total de 9 Tests Cases para el API.

###Requisitos previos
----
- Descargar Intellij IDEA: recomendablemente descargar la versión "Community Edition" del siguiente link: INTELLIJ Link
- JDK: IntelliJ IDEA es una herramienta de desarrollo Java, por lo que necesita tener instalado un JDK en su sistema. Puede usar Oracle JDK, OpenJDK u otros JDK compatibles. El proyecto utiliza la versión "Oracle OpenJDK Version 20.0.2".

###Instrucciones de ejecución
----
1. Descargar el proyecto del repositorio
2. Abrir el proyecto con Intellij IDEA
3. Revisar que el JDK sea el correcto, el proyecto utiliza: "Oracle OpenJDK Version 20.0.2"
4. Abrir dentro de Intellij la carpeta: src > test > java > utils
5. Entrar al archivo TestRunner y hacer click derecho y seleccionar la opción "Run TestRunner".
6. El resultado de la ejecución se verá en la consola de la parte inferior, donde se indicará los test que pasaron y aquellos que fallaron.

###Notas Adicionales
----
Si desea revisar el proyecto más a detalle considerar lo siguiente:
- La carpeta src > test > resources: contiene todos los testcases automatizados, se definen estos mediante pasos.
- La carpeta src > test > java > utils: contiene la definición de los enpoints, las operaciones CRUD.
- La carpeta src > test > java > entities: contiene la definición para el objeto employee, funciona para manipular la estructura de los empleados en el API.
- La carpeta src > test > java > constants: contiene la definición de los endpoints utilizados.
- La carpeta src > test > java > stepDefinitions: contiene todos los pasos definidos para la carpeta resources, la implementación de los pasos.