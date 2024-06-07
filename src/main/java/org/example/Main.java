package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) {
        //Ejercicio 1
        // lista de números del 1 al 100
        List<Integer> numbers = IntStream.rangeClosed(1, 100).boxed().toList();

        // arallelStream() para imprimir cada número y el nombre del hilo que lo procesa
        numbers.parallelStream().forEach(number -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Número: " + number + " procesado por el hilo: " + threadName);
        });

        //Ejercicio 2
        // Lista de 200 números aleatorios
        Random random = new Random();
        List<Integer> randomNumbers = random.ints(200, 1, 1001).boxed().collect(Collectors.toList());

        // Suma y el promedio usando parallelStream()
        double parallelSum = randomNumbers.parallelStream().mapToInt(Integer::intValue).sum();
        double parallelAverage = randomNumbers.parallelStream().mapToInt(Integer::intValue).average().orElse(0);

        // Suma y el promedio usando stream()
        double sequentialSum = randomNumbers.stream().mapToInt(Integer::intValue).sum();
        double sequentialAverage = randomNumbers.stream().mapToInt(Integer::intValue).average().orElse(0);

        // Comparar los resultados
        System.out.println("Resultados usando parallelStream:");
        System.out.println("Suma: " + parallelSum);
        System.out.println("Promedio: " + parallelAverage);

        System.out.println("Resultados usando stream:");
        System.out.println("Suma: " + sequentialSum);
        System.out.println("Promedio: " + sequentialAverage);

        // Verificar si los resultados son iguales
        if (parallelSum == sequentialSum && parallelAverage == sequentialAverage) {
            System.out.println("Los resultados son iguales.");
        } else {
            System.out.println("Los resultados son diferentes.");
        }


        //Ejercicio 3
        // Lista de números del 1 al 10
        List<Integer> numbers2 = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());

        // findAny() con parallelStream() para encontrar cualquier número par
        Optional<Integer> parallelFindAny = numbers2.parallelStream()
                .filter(n -> n % 2 == 0)
                .findAny();

        // findAny() con stream() para encontrar cualquier número par
        Optional<Integer> sequentialFindAny = numbers2.stream()
                .filter(n -> n % 2 == 0)
                .findAny();

        // Imprimir los resultados
        System.out.println("Resultado de findAny() con parallelStream(): " + parallelFindAny.orElse(null));
        System.out.println("Resultado de findAny() con stream(): " + sequentialFindAny.orElse(null));

        // Comparar los resultados
        if (parallelFindAny.equals(sequentialFindAny)) {
            System.out.println("Los resultados son iguales.");
        } else {
            System.out.println("Los resultados son diferentes.");
        }

        //Ejercicio 4
        // Lista de números del 1 al 50
        List<Integer> numbers3 = IntStream.rangeClosed(1, 50).boxed().collect(Collectors.toList());

        // reduce() para calcular la suma de los números
        int sum = numbers3.stream()
                .reduce(0, Integer::sum);
        System.out.println("La suma de los números del 1 al 50 es: " + sum);

        // lista de cadenas
        List<String> strings = Arrays.asList("Hola", " ", "mundo", " ", "desde", " ", "Java", "!");

        // reduce() para concatenar las cadenas en una sola cadena
        Optional<String> concatenatedString = strings.stream()
                .reduce((s1, s2) -> s1 + s2);
        concatenatedString.ifPresent(result -> System.out.println("La cadena concatenada es: " + result));


        //Ejercicio 5
        List<Persona> personas = Arrays.asList(
                new Persona("Juan", 25),
                new Persona("María", 30),
                new Persona("Pedro", 20),
                new Persona("Ana", 35)
        );

        // parallelStream() y reduce() para sumar las edades de todas las personas
        int totalEdad = personas.parallelStream()
                .map(Persona::getEdad)
                .reduce(0, Integer::sum);

        // Imprimir el resultado
        System.out.println("La suma de las edades de todas las personas es: " + totalEdad);
    }
}