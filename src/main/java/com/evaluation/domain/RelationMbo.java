package com.evaluation.domain;

import java.sql.Timestamp;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.UpdateTimestamp;

import com.evaluation.domain.embeddable.RatioValue;

import lombok.Getter;
import lombok.Setter;

/**
 * <code>RelationMbo</code> 객체는 한 회차에 속하는 mbo관계 매핑을 표현한다.
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_relation_mbo")
public class RelationMbo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluated", referencedColumnName = "sno")
    private Staff evaluated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluator", referencedColumnName = "sno")
    private Staff evaluator;

    private String relation;

    @ElementCollection
    @CollectionTable(name = "tbl_relation_mbo_answers", joinColumns = @JoinColumn(name = "relation_rno"))
    @MapKeyColumn(name = "answer_key")
    @OrderBy(clause = "answer_key asc")
    private Map<String, RatioValue> answers;

    @ElementCollection
    @CollectionTable(name = "tbl_relation_mbo_comments", joinColumns = @JoinColumn(name = "relation_rno"))
    @MapKeyColumn(name = "comment_key")
    @Column(name = "comment_value", length = 2000)
    @OrderBy(clause = "comment_key asc")
    private Map<String, String> comments;

    private String finish = "N";

    @CreationTimestamp
    private Timestamp writeDate;
    @UpdateTimestamp
    private Timestamp updateDate;

    @Column(name = "turn_tno")
    private long tno;
}