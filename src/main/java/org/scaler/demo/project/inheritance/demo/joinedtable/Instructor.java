package org.scaler.demo.project.inheritance.demo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_entity")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User{

    private String specialization;
}
