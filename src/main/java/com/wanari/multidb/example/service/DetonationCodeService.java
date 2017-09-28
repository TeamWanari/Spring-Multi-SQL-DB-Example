package com.wanari.multidb.example.service;

import com.wanari.multidb.example.domain.prod.DetonationCode;
import com.wanari.multidb.example.repository.prod.DetonationCodeRepository;
import com.wanari.multidb.example.service.errors.ErrorResponse;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetonationCodeService {
    public final DetonationCodeRepository detonationCodeRepository;

    public DetonationCodeService(DetonationCodeRepository detonationCodeRepository) {
        this.detonationCodeRepository = detonationCodeRepository;
    }

    public Either<ErrorResponse, List<DetonationCode>> findAll() {
        return Either.right(detonationCodeRepository.findAll());
    }
}
