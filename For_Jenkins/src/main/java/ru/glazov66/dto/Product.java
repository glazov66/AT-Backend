package ru.glazov66.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@ToString
public class Product {
    Integer id;
    String title;
    Integer price;
    String categoryTitle;

}
