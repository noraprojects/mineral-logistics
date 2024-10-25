package be.kdg.prog6.parkManagement;


import be.kdg.prog6.parkManagement.adapters.out.db.RefreshmentStandJpaEntity;
import be.kdg.prog6.parkManagement.adapters.out.db.RefreshmentStandJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;


@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initData(RefreshmentStandJpaRepository attractionRepository) {
        return args -> {

            RefreshmentStandJpaEntity refreshmentStand1 = new RefreshmentStandJpaEntity(UUID.fromString("56a7c6e0-c6b4-4eaa-91a7-e34a9a66a6b1"));
            RefreshmentStandJpaEntity refreshmentStand2 = new RefreshmentStandJpaEntity(UUID.fromString("62fb4051-82e1-4d59-9d76-f22d48223bf6"));
            RefreshmentStandJpaEntity refreshmentStand3 = new RefreshmentStandJpaEntity(UUID.fromString("76dbb2e9-0c91-48b0-8c3b-52b8a44c6a8e"));
            RefreshmentStandJpaEntity refreshmentStand4 = new RefreshmentStandJpaEntity(UUID.fromString("84d6d7e1-9743-4d2a-9e96-487a107f67d5"));
            RefreshmentStandJpaEntity refreshmentStand5 = new RefreshmentStandJpaEntity(UUID.fromString("9e78a5a6-2f35-4f9b-8b3f-5a54c8e771c5"));
            RefreshmentStandJpaEntity refreshmentStand6 = new RefreshmentStandJpaEntity(UUID.fromString("a85ef1cb-1254-4b16-bd84-7833ff1d76e1"));
            RefreshmentStandJpaEntity refreshmentStand7 = new RefreshmentStandJpaEntity(UUID.fromString("b4194d3e-cc15-4382-aae1-0f4c3014294d"));
            RefreshmentStandJpaEntity refreshmentStand8 = new RefreshmentStandJpaEntity(UUID.fromString("c34ea4e9-e57b-4b93-9a6f-7f9a2b480c44"));
            RefreshmentStandJpaEntity refreshmentStand9 = new RefreshmentStandJpaEntity(UUID.fromString("d96ea8d7-3c4e-47b2-9a68-9c59a842b2e3"));
            RefreshmentStandJpaEntity refreshmentStand10 = new RefreshmentStandJpaEntity(UUID.fromString("e1f6b49d-849a-46e9-8b5b-cc47c9a5766a"));
            RefreshmentStandJpaEntity refreshmentStand11 = new RefreshmentStandJpaEntity(UUID.fromString("f6e12bf3-413c-4a85-92a7-8d27db163c5b"));
            RefreshmentStandJpaEntity refreshmentStand12 = new RefreshmentStandJpaEntity(UUID.fromString("48c94d3b-61e7-44aa-98c2-0e5bdfe2d71f"));


            List<RefreshmentStandJpaEntity> attractions = List.of(refreshmentStand1, refreshmentStand2, refreshmentStand3, refreshmentStand4, refreshmentStand5, refreshmentStand6
                    , refreshmentStand7, refreshmentStand8, refreshmentStand9, refreshmentStand10, refreshmentStand11, refreshmentStand12);

            // Save all attractions to the repository
            attractionRepository.saveAll(attractions);
//
        };
    }
}
