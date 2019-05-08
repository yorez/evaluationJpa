package com.evaluation.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.evaluation.domain.QStaff;
import com.evaluation.domain.Staff;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface StaffRepository extends CrudRepository<Staff, String>, QuerydslPredicateExecutor<Staff> {

	public default Predicate makePredicate(String type, String keyword, Long cno) {

		BooleanBuilder builder = new BooleanBuilder();

		QStaff staff = QStaff.staff;

		builder.and(staff.company.cno.eq(cno));

		if (type == null) {
			return builder;
		}

		switch (type) {
		case "email":
			builder.and(staff.email.like("%" + keyword + "%"));
			break;
		case "name":
			builder.and(staff.name.like("%" + keyword + "%"));
			break;
		case "department":
			builder.and(staff.department1.like("%" + keyword + "%"));
			builder.and(staff.department2.like("%" + keyword + "%"));
			break;
		case "level":
			builder.and(staff.level.like("%" + keyword + "%"));
			break;
		}
		return builder;
	}
}