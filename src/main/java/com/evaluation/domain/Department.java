package com.evaluation.domain;

import java.sql.Timestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.evaluation.domain.embeddable.Leader;

import lombok.Getter;
import lombok.Setter;

/**
 * <code>Department</code> 객체는 한 회차에 속하는 부서와 leader 정보를 표현한다.
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_department")
@SecondaryTable(name = "tbl_department_leader", pkJoinColumns = @PrimaryKeyJoinColumn(name = "department_dno", referencedColumnName = "dno"))
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dno;

	private String department1;
	private String department2;

	private String writeId;
	private String updateId;

	@CreationTimestamp
	private Timestamp writeDate;
	@UpdateTimestamp
	private Timestamp updateDate;

	@Column(name = "turn_tno")
	private long tno;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "title", column = @Column(table = "tbl_department_leader")),
			@AttributeOverride(name = "content", column = @Column(table = "tbl_department_leader", length = 2000)),
			@AttributeOverride(name = "staff_sno", column = @Column(table = "tbl_department_leader")) })
	private Leader leader;

}
