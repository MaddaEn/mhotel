package hotel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import mhotel.model.Customer;

public class JavaRestClient {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8080/app/rs/customers/all/39");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		System.out.println(content.toString());
		// Jackson
		ObjectMapper objMapper = new ObjectMapper();
		Customer c = objMapper.readValue(content.toString(), Customer.class);
		System.out.println(c);
		System.out.println(objMapper.writeValueAsString(c));
		// { "id":1, "nume":"gigi"}

		TestJson tj = objMapper.readValue("{ \"cucu\":11, \"nume\":\"gigi\"}", TestJson.class);
		System.out.println(tj);

		url = new URL("https://earthquake.usgs.gov/fdsnws/event/1/count?format=geojson");
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		System.out.println(content.toString());
		
		url = new URL("http://localhost:8080/app/rs/customers/all");
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		System.out.println(content.toString());
		Customer[] customers = objMapper.readValue(content.toString(), Customer[].class);
		System.out.println(Arrays.toString(customers));
		
		List<Customer> myObjects = objMapper.readValue(content.toString(), new TypeReference<List<Customer>>(){});
		// List<Customer>.class
		System.out.println(myObjects);

	}

	static class TestJson {
		int id;
		String nume;

		@JsonProperty("cucu")
		public int getId() {
			return id;
		}

		@JsonProperty("cucu")
		public void setId(int id) {
			this.id = id;
		}

		public String getNume() {
			return nume;
		}

		public void setNume(String nume) {
			this.nume = nume;
		}

		@Override
		public String toString() {
			return "TestJson [id=" + id + ", nume=" + nume + "]";
		}

	}

}
