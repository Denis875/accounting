package com.example.accounting.repository;

import com.example.accounting.entity.AbsenceRecord;
import com.example.accounting.entity.Reason;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AbsenceRecordRepository {
    private final JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    public AbsenceRecordRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AbsenceRecord findById(Long id) {
        return entityManager.find(AbsenceRecord.class, id);
    }

    public List<AbsenceRecord> getAllAbsenceRecords() {
        String sql = "SELECT * FROM accounting " +
                "JOIN reason ON accounting.reason_id = reason.id";
        return jdbcTemplate.query(sql, new AbsenceRecordRowMapper());
    }

    public void deleteAbsenceRecord(Long id) {
        String sql = "DELETE FROM accounting WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    private static class AbsenceRecordRowMapper implements RowMapper<AbsenceRecord> {
        @Override
        public AbsenceRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
            AbsenceRecord record = new AbsenceRecord();
            record.setId(rs.getLong("id"));
            record.setStartDate(rs.getDate("start_date"));
            record.setDuration(rs.getInt("duration"));
            record.setDiscounted(rs.getBoolean("discounted"));
            record.setDescription(rs.getString("description"));

            Reason reason = new Reason();
            reason.setId(rs.getLong("reason_id"));
            reason.setReasonText(rs.getString("reason_text"));
            record.setReason(reason);

            return record;
        }
    }

    public void createAbsenceRecord(AbsenceRecord record) {
        String sql = "INSERT INTO accounting (reason_id, start_date, duration, discounted, description) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                record.getReason().getId(),
                record.getStartDate(),
                record.getDuration(),
                record.isDiscounted(),
                record.getDescription()
        );
    }

    public void updateAbsenceRecord(AbsenceRecord record, Long id) {
        String sql = "UPDATE accounting SET reason_id = ?, start_date = ?, duration = ?, discounted = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                record.getReason().getId(),
                record.getStartDate(),
                record.getDuration(),
                record.isDiscounted(),
                record.getDescription(),
                id
        );
    }



}
