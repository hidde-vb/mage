
package mage.cards.n;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.BlocksCreatureWithFlyingTriggeredAbility;
import mage.abilities.effects.common.continuous.BoostSourceEffect;
import mage.abilities.keyword.ReachAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Duration;

/**
 *
 * @author LevelX2
 */
public final class NetcasterSpider extends CardImpl {

    public NetcasterSpider(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{2}{G}");
        this.subtype.add(SubType.SPIDER);

        this.power = new MageInt(2);
        this.toughness = new MageInt(3);

        // Reach
        this.addAbility(ReachAbility.getInstance());

        // Whenever Netcaster Spider blocks a creature with flying, Netcaster Spider gets +2/+0 until end of turn.
        this.addAbility(new BlocksCreatureWithFlyingTriggeredAbility(new BoostSourceEffect(2, 0, Duration.EndOfTurn), false));
    }

    private NetcasterSpider(final NetcasterSpider card) {
        super(card);
    }

    @Override
    public NetcasterSpider copy() {
        return new NetcasterSpider(this);
    }
}