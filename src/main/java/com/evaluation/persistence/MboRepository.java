package com.evaluation.persistence;

import java.util.List;
import java.util.Optional;

import com.evaluation.domain.Mbo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MboRepository extends CrudRepository<Mbo, Long> {

    // 해당 turn에 속하는 직원의 목표 리스트
    @Query("SELECT m FROM Mbo m WHERE m.tno=:tno AND m.sno=:sno ORDER BY m.mno ASC")
    public Optional<List<Mbo>> listByTnoSno(@Param("tno") long tno, @Param("sno") long sno);

    // list에 속하는 전체 목표 엑셀로 다운 받기 위해
    @Query(value = "select s.name, s.email, s.level, s.department1, s.department2, m.finish, m.object, m.process, m.ratio from tbl_mbo m left join tbl_staff s on m.staff_sno=s.sno where m.turn_tno=:tno order by m.mno asc", nativeQuery = true)
    public Optional<List<List<String>>> listByTno(@Param("tno") long tno);

    // 해당 tno와 sno에 속하는 멤버의 ratio 합계
    // @Query("SELECT m.sno, SUM(m.ratio) FROM MBO m GROUP BY m.sno HAVING
    // (m.tno=:tno AND m.sno=:sno)")
    // 위 jpql에서 계속 컬럼 인식 문제가 나타남(turn_tno) 때문에 nativeQuery를 통해 우회.
    @Query(value = "select staff_sno,turn_tno,sum(ratio) from tbl_mbo where finish='Y' group by staff_sno having turn_tno=:tno and staff_sno=:sno", nativeQuery = true)
    public Optional<List<List<String>>> ratioByTnoSno(@Param("tno") long tno, @Param("sno") long sno);

}