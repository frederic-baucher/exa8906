package io.pillopl.library.catalogue;

import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
// import java.util.Collection ;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
// import org.springframework.jdbc.core.RowMapper;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class CatalogueDatabase {

    private final JdbcTemplate jdbcTemplate;

    Book saveNew(Book book) {
        jdbcTemplate.update("" +
                        "INSERT INTO catalogue_book " +
                        "(id, isbn, title, author) VALUES " +
                        "(catalogue_book_seq.nextval, ?, ?, ?)",
                book.getBookIsbn().getIsbn(), book.getTitle().getTitle(), book.getAuthor().getName());
        return book;
    }

    BookInstance saveNew(BookInstance bookInstance) {
        jdbcTemplate.update("" +
                        "INSERT INTO catalogue_book_instance " +
                        "(id, isbn, book_id) VALUES " +
                        "(catalogue_book_instance_seq.nextval, ?, ?)",
                bookInstance.getBookIsbn().getIsbn(), bookInstance.getBookId().getBookId());
        return bookInstance;
    }

    Option<Book> findBy(ISBN isbn) {
        try {
            return Option.of(
                    jdbcTemplate.queryForObject(
                            "SELECT b.* FROM catalogue_book b WHERE b.isbn = ?",
                            new BeanPropertyRowMapper<>(BookDatabaseRow.class), // BookDatabaseRow.class instead of Book.class to have toBook() function defined.
                            isbn.getIsbn())
                            .toBook());
        } catch (EmptyResultDataAccessException e) {
            return Option.none();

        }
    }
	
	// https://apero-tech.fr/spring-jdbctemplate-mapper-lignes-vers-des-objets/
	// https://www.java67.com/2015/01/java-8-map-function-examples.html
	// https://stackoverflow.com/questions/56499565/how-to-use-findall-on-crudrepository-returning-a-list-instead-of-iterable
	List<Book> findAll () {
		String query = "SELECT b.* FROM catalogue_book b" ;
		List<BookDatabaseRow> bookDatabaseRows = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(BookDatabaseRow.class));
		List<Book> books = bookDatabaseRows.stream() //
											.map( BookDatabaseRow::toBook ) //
											.collect(toList());
		return books ;
	}
}

@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class BookDatabaseRow {
    String isbn;
    String author;
    String title;

    Book toBook() {
        return new Book(isbn, author, title);
    }
}
