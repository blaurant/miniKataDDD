package kata.application;

import kata.domain.Deal;
import kata.domain.DealName;

public class Dealing {

    private DealRepo dealRepo;

    public void createDeal(Deal.Id dealId, DealName dealName) {
        dealRepo.save(new Deal(dealId, dealName));
    }

    public void rename(Deal.Id dealId, DealName dealName) {
        Deal deal = dealRepo.byId(dealId)
                .orElseThrow(() -> new UnknowDeal(dealId));
        deal.rename(dealName);
        dealRepo.save(deal);
    }

    public void inject(DealRepo dealRepo) {
        this.dealRepo = dealRepo;
    }
}
