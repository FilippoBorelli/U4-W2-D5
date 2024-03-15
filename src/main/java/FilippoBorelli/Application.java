package FilippoBorelli;

import com.github.javafaker.Faker;
import FilippoBorelli.entities.Libro;
import FilippoBorelli.entities.Periodicita;
import FilippoBorelli.entities.Rivista;
import FilippoBorelli.entities.Articolo;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        Supplier<Libro> libroSupplier = () -> {
            Faker faker = new Faker(Locale.ITALY);
            int isbn = faker.random().nextInt(1, 500000);
            String isbnString = Integer.toString(isbn);
            String titolo = faker.book().title();
            int anno = faker.random().nextInt(1900, 2023);
            int numeroPagine = faker.random().nextInt(10, 700);
            String autore = faker.book().author();
            String genere = faker.book().genre();
            return new Libro(isbnString, titolo, anno, numeroPagine, autore, genere);
        };
        Supplier<Rivista> rivistaSupplier = () -> {
            Faker faker = new Faker(Locale.ITALY);
            int isbn = faker.random().nextInt(1, 500000);
            String isbnString = Integer.toString(isbn);
            String titolo = faker.book().title();
            int anno = faker.random().nextInt(1900, 2023);
            int numeroPagine = faker.random().nextInt(10, 700);
            return new Rivista(isbnString, titolo, anno, numeroPagine, Periodicita.SETTIMANALE);
        };
        Map<String, Articolo> archivio = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Rivista rivista = rivistaSupplier.get();
            Libro libro = libroSupplier.get();
            archivio.put(rivista.getCodiceIsbn(), rivista);
            archivio.put(libro.getCodiceIsbn(), libro);
        }
        System.out.println("---------------------------ARCHIVIO----------------------");
        archivio.forEach((key, value) -> System.out.println(key +  ", " + value));

    }
}