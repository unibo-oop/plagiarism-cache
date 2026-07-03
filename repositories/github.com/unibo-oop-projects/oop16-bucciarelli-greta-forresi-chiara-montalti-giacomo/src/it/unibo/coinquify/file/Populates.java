package it.unibo.coinquify.file;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import it.unibo.coinquify.balance.model.Common;
import it.unibo.coinquify.balance.model.DebtImpl;
import it.unibo.coinquify.balance.model.LendingImpl;
import it.unibo.coinquify.calendar.model.Event;
import it.unibo.coinquify.calendar.model.EventImpl;
import it.unibo.coinquify.home.Room;
import it.unibo.coinquify.mansionsmng.model.MansionImpl;
import it.unibo.coinquify.mansionsmng.model.MansionType;
import it.unibo.coinquify.noticeboard.model.PostIt;
import it.unibo.coinquify.noticeboard.model.PostItImpl;
import it.unibo.coinquify.roommates.model.RoomMate;
import it.unibo.coinquify.roommates.model.RoomMateImpl;
import it.unibo.coinquify.telephonebook.model.Contact;
import it.unibo.coinquify.telephonebook.model.ContactImpl;
import it.unibo.coinquify.utils.PhoneNumberPresentException;
import it.unibo.coinquify.shoppinglist.model.Item;
import it.unibo.coinquify.shoppinglist.model.ItemImpl;
import it.unibo.coinquify.utils.TimeImpl;

/**
 * Final classe that populates all file for demo.
 */
public final class Populates {
    private Populates() {
    }

    /**
     * populates all.
     * 
     * @throws ParseException
     *             if there're problems in parsing
     * @throws IOException
     *             if there're problems in io
     * @throws PhoneNumberPresentException  if there're already one contact with phoneNumber
     * @throws IllegalArgumentException if arguments of Contacts are illegal
     */
    public static void all() throws IOException, ParseException, IllegalArgumentException, PhoneNumberPresentException {
        Populates.roomMates();
        Populates.events();
        Populates.mansions();
        Populates.phoneBook();
        Populates.shoppingList();
        Populates.balance();
        Populates.noticeBoard();
    }

    private static void noticeBoard() throws IOException {
        final List<PostIt> postItList = new ArrayList<>();
        postItList.add(new PostItImpl("Chiamare Idraulico",
                "Perde il lavandino, il numero è 32058694860, solo dalle 15 alle 19 ogni giorno"));
        postItList.add(new PostItImpl("Guardare volantino Conad",
                "C'è in offerta lo Svelto, Edoardo li lava solo con quello, è la sua libertà"));
        postItList.add(new PostItImpl("Bolletta enel",
                "A tal proposito consiglio vivamente 10 Ave Maria e 5 Padre Nostro al Giorno "));
        NoticeBoardFile.savePostIt(postItList);

        final List<String> rulesList = new ArrayList<>();
        rulesList.addAll(Arrays.asList("Non si fuma in bagno", "La doccia si fa entro le 22",
                "Si pulisce sempre entro 1 ora da quando si ha sporcato", "Pulizia bagno ogni 2 giorni",
                "Non si possono fare rave party in casa in assenza degli altri coinquilini",
                "Mentre gli altri studiano non si può ascoltare a volume alto la musica"));
        NoticeBoardFile.saveRules(rulesList);
    }

    // CHECKSTYLE:OFF: checkstyle:magicnumber
    private static void balance() throws IOException {
        final Set<RoomMate> roomMates = RoomMatesFile.readRoomMates();

        ((RoomMate) roomMates.toArray()[0]).addDebt(new DebtImpl("Prestito Tabacchi", roomMates.toArray()[1], 5.60));
        ((RoomMate) roomMates.toArray()[1]).addDebt(new DebtImpl("Prestito Lavanderia", roomMates.toArray()[2], 7.80));
        ((RoomMate) roomMates.toArray()[2]).addDebt(new DebtImpl("Biglietti concerto", roomMates.toArray()[0], 40.50));

        ((RoomMate) roomMates.toArray()[0]).addDebt(new DebtImpl("pizza", roomMates.toArray()[2], 4.80));
        ((RoomMate) roomMates.toArray()[1]).addDebt(new DebtImpl("Libri II anno", roomMates.toArray()[0], 10.40));
        ((RoomMate) roomMates.toArray()[2]).addDebt(new DebtImpl("Crescione", roomMates.toArray()[1], 2.50));

        for (final RoomMate r : roomMates) {
            r.addDebt(new DebtImpl("Bollette Marzo", Common.HOUSE_DEBT, 108.00 / roomMates.size()));
        }

        ((RoomMate) roomMates.toArray()[0]).addLending(new LendingImpl((roomMates.toArray()[2]), "Felpa"));
        ((RoomMate) roomMates.toArray()[1]).addLending(new LendingImpl((roomMates.toArray()[0]), "Lampada per camera"));
        ((RoomMate) roomMates.toArray()[2]).addLending(new LendingImpl((roomMates.toArray()[1]), "Bicicletta"));

        ((RoomMate) roomMates.toArray()[0]).addLending(new LendingImpl(Common.HOUSE_LENDING, "Bicchieri cucina"));
        ((RoomMate) roomMates.toArray()[1]).addLending(new LendingImpl(Common.HOUSE_LENDING, "divano"));
        ((RoomMate) roomMates.toArray()[2]).addLending(new LendingImpl(Common.HOUSE_LENDING, "tende sala"));
        ((RoomMate) roomMates.toArray()[0]).addLending(new LendingImpl(Common.HOUSE_LENDING, "Tostapane"));
        ((RoomMate) roomMates.toArray()[1]).addLending(new LendingImpl(Common.HOUSE_LENDING, "Padelle"));
        ((RoomMate) roomMates.toArray()[2]).addLending(new LendingImpl(Common.HOUSE_LENDING, "Scarpiera"));

        RoomMatesFile.writeRoomMates(roomMates);

    }
    // CHECKSTYLE:ON: checkstyle:magicnumber

    private static void roomMates() throws IOException, ParseException {
        final Set<RoomMate> roomMates = new HashSet<>();
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
        roomMates.add(new RoomMateImpl("Mario", "Rossi", "MRARSS00A01H501B", Room.ROOM_A, "3332323233",
                dateFormat.parse("01/02/1989")));
        roomMates.add(new RoomMateImpl("Giorgia", "Giorgi", "GGARSS00A01H501B", Room.ROOM_B, "3332323233",
                dateFormat.parse("02/03/1993")));
        roomMates.add(new RoomMateImpl("Mattia", "Matti", "MMAMSS00A01H501B", Room.ROOM_C, "33323423233",
                dateFormat.parse("02/09/1992")));
        RoomMatesFile.writeRoomMates(roomMates);
    }
 

    // CHECKSTYLE:OFF: checkstyle:magicnumber
    private static void events() throws IOException, ParseException {
        final List<Event> events = new ArrayList<>();
        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
        events.add(new EventImpl(dateFormat.parse("15/04/2017"), dateFormat.parse("15/04/2017"), new TimeImpl(20, 0),
                new TimeImpl(23, 0), "Pizzata", null, "Cesena"));
        events.add(new EventImpl(dateFormat.parse("18/04/2017"), dateFormat.parse("18/04/2017"), new TimeImpl(12, 0),
                new TimeImpl(14, 30), "Idraulico", null, "Casa"));
        events.add(new EventImpl(dateFormat.parse("20/04/2017"), dateFormat.parse("20/04/2017"), new TimeImpl(21, 0),
                new TimeImpl(22, 0), "Riunione di condominio", null, "Studio amministratore"));
        events.add(new EventImpl(dateFormat.parse("21/04/2017"), dateFormat.parse("21/04/2017"), new TimeImpl(20, 0),
                new TimeImpl(23, 0), "Cena", "cena con i nostri amici", "Casa"));
        EventFile.writeEvents(events);
    }

    private static void phoneBook() throws IllegalArgumentException, PhoneNumberPresentException, IOException {
        final List<Contact> contactList = new ArrayList<>();
        final Calendar cal = Calendar.getInstance();

        contactList.add(new ContactImpl("Mauro", "Liverani", "", "3295840385", Optional.empty(), "Via Campidori 34",
                "maurito456@liverani.it", "", ""));

        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 15);
        contactList.add(new ContactImpl("Paolo", "Antonellini", "NTNPLZ4059482D45", "08539406833", Optional.of(cal.getTime()),
                "Via Forlivese 45", "paolo@anto.it", "0546948594", ""));

        contactList.add(new ContactImpl("Ilaria", "Verdi", "", "2938540358", Optional.empty(), "Piazza Pancrazi 56",
                "ila56stellinaxd@verdi.com", "", "054863864"));

        contactList.add(new ContactImpl("Gianfranco", "Liverani", "", "0546796484", Optional.empty(), "Via dei Martiri 3",
                "gianfranco@liverani.org", "0495833953", ""));
        
        cal.set(Calendar.YEAR, 1996);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 29);
        contactList.add(new ContactImpl("Augusto", "Botta", "BTTGST3495860432", "3331839503", Optional.of(cal.getTime()), "Via Augusto 445",
                "au@bottas.it", "", ""));
        
        contactList.add(new ContactImpl("Matteo", "Zanellato", "", "3312345694", Optional.empty(), "Via Ravennate 29",
                "mattew@zane.it", "0485939548", ""));

        PhoneBookFile.saveContacts(contactList);
    }

    private static void shoppingList() throws IOException {
        final List<Item> shoppingList = new ArrayList<>();

        shoppingList.add(new ItemImpl("Pane", "4"));
        shoppingList.add(new ItemImpl("Sale Grosso", "2"));
        shoppingList.add(new ItemImpl("Olio Extra-Vergine", "2"));
        shoppingList.add(new ItemImpl("Detersivo Bucato", "1"));
        shoppingList.add(new ItemImpl("Ammorbidente", "1"));
        shoppingList.add(new ItemImpl("Sgrassatore", "2"));
        shoppingList.add(new ItemImpl("Pacchi di acqua", "5"));

        ItemFile.write(shoppingList);
    }

    // CHECKSTYLE:OFF: checkstyle:magicnumber
    private static void mansions() throws IOException {
        final Set<RoomMate> roomMates = RoomMatesFile.readRoomMates();

        ((RoomMate) roomMates.toArray()[0]).addMansion(
                new MansionImpl(MansionType.BATHROOM_CLEAN, new TimeImpl(8, 0), new TimeImpl(9, 20), DayOfWeek.MONDAY));
        ((RoomMate) roomMates.toArray()[1]).addMansion(
                new MansionImpl(MansionType.BILLS, new TimeImpl(8, 30), new TimeImpl(9, 20), DayOfWeek.MONDAY));
        ((RoomMate) roomMates.toArray()[2]).addMansion(
                new MansionImpl(MansionType.SHOPPING, new TimeImpl(18, 30), new TimeImpl(19, 20), DayOfWeek.MONDAY));

        ((RoomMate) roomMates.toArray()[0]).addMansion(
                new MansionImpl(MansionType.KITCHEN_CLEAN, new TimeImpl(8, 0), new TimeImpl(9, 20), DayOfWeek.SUNDAY));
        ((RoomMate) roomMates.toArray()[1]).addMansion(new MansionImpl(MansionType.LIVING_ROOM_CLEAN,
                new TimeImpl(8, 30), new TimeImpl(9, 20), DayOfWeek.TUESDAY));
        ((RoomMate) roomMates.toArray()[2]).addMansion(new MansionImpl(MansionType.OTHERS_MANSIONS,
                new TimeImpl(18, 30), new TimeImpl(19, 20), DayOfWeek.FRIDAY));
        RoomMatesFile.writeRoomMates(roomMates);
    }
    // CHECKSTYLE:ON: checkstyle:magicnumber
}
