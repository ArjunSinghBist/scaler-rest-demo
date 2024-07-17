package org.scaler.demo.project.inheritance.demo.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_instructor")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Instructor extends User{

    private String specialization;
}
