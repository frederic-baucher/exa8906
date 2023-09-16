package io.pillopl.library.catalogue;

import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
class ISBN {

    private static final String VERY_SIMPLE_ISBN_CHECK = "^\\d{9}[\\d|X]$";

    @NonNull
    String isbn;

    ISBN(String isbn) {
        if (!isbn.trim().matches(VERY_SIMPLE_ISBN_CHECK)) {
			log.info("ISBN - ctor : {}", isbn);
            throw new IllegalArgumentException("Wrong ISBN!");
        }
        this.isbn = isbn.trim();

    }
}
