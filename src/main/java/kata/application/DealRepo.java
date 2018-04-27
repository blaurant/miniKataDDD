package kata.application;

import kata.domain.Deal;

import java.util.Optional;

public interface DealRepo {

    void save(Deal deal);

    Optional<Deal> byId(Deal.Id id);
}
