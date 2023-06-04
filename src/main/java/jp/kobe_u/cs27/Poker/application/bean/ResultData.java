package jp.kobe_u.cs27.Poker.application.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ResultData {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String userID;
    private int solved;
    private String date;
    private double restTime;
}
