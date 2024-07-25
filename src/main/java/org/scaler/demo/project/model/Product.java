package org.scaler.demo.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel{

    private String name;
    private String description;
    @ManyToOne()
    private Category category;
    private String imageUrl;
}
