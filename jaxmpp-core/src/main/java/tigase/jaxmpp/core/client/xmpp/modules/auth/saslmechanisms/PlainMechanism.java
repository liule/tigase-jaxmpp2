/*
 * Tigase XMPP Client Library
 * Copyright (C) 2006-2012 "Bartosz Małkowski" <bartosz.malkowski@tigase.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. Look for COPYING file in the top folder.
 * If not, see http://www.gnu.org/licenses/.
 */
package tigase.jaxmpp.core.client.xmpp.modules.auth.saslmechanisms;

import tigase.jaxmpp.core.client.BareJID;
import tigase.jaxmpp.core.client.Base64;
import tigase.jaxmpp.core.client.SessionObject;
import tigase.jaxmpp.core.client.xmpp.modules.auth.AuthModule;
import tigase.jaxmpp.core.client.xmpp.modules.auth.CredentialsCallback;
import tigase.jaxmpp.core.client.xmpp.modules.auth.SaslMechanism;

public class PlainMechanism implements SaslMechanism {

	private static final String NULL = String.valueOf((char) 0);

	public PlainMechanism() {
	}

	@Override
	public String evaluateChallenge(String input, SessionObject sessionObject) {
		if (input == null) {
			CredentialsCallback callback = sessionObject.getProperty(AuthModule.CREDENTIALS_CALLBACK);
			if (callback == null)
				callback = new AuthModule.DefaultCredentialsCallback(sessionObject);
			BareJID userJID = sessionObject.getProperty(SessionObject.USER_BARE_JID);
			String lreq = userJID.toString() + NULL + userJID.getLocalpart() + NULL + callback.getCredential();

			String base64 = Base64.encode(lreq.getBytes());
			return base64;
		}
		return null;
	}

	@Override
	public boolean isAllowedToUse(final SessionObject sessionObject) {
		return sessionObject.getProperty(SessionObject.PASSWORD) != null
				&& sessionObject.getProperty(SessionObject.USER_BARE_JID) != null;
	}

	@Override
	public String name() {
		return "PLAIN";
	}

}