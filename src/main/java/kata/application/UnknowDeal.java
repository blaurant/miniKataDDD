package kata.application;

import kata.domain.Deal;

public class UnknowDeal extends RuntimeException {

    public UnknowDeal(Deal.Id dealId) {
        super("Deal with ID " + dealId + "doesn't exists");
    }

}
