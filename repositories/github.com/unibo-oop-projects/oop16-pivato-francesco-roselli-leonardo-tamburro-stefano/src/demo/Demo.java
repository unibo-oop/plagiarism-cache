package demo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.DataImpl;
import model.EmployeeImpl;
import model.ExerciseImpl;
import model.ModelImpl;
import model.PersonImpl;
import model.ProductImpl;
import model.ReadWriteData;
import model.SubscriptionImpl;
import model.UserImpl;
import model.enumerations.ContractType;
import model.enumerations.ExerciseType;
import model.enumerations.LoginData;
import model.enumerations.SubscriptionType;
import model.interfaces.Data;
import model.interfaces.Employee;
import model.interfaces.Product;
import model.interfaces.User;

public class Demo {

        public Demo(){
            ModelImpl m = new ModelImpl();

            final Data data = DataImpl.getInstance();

            final Map<String, String> login = new HashMap<>();

            for (LoginData l : LoginData.values()) {
                    login.put(l.getId(), l.getPassword());
            }

            data.addToLoginData(login);

            User user1 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Stefano")
                            .lastName("Tamburro")
                            .taxCode("STFTMB25349SH94HE")
                            .birthday(LocalDate.of(1994, 5, 18))
                            .email("stefano.tamburro@unibo.it")
                            .telephoneNumber("3316598746")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 1, 5))
                            .expirationDate(LocalDate.of(2016, 1, 5).plusMonths(12))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 1, 12))
                            .build());
            user1.getWorkout().setExerciseInDay("lunedì", new ExerciseImpl(ExerciseType.ABDOMINALS, "crunch", "3", "12", "60", "55"));
            user1.getWorkout().setExerciseInDay("lunedì", new ExerciseImpl(ExerciseType.BICEPS, "curl", "3", "12", "60", "55"));
            user1.getWorkout().setExerciseInDay("lunedì", new ExerciseImpl(ExerciseType.DORSALS, "pulley", "3", "12", "60", "55"));
            user1.getWorkout().setExerciseInDay("martedì", new ExerciseImpl(ExerciseType.ABDOMINALS, "crunch", "3", "12", "60", "55"));
            user1.getWorkout().setExerciseInDay("mercoledì", new ExerciseImpl(ExerciseType.LEGS, "squat", "3", "12", "60", "55"));

            User user2 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Leonardo")
                            .lastName("Roselli")
                            .taxCode("RSLLRD95A112HS00")
                            .birthday(LocalDate.of(1995, 1, 12))
                            .email("leonardo.roselli@studio.unibo.it")
                            .telephoneNumber("3319370926")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 1, 7))
                            .expirationDate(LocalDate.of(2016, 1, 7).plusMonths(12))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 11, 12))
                            .build());

            User user3 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Francesco")
                            .lastName("Pivato")
                            .taxCode("PVTFRN1267HG7H09")
                            .birthday(LocalDate.of(1995, 5, 18))
                            .email("francesco.pivato@studio.unibo.it")
                            .telephoneNumber("3319076590")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.QUARTERLY)
                            .signingDate(LocalDate.of(2016, 2, 5))
                            .expirationDate(LocalDate.of(2016, 2, 5).plusMonths(4))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 2, 5))
                            .build());

            User user4 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Federico")
                            .lastName("Rossi")
                            .taxCode("FDTFRN1267HG7H00")
                            .birthday(LocalDate.of(1980, 5, 1))
                            .email("federico.rossi@studio.unibo.it")
                            .telephoneNumber("3219076590")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 3, 5))
                            .expirationDate(LocalDate.of(2016, 3, 5).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 3, 5))
                            .build());

            User user5 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Luca")
                            .lastName("Galeazzi")
                            .taxCode("LCGFRN1267HG7H00")
                            .birthday(LocalDate.of(1980, 5, 1))
                            .email("luca.galeazzi@studio.unibo.it")
                            .telephoneNumber("3919076590")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 4, 1))
                            .expirationDate(LocalDate.of(2016, 4, 1).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 4, 1))
                            .build());

            User user6 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Martino")
                            .lastName("Troconi")
                            .taxCode("MRTFRN1267HG7H01")
                            .birthday(LocalDate.of(1990, 8, 15))
                            .email("martino.tronconi@studio.unibo.it")
                            .telephoneNumber("3919076580")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 5, 1))
                            .expirationDate(LocalDate.of(2016, 5, 1).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 5, 1))
                            .build());

            User user7 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Margherita")
                            .lastName("Mercatali")
                            .taxCode("MRGMRC1267HG7H02")
                            .birthday(LocalDate.of(1990, 8, 15))
                            .email("margherita.mercatali@studio.unibo.it")
                            .telephoneNumber("3911076480")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 6, 1))
                            .expirationDate(LocalDate.of(2016, 6, 1).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 6, 1))
                            .build());

            User user8 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Alessandro")
                            .lastName("Albonetti")
                            .taxCode("LBNLND1267HG7H03")
                            .birthday(LocalDate.of(1990, 8, 15))
                            .email("alessandro.albonetti@studio.unibo.it")
                            .telephoneNumber("3911001480")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 11, 10))
                            .expirationDate(LocalDate.of(2016, 11, 10).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 11, 10))
                            .build());

            User user9 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Alessandro")
                            .lastName("Albonetti")
                            .taxCode("LBNLND1267HG7H03")
                            .birthday(LocalDate.of(1990, 8, 15))
                            .email("alessandro.albonetti@studio.unibo.it")
                            .telephoneNumber("3911001480")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 7, 10))
                            .expirationDate(LocalDate.of(2016, 7, 10).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 7, 10))
                            .build());

            User user10 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Davide")
                            .lastName("Milani")
                            .taxCode("MLNDVD1267HG7H05")
                            .birthday(LocalDate.of(1990, 8, 15))
                            .email("davide.milani@studio.unibo.it")
                            .telephoneNumber("3911681480")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 8, 11))
                            .expirationDate(LocalDate.of(2016, 8, 11).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 8, 11))
                            .build());

            User user11 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Riccardo")
                            .lastName("Biagi")
                            .taxCode("BGRCCR1267HG7H05")
                            .birthday(LocalDate.of(1990, 8, 15))
                            .email("riccardo.biagi@studio.unibo.it")
                            .telephoneNumber("3911680080")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 9, 11))
                            .expirationDate(LocalDate.of(2016, 9, 11).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 9, 11))
                            .build());

            User user12 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Sean")
                            .lastName("Facchini")
                            .taxCode("FCCNSN1267HG7H08")
                            .birthday(LocalDate.of(1991, 3, 15))
                            .email("sean.facchini@studio.unibo.it")
                            .telephoneNumber("3893681480")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 10, 12))
                            .expirationDate(LocalDate.of(2016, 10, 12).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 10, 12))
                            .build());

            User user13 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Giampaolo")
                            .lastName("Billi")
                            .taxCode("BLLGMP1267HG7H08")
                            .birthday(LocalDate.of(1995, 3, 19))
                            .email("giampaolo.billi@studio.unibo.it")
                            .telephoneNumber("3898787080")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 12, 12))
                            .expirationDate(LocalDate.of(2016, 12, 12).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 12, 12))
                            .build());

            User user14 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Alessandro")
                            .lastName("Maurizi")
                            .taxCode("ALSMRZ1267H8H08")
                            .birthday(LocalDate.of(1993, 8, 20))
                            .email("alessandro.maurizi@studio.unibo.it")
                            .telephoneNumber("3997680040")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2016, 6, 12))
                            .expirationDate(LocalDate.of(2016, 6, 12).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2016, 6, 12))
                            .build());

            User user15 = new UserImpl(new PersonImpl.PersonBuilder()
                            .firstName("Nicolò")
                            .lastName("Naldi")
                            .taxCode("NLDNCL2467H8H08")
                            .birthday(LocalDate.of(1993, 8, 20))
                            .email("nicolo.naldi@studio.unibo.it")
                            .telephoneNumber("3999980040")
                            .build(),
                            new SubscriptionImpl.SubscriptionBuilder()
                            .code(new Random().nextInt())
                            .type(SubscriptionType.ANNUAL)
                            .signingDate(LocalDate.of(2017, 2, 12))
                            .expirationDate(LocalDate.of(2017, 2, 12).plusYears(1))
                            //.price(250)
                            .paymentDate(LocalDate.of(2017, 2, 12))
                            .build());

            Employee employee1 = new EmployeeImpl(new PersonImpl.PersonBuilder()
                            .firstName("Alessandro")
                            .lastName("Matulli")
                            .taxCode("MTLLSSSOKM12KK24")
                            .birthday(LocalDate.of(1980, 5, 18))
                            .email("ale.matu@hotmail.it")
                            .telephoneNumber("3319034590")
                            .build() , 900, LocalDate.of(2016, 1, 5), ContractType.STAGE);

            Employee employee2 = new EmployeeImpl(new PersonImpl.PersonBuilder()
                            .firstName("Flavio")
                            .lastName("Brunetti")
                            .taxCode("BRNFLVSOK111KK24")
                            .birthday(LocalDate.of(1980, 9, 20))
                            .email("flavio.brunetti@hotmail.it")
                            .telephoneNumber("3319888590")
                            .build() , 900, LocalDate.of(2016, 2, 5), ContractType.ANNUAL);

            Employee employee3 = new EmployeeImpl(new PersonImpl.PersonBuilder()
                            .firstName("Raffaella")
                            .lastName("Zambelli")
                            .taxCode("ZMBRFFIII234KK24")
                            .birthday(LocalDate.of(1980, 9, 20))
                            .email("raffaella.zambelli@hotmail.it")
                            .telephoneNumber("3331118590")
                            .build() , 900, LocalDate.of(2016, 2, 5), ContractType.BIENNAL);


            Product p1 = new ProductImpl.ProductBuilder()
			                 .name("Accappatoio")
			                 .category("Vestiario")
			                 .code("1")
			                 .description("prodotto messo a disposizione dalla nostra palestra")
			                 .price(31.0)
			                 .quantity(100)
			                 .builder();
            
            Product p2 = new ProductImpl.ProductBuilder()
		                    .name("Ciabatte")
		                    .category("Vestiario")
		                    .code("2")
		                    .description("prodotto messo a disposizione dalla nostra palestra")
		                    .price(15.0)
		                    .quantity(100)
		                    .builder();
            
            Product p3 = new ProductImpl.ProductBuilder()
			                .name("Maglietta")
			                .category("Vestiario")
			                .code("3")
			                .description("prodotto messo a disposizione dalla nostra palestra")
			                .price(30.0)
			                .quantity(100)
			                .builder();


            m.saveUser(user1);
            m.saveUser(user2);
            m.saveUser(user3);
            m.saveUser(user4);
            m.saveUser(user5);
            m.saveUser(user6);
            m.saveUser(user7);
            m.saveUser(user8);
            m.saveUser(user9);
            m.saveUser(user10);
            m.saveUser(user11);
            m.saveUser(user12);
            m.saveUser(user13);
            m.saveUser(user14);
            m.saveUser(user15);

            m.addEmployee(employee1);
            m.addEmployee(employee2);
            m.addEmployee(employee3);

            m.addProduct(p1);
            m.addProduct(p2);
            m.addProduct(p3);


            ReadWriteData.write(DataImpl.getInstance());
        }

}
