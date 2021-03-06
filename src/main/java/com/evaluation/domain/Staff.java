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
 * <code>Staff</code> 객체는 한 회사에 속하는 직원 정보를 표현한다.
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_staff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sno;

	@Column(unique = true, nullable = false)
	private String email;
	private String password;
	private String name;
	private String telephone;

	private String department1;
	private String department2;
	private String level;
	private String division1;
	private String division2;
	private String writeId;
	private String updateId;
	@CreationTimestamp
	private Timestamp writeDate;
	@UpdateTimestamp
	private Timestamp updateDate;

	@Column(name = "company_cno")
	private long cno;

}
