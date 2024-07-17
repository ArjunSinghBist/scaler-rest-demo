package org.scaler.demo.project.inheritance.demo.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mentors")
public class Mentor extends User {

    private double avgRating;
}
