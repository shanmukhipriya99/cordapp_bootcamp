package bootcamp;

import com.google.common.collect.ImmutableList;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;

import java.util.List;

public class ContainerState implements ContractState {
	private int width;
	private int height;
	private int depth;

	private String contents;

	private Party owner;
	private Party carrier;

	public ContainerState(int width, int height, int depth, String contents, Party owner, Party carrier) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.contents = contents;
		this.owner = owner;
		this.carrier = carrier;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getDepth() {
		return depth;
	}

	public List<AbstractParty> getParticipants() {
		return ImmutableList.of(owner);
	}

	public static void main(String[] args) {
		Party jetpackImporters = null;
		Party jetpackCarriers = null;

		ContainerState container = new ContainerState(2, 4, 2, "Jetpacks", jetpackImporters, jetpackCarriers);

	}



}
