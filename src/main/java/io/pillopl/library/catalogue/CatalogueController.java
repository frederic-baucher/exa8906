package io.pillopl.library.catalogue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@EnableAutoConfiguration
@ComponentScan
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
public class CatalogueController {

	private final Catalogue catalogue;

	// https://gayerie.dev/docs/spring/spring/spring_mvc_apiweb.html
    @GetMapping(path="/books/{bookIsdn}", produces= "application/json")
    public Book getBook() {
        Book book = new Book("9780321831", "Implementing DDD", "Vaughn Vernon");
        return book;
    }

    @PostMapping("/books")
	ResponseEntity<Book> addBook(@RequestBody Book book) {
		ResponseEntity<Book> response = null ;
		log.info ( "Book added" ) ;
		// catalogue.addBook("Joshua Bloch", "Effective Java", "0321125215").get();
		// ResponseEntity.ok( catalogue.addBook(book.getAuthor().toString(), book.getTitle().toString(), book.getBookIsbn().toString()) ) ;
		ResponseEntity.ok( catalogue.addBook( book.getAuthor(), book.getTitle(), book.getBookIsbn() ) ) ;
		return response ;
	}


	@PostMapping("/books/{bookIsbn}/instances")
	ResponseEntity<Book> addBook( @PathVariable String bookIsbn ) {
		ResponseEntity<Book> response = null ;
		log.info ( "Book instance added" ) ;

		ResponseEntity.ok( catalogue.addBookInstance( bookIsbn, BookType.Restricted) ) ;
		return response ;
	}

	
	// display all books available in database
    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> getBooks() {

        return ResponseEntity.ok(catalogue.findAll());
    }	
}