package com.luis.luis_books.model;

//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @JacksonXmlRootElement // Con esta anotación el objeto se devolvera como XML.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    private int idBook;
    @Column(name = "book_title", length = 50, nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name="id_category",  nullable = false)
    private Category category;
    @Column(nullable = false)
    private boolean enabled;

}
