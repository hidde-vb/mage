
package mage.cards.e;

import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.KickedCondition;
import mage.abilities.costs.*;
import mage.abilities.costs.mana.ColoredManaCost;
import mage.abilities.costs.mana.ManaCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.costs.mana.VariableManaCost;
import mage.abilities.decorator.ConditionalInterveningIfTriggeredAbility;
import mage.abilities.dynamicvalue.DynamicValue;
import mage.abilities.dynamicvalue.common.ColorsOfManaSpentToCastCount;
import mage.abilities.dynamicvalue.common.GetKickerXValue;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.abilities.effects.common.EntersBattlefieldWithXCountersEffect;
import mage.abilities.effects.common.InfoEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.abilities.keyword.KickerAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.choices.ManaChoice;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.SubType;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.filter.FilterMana;
import mage.game.Game;
import mage.game.permanent.token.SaprolingToken;
import mage.players.Player;

import java.util.UUID;

/**
 *
 * @author Hiddevb
 */
public final class EmblazonedGolem extends CardImpl {

    public EmblazonedGolem(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.ARTIFACT,CardType.CREATURE},"{2}");

        this.subtype.add(SubType.GOLEM);
        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        // Kicker {X}.
        KickerAbility kickerAbility = new KickerAbility("{X}");
        kickerAbility.getKickerCosts().forEach(cost -> {
            cost.setMaximumCost(5);
            // TODO this cost should trigger the conditional
            //cost.setCostType();
        });
        this.addAbility(kickerAbility);

        // Spend only colored mana on X. No more than one mana of each color may be spent this way.
        this.addAbility(new SimpleStaticAbility(
                Zone.ALL, new InfoEffect("Spend only colored mana on X. No more than one mana of each color may be spent this way.")
        ));

        // If Emblazoned Golem was kicked, it enters the battlefield with X +1/+1 counters on it.
        this.addAbility(new EntersBattlefieldAbility(
                new AddCountersSourceEffect(CounterType.P1P1.createInstance(), GetKickerXValue.instance, true),
                KickedCondition.instance,
                null,
                "If {this} was kicked, it enters the battlefield with X +1/+1 counters on it."
        ));
    }

    private EmblazonedGolem(final EmblazonedGolem card) {
        super(card);
    }

    @Override
    public EmblazonedGolem copy() {
        return new EmblazonedGolem(this);
    }
}