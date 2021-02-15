package by.zadziarnouski.telegrambotdemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "descriptions")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
}
