package org.act.arch.client.security;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONClient {

	/**
	 * Populates Token object using following JSON String { "token_type":
	 * "bearer", "expires_in": 3600000, "refresh_token":
	 * "f43de118a489d56c3b3b7ba77a1549e", "access_token":
	 * "269becaec9b8b292906b3f9e69b5a9" }
	 * 
	 * @param accessTokenJson
	 * @return
	 */
	public static Token getAccessToken(String accessTokenJson) {
		JSONParser parser = new JSONParser();

		Token token = new Token();
		try {
			Object obj = parser.parse(accessTokenJson);
			JSONObject jsonObject = (JSONObject) obj;
			token.setAccessToken((String) jsonObject.get("access_token"));
			long expiresIn = ((Long) jsonObject.get("expires_in")).intValue();
			token.setExpiresIn(expiresIn);
			token.setRefreshToken((String) jsonObject.get("refresh_token"));
			token.setTokenType((String) jsonObject.get("token_type"));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return token;

	}
}