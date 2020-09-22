package com.martinsgms.pyxis.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.martinsgms.pyxis.bean.Missa;

@Repository
public interface MissaRepository extends JpaRepository<Missa, Long> {

	List<Missa> findDataByDataGreaterThanAndDataLessThan(LocalDateTime startDate, LocalDateTime endDate);
}
