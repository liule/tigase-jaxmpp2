package tigase.jaxmpp.core.client.xmpp.modules.roster;

import java.util.ArrayList;

import tigase.jaxmpp.core.client.BareJID;

public class RosterItem {

	public static enum Subscription {
		both(true, true),
		from(true, false),
		none(false, false),
		remove(false, false),
		to(false, true);

		private final boolean sFrom;

		private final boolean sTo;

		private Subscription(boolean statusFrom, boolean statusTo) {
			this.sFrom = statusFrom;
			this.sTo = statusTo;
		}

		public boolean isFrom() {
			return this.sFrom;
		}

		public boolean isTo() {
			return this.sTo;
		}
	}

	private boolean ask;

	private final ArrayList<String> groups = new ArrayList<String>();

	private final BareJID jid;

	private String name;

	private Subscription subscription;

	public RosterItem(BareJID jid) {
		this.jid = jid;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof RosterItem))
			return false;
		return ((RosterItem) obj).jid.equals(this.jid);
	}

	public ArrayList<String> getGroups() {
		return groups;
	}

	public BareJID getJid() {
		return jid;
	}

	public String getName() {
		return name;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	@Override
	public int hashCode() {
		return jid.hashCode();
	}

	public boolean isAsk() {
		return ask;
	}

	public void setAsk(boolean ask) {
		this.ask = ask;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

}