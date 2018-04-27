package kata.domain;

import DDD.framework.Entity;
import DDD.framework.EntityId;

import java.util.UUID;

import static DDD.framework.basic.Objects.requireNotNull;

@DDD.Aggregate
public class Deal extends Entity<Deal.Id> {

    private DealName dealName;

    public Deal(Id id, DealName dealName) {
        super(id);
        this.dealName = requireNotNull(dealName);
    }

    public Deal(DealName dealName) {
        this(Id.generate(), dealName);
    }

    public void rename(DealName dealName) {
        this.dealName = dealName;
    }

    public DealName getDealName() {
        return dealName;
    }

    public static class Id extends EntityId<UUID> {
        public Id(UUID id) {
            super(id);
        }

        public static Deal.Id generate() {
            return new Deal.Id(UUID.randomUUID());
        }
    }

}