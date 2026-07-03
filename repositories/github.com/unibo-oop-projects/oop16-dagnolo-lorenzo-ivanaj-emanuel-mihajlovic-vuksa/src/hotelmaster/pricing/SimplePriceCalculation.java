package hotelmaster.pricing;

import java.util.stream.Collectors;

import hotelmaster.reservations.Occupation;
import hotelmaster.reservations.Stay;
import hotelmaster.structure.Hotel;

/**
 * A simple price calculation, following this expression: stayPrice = [for each
 * season](daysInSeason * (sum(stayExtras) + [for each room](sum(roomExtras) +
 * (maxPeople - actualPeople) * roomMissing + seasonPrice * [for each
 * personType](stayType * roomType * personPrice * amountOfPeople)))).
 */
public class SimplePriceCalculation implements PriceCalculation {

    @Override
    public double calculate(final Stay stay) {
        double total = 0;
        // retrieves all relevant seasons and iterates them
        for (final SeasonPriceDescriber season : Hotel.instance().getPriceView(SeasonPriceDescriber.class).stream()
                .filter(season -> season.getPeriod().overlaps(stay.getDates())).collect(Collectors.toList())) {
            double roomPrice = 0;
            int totalPeople = 0;
            // iterates all the occupations
            for (final Occupation occupation : stay.getOccupationsView()) {
                roomPrice = 0;
                // calculates the missing price for the given room as:
                // (maxPeople - actualPeople) * roomMissing
                final double totalMissingPrice = occupation.getRoom().getType().getMissingPrice()
                        * (occupation.getRoom().getType().getMaxPeople()
                                - occupation.getPeopleView().values().stream().mapToInt(num -> num).sum());
                for (final PersonPriceDescriber people : occupation.getPeopleView().keySet()) {
                    // partial calculation of a room's price, done for every
                    // type of person in it:
                    // roomPrice += stayType * roomType * people *
                    // amountOfPeople
                    roomPrice += stay.getType().getPrice() * occupation.getRoom().getType().getPrice()
                            * people.getPrice() * occupation.getPeopleView().get(people);
                }
                totalPeople += occupation.getPeopleView().values().stream().mapToInt(num -> num).sum();
                // considers the season's price, the missing price and the
                // room's extras:
                // roomPrice = roomPrice * seasonPrice * missingPrice +
                // sum(stayExtra)
                roomPrice = roomPrice * season.getPrice() + totalMissingPrice
                        + occupation.getRoom().getExtrasView().stream().mapToDouble(extra -> extra.getPrice()).sum();
            }
            // adds the room's price (adjusted for the season) to the total:
            // total += roomPrice + sum(roomExtras per person) * people +
            // sum(roomExtras not per person) * daysInSeason
            total += (roomPrice
                    + stay.getExtrasView().stream().filter(extra -> extra.isPerPerson())
                            .mapToDouble(extra -> extra.getPrice()).sum() * totalPeople
                    + stay.getExtrasView().stream().filter(extra -> !extra.isPerPerson())
                            .mapToDouble(extra -> extra.getPrice()).sum())
                    * season.getPeriod().getIntersectionWith(stay.getDates()).get().getTotalDays();
        }
        return total;
    }

}
