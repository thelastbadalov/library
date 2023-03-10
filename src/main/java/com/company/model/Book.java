package com.company.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Book  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String title;
private String authorName;
@ManyToOne
@JoinColumn(name = "category_id")
private Category category;
@OneToOne
private Image image;
@Enumerated( EnumType.STRING)
private BookStatus status;
private Integer totalPages;
private Integer lastPageNumber;
private String publisher;
private Long userId;

}
