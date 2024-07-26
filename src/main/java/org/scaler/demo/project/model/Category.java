package org.scaler.demo.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "categories")
public class Category extends BaseModel{

    private String name;
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    List<Product> products;
}
