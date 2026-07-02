package gymman;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import gymman.auth.AuthService;
import gymman.auth.AuthServiceImpl;
import gymman.auth.Role;
import gymman.auth.RoleRepository;
import gymman.auth.RoleRepositoryImpl;
import gymman.customers.AdditionalService;
import gymman.customers.AdditionalServiceRepository;
import gymman.customers.AdditionalServiceRepositoryImpl;
import gymman.customers.Customer;
import gymman.customers.Customer.Gender;
import gymman.customers.CustomerRepository;
import gymman.customers.CustomerRepositoryImpl;
import gymman.customers.NumberedRegistration;
import gymman.customers.RegistrationRepository;
import gymman.customers.RegistrationRepositoryImpl;
import gymman.customers.SubscriptionType;
import gymman.customers.SubscriptionTypeRepository;
import gymman.customers.SubscriptionTypeRepositoryImpl;
import gymman.customers.TermRegistration;
import gymman.employees.CalendarService;
import gymman.employees.Employee;
import gymman.employees.EmployeeRepository;
import gymman.employees.EmployeeRepositoryImpl;
import gymman.employees.WorkShiftRepository;
import gymman.employees.WorkShiftRepositoryImpl;
import gymman.fitness.TrainingProgramRepository;
import gymman.fitness.TrainingProgramRepositoryImpl;
import gymman.infoclient.BmiRepository;
import gymman.tool.ToolRepository;
import gymman.ui.Controller;
import gymman.ui.Page;
import gymman.ui.app.AppController;
import gymman.ui.customers.AddCustomerPage;
import gymman.ui.customers.AddRegistrationPage;
import gymman.ui.customers.AddServicePage;
import gymman.ui.customers.AddSubscriptionPage;
import gymman.ui.customers.AdditionalServicePage;
import gymman.ui.customers.CreateCustomerController;
import gymman.ui.customers.CreateRegistrationController;
import gymman.ui.customers.CreateServiceController;
import gymman.ui.customers.CreateSubscriptionController;
import gymman.ui.customers.CustomerController;
import gymman.ui.customers.CustomerPage;
import gymman.ui.customers.RegistrationController;
import gymman.ui.customers.RegistrationPage;
import gymman.ui.customers.ServiceController;
import gymman.ui.customers.SubscriptionController;
import gymman.ui.customers.SubscriptionPage;
import gymman.ui.dashboard.DashboardController;
import gymman.ui.dashboard.DashboardPage;
import gymman.ui.employees.EditEmployeeController;
import gymman.ui.employees.EditEmployeePage;
import gymman.ui.employees.EditWorkShiftController;
import gymman.ui.employees.EditWorkShiftPage;
import gymman.ui.employees.EmployeesController;
import gymman.ui.employees.EmployeesPage;
import gymman.ui.employees.ShiftCalendarController;
import gymman.ui.employees.ShiftCalendarPage;
import gymman.ui.infoclient.BMIController;
import gymman.ui.infoclient.BMIPage;
import gymman.ui.infoclient.InfoClientController;
import gymman.ui.infoclient.InfoClientPage;
import gymman.ui.infoclient.LoginClientController;
import gymman.ui.infoclient.LoginClientPage;
import gymman.ui.instructor.CompileTrainingProgramController;
import gymman.ui.instructor.CompileTrainingProgramPage;
import gymman.ui.instructor.EditTrainingProgramController;
import gymman.ui.instructor.EditTrainingProgramPage;
import gymman.ui.instructor.InstructorController;
import gymman.ui.instructor.InstructorPage;
import gymman.ui.instructor.TrainingProgramController;
import gymman.ui.instructor.TrainingProgramPage;
import gymman.ui.login.LoginController;
import gymman.ui.login.LoginPage;
import gymman.ui.navigation.NavigationService;
import gymman.ui.navigation.NavigationServiceImpl;
import gymman.ui.roles.NewRoleController;
import gymman.ui.roles.NewRolePage;
import gymman.ui.roles.RolesController;
import gymman.ui.roles.RolesPage;
import gymman.ui.tool.NewToolController;
import gymman.ui.tool.NewToolPage;
import gymman.ui.tool.ToolController;
import gymman.ui.tool.ToolPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int WINDOW_WIDTH_MIN = 1200;
    public static final int WINDOW_HEIGHT_MIN = 800;

    @Override
    public void init() {

    }

    @Override
    public void start(final Stage primaryStage) {
        try {
            // Repositories
            final RoleRepository roleRepo = new RoleRepositoryImpl();
            final EmployeeRepository employeeRepo = new EmployeeRepositoryImpl();
            final CustomerRepository customerRepo = new CustomerRepositoryImpl();
            final WorkShiftRepository workshiftRepo = new WorkShiftRepositoryImpl();
            final RegistrationRepository registrationRepo = new RegistrationRepositoryImpl();
            final SubscriptionTypeRepository subscriptionTypeRepo = new SubscriptionTypeRepositoryImpl();
            final ToolRepository toolrepository = new ToolRepository();
            final AdditionalServiceRepository serviceRepo = new AdditionalServiceRepositoryImpl();
            final TrainingProgramRepository trainingProgramRepo = new TrainingProgramRepositoryImpl();
            final BmiRepository bmirepo = new BmiRepository();

		    // Services
		    final NavigationService navService = new NavigationServiceImpl();
			final AuthService authService = new AuthServiceImpl(employeeRepo, roleRepo);
			final CalendarService calendarService = new CalendarService(workshiftRepo);

			// Login
			final Page login = new LoginPage();
			final Controller loginCtrl = new LoginController(authService, navService);
			login.setController(loginCtrl);
			navService.registerPage(login);

			// Dashboard
			final Page dashboard = new DashboardPage();
			final Controller dashboardCtrl = new DashboardController(authService, employeeRepo);
			dashboard.setController(dashboardCtrl);
			navService.registerPage(dashboard);

			// Roles
		    final Page roles = new RolesPage();
		    final Controller rolesCtrl = new RolesController(roleRepo, navService, authService);
		    roles.setController(rolesCtrl);
			navService.registerPage(roles);

			// New Role
			final Page newRole = new NewRolePage();
			final Controller newRoleCtrl = new NewRoleController(authService, roleRepo, navService);
			newRole.setController(newRoleCtrl);
			navService.registerPage(newRole);

			// Employees
			final Page employees = new EmployeesPage();
			final Controller employeesCtrl = new EmployeesController(employeeRepo, navService, authService);
			employees.setController(employeesCtrl);
			navService.registerPage(employees);

			// Edit employee
			final Page editEmployee = new EditEmployeePage();
			final Controller editEmployeeCtrl = new EditEmployeeController(authService, employeeRepo, navService, roleRepo);
			editEmployee.setController(editEmployeeCtrl);
			navService.registerPage(editEmployee);

			// Shifts Calendar
			final Page shiftCalendar = new ShiftCalendarPage();
			final Controller shiftCalendarCtrl = new ShiftCalendarController(calendarService, navService, employeeRepo);
			shiftCalendar.setController(shiftCalendarCtrl);
            navService.registerPage(shiftCalendar);

            // Edit work shift
            final Page editWorkShift = new EditWorkShiftPage();
            final Controller editWorkShiftCtrl = new EditWorkShiftController(authService, navService, workshiftRepo, employeeRepo);
            editWorkShift.setController(editWorkShiftCtrl);
            navService.registerPage(editWorkShift);

		    //Customer
		    final Page customer = new CustomerPage();
            final Controller customerCtrl = new CustomerController(customerRepo, navService, authService, registrationRepo);
            customer.setController(customerCtrl);
            navService.registerPage(customer);

            // New Customer
            final Page addCustomer = new AddCustomerPage();
            final Controller createCustomerCtrl = new CreateCustomerController(customerRepo, navService);
            addCustomer.setController(createCustomerCtrl);
            navService.registerPage(addCustomer);

            // Registration
            final Page registration = new RegistrationPage();
            final Controller registrationCtrl = new RegistrationController(registrationRepo, navService, authService);
            registration.setController(registrationCtrl);
            navService.registerPage(registration);

            // New Registration
            final Page addRegistration = new AddRegistrationPage();
            final Controller createRegistrationCtrl = new CreateRegistrationController(customerRepo, subscriptionTypeRepo, registrationRepo, serviceRepo, navService);
            addRegistration.setController(createRegistrationCtrl);
            navService.registerPage(addRegistration);

            // Subscription
            final Page subscription = new SubscriptionPage();
            final Controller subscriptionCtrl = new SubscriptionController(subscriptionTypeRepo, navService, authService);
            subscription.setController(subscriptionCtrl);
            navService.registerPage(subscription);

            // New Subscription
            final Page addSubscription = new AddSubscriptionPage();
            final Controller createSubscriptionCtrl = new CreateSubscriptionController(subscriptionTypeRepo, navService);
            addSubscription.setController(createSubscriptionCtrl);
            navService.registerPage(addSubscription);

            // AdditionalService
            final Page additionalService = new AdditionalServicePage();
            final Controller addServiceCtrl = new ServiceController(serviceRepo, navService, authService);
            additionalService.setController(addServiceCtrl);
            navService.registerPage(additionalService);

            // New AdditionalService
            final Page addService = new AddServicePage();
            final Controller createServiceCtrl = new CreateServiceController(serviceRepo, navService);
            addService.setController(createServiceCtrl);
            navService.registerPage(addService);

            // Instructor form
            final Page instructor = new InstructorPage();
            final Controller instructorCtrl = new InstructorController(navService, customerRepo, registrationRepo, authService);
            instructor.setController(instructorCtrl);
            navService.registerPage(instructor);

            // TrainingProgram form
            final Page trainingProgram = new TrainingProgramPage();
            final Controller trainingProgramCtrl = new TrainingProgramController(navService, trainingProgramRepo, authService);
            trainingProgram.setController(trainingProgramCtrl);
            navService.registerPage(trainingProgram);

            // Tool
            final Page tool = new NewToolPage();
            final Controller toolCtrl = new NewToolController(toolrepository, navService);
            tool.setController(toolCtrl);
            navService.registerPage(tool);

            // Compile TrainingProgram form
            final Page compileTrainingProgram = new CompileTrainingProgramPage();
            final Controller compileTrainingCtrl = new CompileTrainingProgramController(navService, trainingProgramRepo, authService);
            compileTrainingProgram.setController(compileTrainingCtrl);
            navService.registerPage(compileTrainingProgram);

            // Edit TrainingProgram form
            final Page editTrainingProgram = new EditTrainingProgramPage();
            final Controller editTrainingCtrl = new EditTrainingProgramController(navService, trainingProgramRepo, authService);
            editTrainingProgram.setController(editTrainingCtrl);
            navService.registerPage(editTrainingProgram);

            // SearchTools
            final Page searchtool = new ToolPage();
            final Controller searchtoolCtrl = new ToolController(toolrepository, navService, authService);
            searchtool.setController(searchtoolCtrl);
            navService.registerPage(searchtool);

            // InfoClient
            final Page infoclient = new InfoClientPage();
            final Controller infoclientCtrl = new InfoClientController(serviceRepo, registrationRepo, bmirepo, navService);
            infoclient.setController(infoclientCtrl);
            navService.registerPage(infoclient);

            // InfoClient
            final Page loginClient = new LoginClientPage();
            final Controller loginClientCtrl = new LoginClientController(customerRepo, navService);
            loginClient.setController(loginClientCtrl);
            navService.registerPage(loginClient);

            // BMIClient
            final Page BMIClient = new BMIPage();
            final Controller BMICtrl = new BMIController(bmirepo, navService);
            BMIClient.setController(BMICtrl);
            navService.registerPage(BMIClient);

            final FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/app/App.fxml"));
			final AppController appController = new AppController(navService, authService);

            loader.setController(appController);

            final Pane root = loader.load();

            final Scene scene = new Scene(root, WINDOW_WIDTH_MIN, WINDOW_HEIGHT_MIN);

            primaryStage.setMinWidth(WINDOW_WIDTH_MIN);
            primaryStage.setMinHeight(WINDOW_HEIGHT_MIN);
            primaryStage.setScene(scene);
            primaryStage.setTitle("GymMan");

            // Super admin
            roleRepo.add(Role.builder()
                .name("super_admin")
                .permissions(new HashSet<>(authService.getRegisteredPermissions()))
                .build());
			employeeRepo.add(Employee.builder()
				.firstname("Admin")
				.lastname("Admin")
				.username("superadmin")
				.password("admin123")
				.role("super_admin")
				.fiscalCode("ABCDEF99A67E123K")
				.birthdate(LocalDate.of(1990, 1, 1))
				.address("abc")
				.phone("+39 123 456 7890")
                .build());

			// customer
			final Customer manfredonia = Customer.builder()
					.firstname("Daniele")
					.lastname("Manfredonia")
					.username("dManfre")
					.gender(Gender.M)
					.birthdate(LocalDate.of(1998, 7, 10))
					.email("danimanfre@gmail.com")
					.telephoneNumber("3476589236")
					.fiscalCode("DNLMNF10F34M455F")
					.build();

			final Customer mari = Customer.builder()
					.firstname("Mattia")
					.lastname("Mari")
					.username("mMari")
					.gender(Gender.M)
					.birthdate(LocalDate.of(1996, 4, 9))
					.email("mattimari@gmail.com")
					.telephoneNumber("3317538295")
					.fiscalCode("MTAMRI53F86S315I")
					.build();

			final Customer manzi = Customer.builder()
					.firstname("Matteo")
					.lastname("Manzi")
					.username("mManzi")
					.gender(Gender.M)
					.birthdate(LocalDate.of(1995, 5, 22))
					.email("matteomanz@gmail.com")
					.telephoneNumber("3476589236")
					.fiscalCode("MTOMAN34F67F325F")
					.build();

			final Customer guri = Customer.builder()
					.firstname("Sokol")
					.lastname("Guri")
					.username("sGuri")
					.gender(Gender.M)
					.birthdate(LocalDate.of(1997, 5, 12))
					.email("sokolguri@gmail.com")
					.telephoneNumber("3475939485")
					.fiscalCode("SKLGRI39F75H374G")
					.build();

			final Customer vavusotto = Customer.builder()
					.firstname("Matteo")
					.lastname("Vavusotto")
					.username("mVavu")
					.gender(Gender.M)
					.birthdate(LocalDate.of(1996, 10, 20))
					.email("matteovavu@gmail.com")
					.telephoneNumber("3338537491")
					.fiscalCode("MTTVVS48K42H665V")
					.build();

			final Customer morigi = Customer.builder()
					.firstname("Tommaso")
					.lastname("Morigi")
					.username("tMor")
					.gender(Gender.M)
					.birthdate(LocalDate.of(1998, 2, 8))
					.email("tommasomoro@gmail.com")
					.telephoneNumber("3297583268")
					.fiscalCode("TMSMRG48D84S234D")
					.build();

			customerRepo.add(manfredonia);
			customerRepo.add(mari);
			customerRepo.add(manzi);
			customerRepo.add(guri);
			customerRepo.add(vavusotto);
			customerRepo.add(morigi);

			// subscription
			final SubscriptionType salaPesi = SubscriptionType.builder()
					.name("sala pesi")
					.description("sollevare più pesi possibile per aumentare la massa muscolare.")
					.unitPrice(12)
					.build();

			final SubscriptionType yoga = SubscriptionType.builder()
					.name("yoga")
					.description("una disciplina per il corpo, la mente e l’anima che serve a farci vivere meglio.")
					.unitPrice(6)
					.build();

			final SubscriptionType pilates = SubscriptionType.builder()
					.name("pilates")
					.description("metodo di allenamento che insegna a prendere coscienza del proprio corpo,"
							+ " per rafforzarlo, correggere la postura "
							+ "e migliorare la fluidità e la precisione dei movimenti.")
					.unitPrice(10)
					.build();

			final SubscriptionType spinning = SubscriptionType.builder()
					.name("spinning")
					.description("Gli allenamenti di spinning sono finalizzati principalmente"
							+ " ad allenare la resistenza generale ma,"
							+ " basandosi sulle variazioni di ritmo e sulle ripetute,"
							+ " richiedendo quindi un dispendio energetico considerevole.")
					.unitPrice(9)
					.build();

			subscriptionTypeRepo.add(salaPesi);
			subscriptionTypeRepo.add(yoga);
			subscriptionTypeRepo.add(pilates);
			subscriptionTypeRepo.add(spinning);

			// additional service
			final AdditionalService sauna = AdditionalService.builder()
					.name("sauna")
					.description("contribuisce a migliorare ricambio e metabolismo."
							+ " La sauna disintossica in profondità e, quindi,"
							+ " rende la pelle luminosa e trasparente "
							+ "ed i tessuti maggiormente elastici.")
					.price(4)
					.build();

			final AdditionalService nuotoLibero = AdditionalService.builder()
			.name("nuoto libero")
			.description("sport completo che sollecita l’insieme "
					+ "dei muscoli del corpo e che sviluppa la resistenza.")
			.price(3)
			.build();

			final AdditionalService bibiteIllimitate = AdditionalService.builder()
					.name("bibite illimitate")
					.description("servizio che offre un numero"
							+ "illimitato di bevande che sono necessarie"
							+ "a manterenere il corpo idratato")
					.price(10)
					.build();

			final AdditionalService sedutaMassaggi = AdditionalService.builder()
					.name("seduta di massaggi")
					.description("servizio che garantisce due sedute di"
							+ "massaggi a settimana nelle giornate che preferisci.")
					.price(10)
					.build();

			serviceRepo.add(sauna);
			serviceRepo.add(nuotoLibero);
			serviceRepo.add(bibiteIllimitate);
			serviceRepo.add(sedutaMassaggi);

			// registration
			registrationRepo.add(TermRegistration.builder()
					.idClient(manfredonia.getId())
					.duration(6)
					.type(salaPesi)
					.discount(20)
					.additionalService(Arrays.asList(sauna,bibiteIllimitate))
					.signingDate(LocalDate.of(2021, 01, 01))
					.build());

			registrationRepo.add(TermRegistration.builder()
					.idClient(manzi.getId())
					.duration(2)
					.type(yoga)
					.discount(5)
					.additionalService(Arrays.asList(nuotoLibero,bibiteIllimitate))
					.signingDate(LocalDate.of(2021, 01, 03))
					.build());

			registrationRepo.add(TermRegistration.builder()
					.idClient(mari.getId())
					.duration(1)
					.type(salaPesi)
					.discount(0)
					.additionalService(Arrays.asList(sedutaMassaggi))
					.signingDate(LocalDate.of(2021, 01, 03))
					.build());

			registrationRepo.add(TermRegistration.builder()
					.idClient(guri.getId())
					.duration(12)
					.type(pilates)
					.discount(5)
					.additionalService(null)
					.signingDate(LocalDate.of(2021, 01, 28))
					.build());

			registrationRepo.add(TermRegistration.builder()
					.idClient(guri.getId())
					.duration(3)
					.type(spinning)
					.discount(50)
					.additionalService(Arrays.asList(bibiteIllimitate))
					.signingDate(LocalDate.of(2021, 01, 01))
					.build());

			registrationRepo.add(TermRegistration.builder()
					.idClient(vavusotto.getId())
					.duration(7)
					.type(yoga)
					.discount(30)
					.additionalService(Arrays.asList(sauna,sedutaMassaggi))
					.signingDate(LocalDate.of(2021, 01, 03))
					.build());

			registrationRepo.add(NumberedRegistration.builder()
					.idClient(manfredonia.getId())
					.maxEntries(20)
					.type(salaPesi)
					.discount(20)
					.additionalService(Arrays.asList(sauna))
					.build());

			registrationRepo.add(NumberedRegistration.builder()
					.idClient(manzi.getId())
					.maxEntries(15)
					.type(yoga)
					.discount(20)
					.additionalService(Arrays.asList(bibiteIllimitate))
					.build());

			registrationRepo.add(NumberedRegistration.builder()
					.idClient(mari.getId())
					.maxEntries(50)
					.type(pilates)
					.discount(8)
					.additionalService(Arrays.asList(nuotoLibero))
					.build());





            primaryStage.show();
            appController.onDisplay();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
