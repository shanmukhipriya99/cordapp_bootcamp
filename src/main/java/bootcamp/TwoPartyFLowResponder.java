package bootcamp;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.FlowSession;
import net.corda.core.flows.InitiatedBy;
import net.corda.core.identity.Party;
import net.corda.core.internal.FetchDataFlow;
import net.corda.core.node.ServiceHub;

import java.util.List;

@InitiatedBy(TwoPartyFlow.class)
public class TwoPartyFLowResponder extends FlowLogic<Void> {
	private FlowSession counterpartySession;

	public TwoPartyFLowResponder(FlowSession counterpartSession) {
		this.counterpartySession = counterpartSession;
	}
	@Suspendable
	public Void call() throws FlowException {
		ServiceHub serviceHub = getServiceHub();
		List<StateAndRef<HouseState>> statesFromVault = serviceHub.getVaultService().queryBy(HouseState.class).getStates();
		int receivedInt =  counterpartySession.receive(Integer.class).unwrap(it -> {
			if (it > 3) throw new IllegalArgumentException("Number too high");
			return it;
		});

		int receivedIntPlusOne = receivedInt + 1;
		counterpartySession.send(receivedIntPlusOne);
		return null;
	}
}
