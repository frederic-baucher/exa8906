package io.pillopl.library.catalogue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.extern.slf4j.Slf4j;
import lombok.NoArgsConstructor;


@Value
@EqualsAndHashCode(of = "bookIsbn")
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@JsonCreator))
class Book {

    @NonNull
    private ISBN bookIsbn;
    @NonNull
    private Title title;
    @NonNull
    private Author author;

    Book(String isbn, String author, String title) {
        this(new ISBN(isbn), new Title(title), new Author(author));
    }
}


@Value
@Slf4j
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@JsonCreator))
// @NoArgsConstructor => https://stackoverflow.com/questions/58171839/using-lombok-requiredargsconstructor-as-jsoncreator (exa8906/issues/9)
class Title {

    @NonNull String title;

	/*
    Title(String title) {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title.trim();
    }
	*/
}

@Value
@Slf4j
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@JsonCreator))
class Author {

    @NonNull String name;

	/*
    Author(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        this.name = name.trim();
    }
	*/
}