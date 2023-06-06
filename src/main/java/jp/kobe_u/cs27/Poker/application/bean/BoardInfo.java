package jp.kobe_u.cs27.Poker.application.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BoardInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String date;

    private String type;

    private int boardNumber;

    private String suit;

    private String number;
}
