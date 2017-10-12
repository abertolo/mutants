#Mutantes

## Local

Instrucciones para correr la aplicación localmente

#### Requisitos:

- Maven (v3.3.9)
- Mysql (v5.7.19)
- Java 8

####Realizar los siguientes pasos (situado en el directorio root del proyecto):

1. sudo mysql --password
2. Crear la base de datos: create database db_mutants
3. Crear usuario: create user 'user'@'localhost' identified by 'password'
4. Darle privilegios al usuario recien creado: grant all on db_mutants.* to 'user'@'localhost'
5. quit
6. mvn package
7. cd target
8. java -jar mutants-0.0.1-SNAPSHOT.jar

## AWS

Levante una instancia EC2 de AWS. Para consumir la API utilizar el siguiente dominio:
- ec2-18-231-88-39.sa-east-1.compute.amazonaws.com

Ejemplo:

1.POST: http://ec2-18-231-88-39.sa-east-1.compute.amazonaws.com:8080/mutants pasándole el siguiente json:

{
   "dna":[
      "ATGCGG",
      "CAGTGC",
      "TTCTGT",
      "AGAAGG",
      "CCCCTA",
      "TCACTG"
   ]
}

2.GET: http://ec2-18-231-88-39.sa-east-1.compute.amazonaws.com:8080/stats

