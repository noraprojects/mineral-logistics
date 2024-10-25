package be.kdg.prog6.Park.adapters.out.db;

import be.kdg.prog6.Park.domain.AttractionStatus;
import be.kdg.prog6.Park.ports.out.StandCreatePort;
import be.kdg.prog6.Park.ports.out.StandDeletePort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class StandDBAdapter implements StandDeletePort, StandCreatePort {

    private final AttractionJpaRepository jpaRepository;

    public StandDBAdapter(AttractionJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void create(UUID uuid) {
        Optional<AttractionJpaEntity> entityOptional = jpaRepository.findById(uuid);
        if (entityOptional.isPresent()) {
            AttractionJpaEntity entity = entityOptional.get();
            entity.setAttractionStatus(AttractionStatus.open);
            jpaRepository.save(entity);
        }

    }

    @Override
    public void delete(UUID uuid) {
        Optional<AttractionJpaEntity> entityOptional = jpaRepository.findById(uuid);
        if (entityOptional.isPresent()) {
            AttractionJpaEntity entity = entityOptional.get();
            entity.setAttractionStatus(AttractionStatus.closed);
            jpaRepository.save(entity);
        }

    }
}
