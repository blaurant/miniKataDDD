package kata.application;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import com.tngtech.jgiven.junit.ScenarioTest;
import kata.domain.Deal;
import kata.domain.DealName;
import kata.infra.InMemoryDealRepo;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static kata.application.DealingTest.DEAL_ID;
import static kata.application.DealingTest.DEAL_NAME;
import static kata.application.DealingTest.dealRepo;
import static kata.domain.Deal.Id.generate;

public class DealingTest extends ScenarioTest<GivenSomeState, WhenSomeAction, ThenSomeOutcome> {

    // NO IOC
    static DealRepo dealRepo = new InMemoryDealRepo();
    static Dealing dealing = new Dealing();
    static {
        dealing.inject(dealRepo);
    }

    static DealName DEAL_NAME = new DealName("myDeal");
    static Deal.Id DEAL_ID = generate();
    static DealName NEW_DEAL_NAME = new DealName("newDeal");


    @Test
    public void create_deal() {
        given().a_deal_id(DEAL_ID).and().a_deal_name(DEAL_NAME);
        when().create_deal();
        then().deal_is_created(DEAL_ID);
    }

    @Test
    public void rename_deal() {
        given().a_deal();
        when().rename_deal(NEW_DEAL_NAME);
        then().deal_name_is(NEW_DEAL_NAME);
    }

}

class GivenSomeState extends Stage<GivenSomeState> {

    @ProvidedScenarioState
    DealName dealName;

    @ProvidedScenarioState
    Deal.Id dealId;

    public GivenSomeState a_deal_id(Deal.Id dealId) {
        this.dealId = dealId;
        return self();
    }

    public GivenSomeState a_deal_name(DealName dealName) {
        this.dealName = dealName;
        return self();
    }

    public GivenSomeState a_deal() {
        DealingTest.dealing.createDeal(DEAL_ID, DEAL_NAME);
        this.dealId = DEAL_ID;
        return self();
    }
}

class WhenSomeAction extends Stage<WhenSomeAction> {

    @ExpectedScenarioState
    DealName dealName;

    @ScenarioState
    Deal.Id dealId;

    public WhenSomeAction create_deal() {
        DealingTest.dealing.createDeal(dealId, dealName);
        return self();
    }


    public WhenSomeAction rename_deal(DealName new_deal_name) {
        DealingTest.dealing.rename(dealId, new_deal_name);
        return self();
    }
}

class ThenSomeOutcome extends Stage<ThenSomeOutcome> {

    @ExpectedScenarioState
    Deal.Id dealId;

    public ThenSomeOutcome deal_is_created(Deal.Id dealId) {
        Assertions.assertThat(dealRepo.byId(dealId).get().getId()).isEqualTo(dealId);
        return self();
    }

    public ThenSomeOutcome deal_name_is(DealName dealName) {
        Assertions.assertThat(dealRepo.byId(dealId).get().getDealName()).isEqualTo(dealName);
        return self();
    }
}
