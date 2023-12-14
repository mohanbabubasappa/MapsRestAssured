package graphqltests;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import static org.junit.Assert.*;

public class GraphQLTesting {

	static String queryResponse;
	static String mutationResponse;
	static JsonPath js;
	public static void main(String[] args) {
		
		int characterId=3124;
		int locationId=3245;
		int episodeId=31;
		String name="rahul";

		queryResponse=given()
			.log().all()
			.header("COntent-Type","application/json")
			.body("{\"query\":\"query ($characterId: Int!, $locationId: Int!, $episodeId: Int!, $name: String) {\\n  "
					+ "character(characterId: $characterId) {\\n    name\\n    type\\n    status\\n    gender\\n  }\\n  "
					+ "location(locationId: $locationId) {\\n    name\\n    dimension\\n    created\\n    residents {\\n      "
					+ "name\\n      id\\n      status\\n    }\\n  }\\n  episode(episodeId: $episodeId) {\\n    name\\n    "
					+ "air_date\\n    episode\\n    created\\n  }\\n  characters(filters: {name: $name}) {\\n    info {\\n      "
					+ "count\\n    }\\n    result {\\n      id\\n      name\\n    }\\n  }\\n  episodes(filters: {name: \\\"hulu\\\"}) "
					+ "{\\n    info {\\n      count\\n      pages\\n    }\\n    result {\\n      id\\n      name\\n      "
					+ "characters {\\n        id\\n        name\\n        status\\n      }\\n    }\\n  }\\n}\\n\","
					+ "\"variables\":{\"characterId\":"+characterId+",\"locationId\":"+locationId+",\"episodeId\":"+episodeId+",\"name\":\""+name+"\"}}")
		.when()
			.get("https://rahulshettyacademy.com/gq/graphql")
		.then()
			.log().all()
			.extract().response().asString();
		js=new JsonPath(queryResponse);
		String actualLocationName=js.getString("data.location.name");
		assertEquals(actualLocationName,"Munich");
		
		String myName="Bharatdesh";
		String type="Hindu";
		mutationResponse=given()
			.log().all()
			.header("Content-Type","application/json")
			.body("{\"query\":\"mutation ($name: String!, $type: String!, $dimension: String!, $air_date: String!, $episode: String!) "
					+ "{\\n  createLocation(location: {name: $name, type: $type, dimension: $dimension}) {\\n    id\\n  }\\n  "
					+ "createEpisode(episode: {name: $name, air_date: $air_date, episode: $episode}) {\\n    id\\n  }\\n  "
					+ "deleteLocations(locationIds: [4299, 4298]) {\\n    locationsDeleted\\n  }\\n  deleteEpisodes(episodeIds: [3113, 3112]) "
					+ "{\\n    episodesDeleted\\n  }\\n}\\n\",\"variables\":{\"name\":\""+myName+"\",\"type\":\""+type+"\",\"dimension\":\"999\","
					+ "\"air_date\":\"9-9-9999\",\"episode\":\"001\"}}")
		.when()
			.post("https://rahulshettyacademy.com/gq/graphql")
		.then()
		.log().all()
		.extract().response().asString();
		js=new JsonPath(mutationResponse);
		int actualLocationsDeleted=js.get("data.deleteLocations.locationsDeleted");
		assertEquals(actualLocationsDeleted,0);
	}

}
