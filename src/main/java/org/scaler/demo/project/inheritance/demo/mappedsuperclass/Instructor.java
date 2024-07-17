package org.scaler.demo.project.inheritance.demo.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "instructors")
public class Instructor extends User {

    private String specialization;
}
