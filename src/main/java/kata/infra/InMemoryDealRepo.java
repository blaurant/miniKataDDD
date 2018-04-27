package kata.infra;

import kata.application.DealRepo;
import kata.domain.Deal;
import kata.domain.Deals;

import java.util.Optional;

public class InMemoryDealRepo implements DealRepo {

    Deals deals = new Deals();

    @Override
    public void save(Deal deal) {
        deals = deals.add(deal);
    }

    @Override
    public Optional<Deal> byId(Deal.Id id) {
        return deals.findBy(id);
    }
}
