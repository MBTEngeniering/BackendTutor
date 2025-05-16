package de.fairstudy.backend.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import de.fairstudy.backend.model.enums.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Fach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private Subject fach;

    private String en;
    private String de;
    private String fr;
    /*
    private String es;
    private String it;
    private String pl;
    private String ru;
    private String tr;
    private String ar;
    private String zh;
    private String ja;
    private String ko;
    private String pt;
    private String nl;
    private String sv;
    private String da;
    private String no;
    private String fi;
    private String cs;
    private String hu;
    private String ro;
    private String sk;
    private String bg;
    private String hr;
    private String sr;
    private String el;
     */
}
