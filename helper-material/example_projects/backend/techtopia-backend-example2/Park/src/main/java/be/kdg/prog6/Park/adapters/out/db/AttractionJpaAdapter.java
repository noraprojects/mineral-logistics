package be.kdg.prog6.Park.adapters.out.db;

import be.kdg.prog6.Park.domain.Attraction;
import be.kdg.prog6.Park.ports.out.AttractionRepositoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttractionJpaAdapter implements AttractionRepositoryPort {

    private final AttractionJpaRepository jpaRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public AttractionJpaAdapter(AttractionJpaRepository jpaRepository, ObjectMapper objectMapper) {
        this.jpaRepository = jpaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Attraction save(Attraction attraction) {
        AttractionJpaEntity entity = objectMapper.convertValue(attraction, AttractionJpaEntity.class);
        entity = jpaRepository.save(entity);
        return objectMapper.convertValue(entity, Attraction.class);
    }

}
