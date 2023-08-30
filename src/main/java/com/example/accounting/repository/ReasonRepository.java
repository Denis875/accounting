package com.example.accounting.repository;

import com.example.accounting.entity.AbsenceRecord;
import com.example.accounting.entity.Reason;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReasonRepository {

    private final JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    public ReasonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Reason findById(Long id) {
        return entityManager.find(Reason.class, id);
    }

    public List<Reason> getAllReasons() {
        String sql = "SELECT * FROM reason";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Reason reason = new Reason();
            reason.setId(rs.getLong("id"));
            reason.setReasonText(rs.getString("reason_text"));
            return reason;
        });
    }

}
