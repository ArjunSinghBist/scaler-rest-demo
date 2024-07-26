package org.scaler.demo.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "products")
public class Product extends BaseModel{

    private String name;
    private String description;
    @ManyToOne()
    private Category category;
    private String imageUrl;
}
