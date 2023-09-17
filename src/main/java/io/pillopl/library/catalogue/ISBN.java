package io.pillopl.library.catalogue;

import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;

@Slf4j
@Value
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor = @__(@JsonCreator))
class ISBN {

    private static final String VERY_SIMPLE_ISBN_CHECK = "^\\d{9}[\\d|X]$";

    @NonNull
    String isbn;

	/* TO DO : recover when setup correctly @AllArgsConstructor */
	/*
    ISBN(String isbn) {
        if (!isbn.trim().matches(VERY_SIMPLE_ISBN_CHECK)) {
			log.info("ISBN - ctor : {}", isbn);
            throw new IllegalArgumentException("Wrong ISBN!");
        }
        this.isbn = isbn.trim();
    }
	*/
}
