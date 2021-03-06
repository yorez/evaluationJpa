package com.evaluation.domain;

import java.sql.Timestamp;

import javax.jdo.annotations.Unique;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * * <code>Company</code> 객체는 회사 정보를 표현한다.
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_company")
@EqualsAndHashCode(of = "cno")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cno;

	@Unique
	private String id;
	private String name;
	private String password;
	private String homepage;

	private String hrManager;
	private String hrManagerTel;

	private String writeId;
	private String updateId;

	@CreationTimestamp
	private Timestamp writeDate;
	@UpdateTimestamp
	private Timestamp updateDate;

}
