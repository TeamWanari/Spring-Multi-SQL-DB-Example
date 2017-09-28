package com.wanari.multidb.example.repository.prod;

import com.wanari.multidb.example.domain.prod.DetonationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetonationCodeRepository extends JpaRepository<DetonationCode, Long> {
}
