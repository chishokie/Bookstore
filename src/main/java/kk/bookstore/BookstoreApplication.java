package kk.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import kk.bookstore.model.Book;
import kk.bookstore.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean public CommandLineRunner bookDemo(BookRepository bookRepository) {
		return (args) -> {
			log.info("save a couple of books");
			bookRepository.save(new Book("Aku Ankka", "Carl B", 1945, "951-98548-9-4", 20));
			bookRepository.save(new Book("Aku Ankka2", "Carl B", 1946, "978-951-98548-9-2", 30));

			log.info("fetch all books");
			for (Book kirja : bookRepository.findAll()) {
				log.info(kirja.toString());
			}
		};
	}
}
