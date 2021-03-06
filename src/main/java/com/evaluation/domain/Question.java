package com.evaluation.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * <code>Question</code> 객체는 서베이에 이용되는 한 회차의 문항을 표현한다.
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qno;

    private int idx;

    private String category;
    private String item;

    private String division1;
    private String division2;

    private Double ratio;

    private String writeId;
    private String updateId;

    @CreationTimestamp
    private Timestamp writeDate;
    @UpdateTimestamp
    private Timestamp updateDate;

    @Column(name = "turn_tno")
    private long tno;
}
