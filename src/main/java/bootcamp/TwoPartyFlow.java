package bootcamp;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;

@InitiatingFlow
@StartableByRPC
public class TwoPartyFlow extends FlowLogic<Integer> {
	private Party counterparty;
	private Integer number;
	public void TwoPartyFlow(Party counterparty, Integer number) {
		this.counterparty = counterparty;
		this.number = number;
	}
	@Suspendable
	public  Integer call() throws FlowException {
		FlowSession session = initiateFlow(counterparty);
		session.send(number);

		int receivedIncrementedInteger = session.receive(Integer.class).unwrap(it -> it);

		return receivedIncrementedInteger;
	}

	}

