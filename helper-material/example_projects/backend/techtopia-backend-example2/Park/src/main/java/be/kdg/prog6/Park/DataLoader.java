package be.kdg.prog6.Park;


import be.kdg.prog6.Park.adapters.out.db.AttractionJpaEntity;
import be.kdg.prog6.Park.adapters.out.db.AttractionJpaRepository;
import be.kdg.prog6.Park.adapters.out.db.StandJpaRepository;
import be.kdg.prog6.Park.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;


@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initData(AttractionJpaRepository attractionRepository) {
        return args -> {



            UUID FerrywheelUUID = UUID.fromString("ef01c728-ce36-46b5-a110-84f53fdd9661");


            List<Tags> tagsList = new ArrayList<>();
            tagsList.add(Tags.CANDY);
            tagsList.add(Tags.TEA);
            tagsList.add(Tags.FRENCH_FRY);

            Random random = new Random();
            int randomIndex = random.nextInt(tagsList.size());

            Tags randomTag = tagsList.get(randomIndex);




            AttractionJpaEntity ferryWheel = new AttractionJpaEntity(FerrywheelUUID,"Ferry Wheel",Category.ATTRACTION,Tags.DOPAMINE_RUSH,AgeGroup.ALL,AttractionStatus.open,"../src/assets/background/ferrywheel.png","A amazing place to get a great view of the entire park",100);
            AttractionJpaEntity farm = new AttractionJpaEntity(UUID.randomUUID(),"Farm",Category.ATTRACTION,Tags.WILD,AgeGroup.KIDS,AttractionStatus.open,"../src/assets/background/barn.png","See cute cows and play with them",30);
            AttractionJpaEntity soccer = new AttractionJpaEntity(UUID.randomUUID(),"Soccer",Category.SPORT,Tags.COMPETETIVE,AgeGroup.ALL,AttractionStatus.open,"../src/assets/background/soccer.png","Come play a game of soccer against kids",126);
            AttractionJpaEntity baseBall = new AttractionJpaEntity(UUID.randomUUID(),"BaseBall",Category.SPORT,Tags.COMPETETIVE,AgeGroup.ALL,AttractionStatus.open,"../src/assets/background/baseball.png","come play a game of baseball against kids",111);
            AttractionJpaEntity cruise = new AttractionJpaEntity(UUID.randomUUID(),"Cruise",Category.ATTRACTION,Tags.RELAXING,AgeGroup.ALL,AttractionStatus.maintenance,"../src/assets/background/cruise.png","Play hide and seek on the cruise",126);
            AttractionJpaEntity bakery = new AttractionJpaEntity(UUID.randomUUID(),"Bakery",Category.FOOD,Tags.RELAXING,AgeGroup.ALL,AttractionStatus.open,"../src/assets/background/bakery.png","Fresh breads and cookies",100);
            AttractionJpaEntity football = new AttractionJpaEntity(UUID.randomUUID(),"Football",Category.SPORT,Tags.COMPETETIVE,AgeGroup.ADULTS,AttractionStatus.open,"../src/assets/background/rugby.png","play a game of football",150);
            AttractionJpaEntity hotel = new AttractionJpaEntity(UUID.randomUUID(),"Paridise Hotel",Category.HOTEL,Tags.RELAXING,AgeGroup.ALL,AttractionStatus.open,"../src/assets/background/hotel1.png","5 star hotel",0);
            AttractionJpaEntity hotel2 = new AttractionJpaEntity(UUID.randomUUID(),"Bowser's Castle Hotel",Category.HOTEL,Tags.RELAXING,AgeGroup.ALL,AttractionStatus.open,"../src/assets/background/hotel2.png","4 star hotel",0);
            AttractionJpaEntity space = new AttractionJpaEntity(UUID.randomUUID(),"Space Shuttle",Category.SPACE,Tags.RELAXING,AgeGroup.ALL,AttractionStatus.open,"../src/assets/background/space.png","Interstelalr",120);

            UUID uuid1 = UUID.fromString("56a7c6e0-c6b4-4eaa-91a7-e34a9a66a6b1");
            UUID uuid2 = UUID.fromString("62fb4051-82e1-4d59-9d76-f22d48223bf6");
            UUID uuid3 = UUID.fromString("76dbb2e9-0c91-48b0-8c3b-52b8a44c6a8e");
            UUID uuid4 = UUID.fromString("84d6d7e1-9743-4d2a-9e96-487a107f67d5");
            UUID uuid5 = UUID.fromString("9e78a5a6-2f35-4f9b-8b3f-5a54c8e771c5");
            UUID uuid6 = UUID.fromString("a85ef1cb-1254-4b16-bd84-7833ff1d76e1");
            UUID uuid7 = UUID.fromString("b4194d3e-cc15-4382-aae1-0f4c3014294d");
            UUID uuid8 = UUID.fromString("c34ea4e9-e57b-4b93-9a6f-7f9a2b480c44");
            UUID uuid9 = UUID.fromString("d96ea8d7-3c4e-47b2-9a68-9c59a842b2e3");
            UUID uuid10 = UUID.fromString("e1f6b49d-849a-46e9-8b5b-cc47c9a5766a");
            UUID uuid11 = UUID.fromString("f6e12bf3-413c-4a85-92a7-8d27db163c5b");
            UUID uuid12 = UUID.fromString("48c94d3b-61e7-44aa-98c2-0e5bdfe2d71f");

            AttractionJpaEntity attraction1 = new AttractionJpaEntity(uuid1, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));
            AttractionJpaEntity attraction2 = new AttractionJpaEntity(uuid2, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));
            AttractionJpaEntity attraction3 = new AttractionJpaEntity(uuid3, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));
            AttractionJpaEntity attraction4 = new AttractionJpaEntity(uuid4, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));
            AttractionJpaEntity attraction5 = new AttractionJpaEntity(uuid5, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));
            AttractionJpaEntity attraction6 = new AttractionJpaEntity(uuid6, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));
            AttractionJpaEntity attraction7 = new AttractionJpaEntity(uuid7, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));
            AttractionJpaEntity attraction8 = new AttractionJpaEntity(uuid8, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));
            AttractionJpaEntity attraction9 = new AttractionJpaEntity(uuid9, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));
            AttractionJpaEntity attraction10 = new AttractionJpaEntity(uuid10, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));
            AttractionJpaEntity attraction11 = new AttractionJpaEntity(uuid11, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));
            AttractionJpaEntity attraction12 = new AttractionJpaEntity(uuid12, "Food Stand", Category.FOOD, randomTag, AgeGroup.ALL, AttractionStatus.closed, "../src/assets/background/truck.png", "food", random.nextInt(0, 200));


            List<AttractionJpaEntity> attractions = Arrays.asList(ferryWheel, farm, soccer, baseBall, cruise, bakery, football, hotel, hotel2, space,attraction1,attraction2,attraction3,attraction4,attraction5,attraction6
            ,attraction7,attraction8,attraction9,attraction10,attraction11,attraction12);

            // Save all attractions to the repository
            attractionRepository.saveAll(attractions);
//
        };
    }
}
