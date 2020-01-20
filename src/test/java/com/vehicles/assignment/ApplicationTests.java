package com.vehicles.assignment;

import com.vehicles.assignment.model.Vehicle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllVehicles() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/vehicles",
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetUserById() {
		Vehicle user = restTemplate.getForObject(getRootUrl() + "/vehicles/1", Vehicle.class);
		System.out.println(user.getMake());
		Assert.assertNotNull(user);
	}

	@Test
	public void testCreateUser() {
		Vehicle user = new Vehicle();
		user.setYear(2000);
		user.setMake("Toyota");
		user.setModel("Corolla");
		user.setCreatedBy("admin");

		ResponseEntity<Vehicle> postResponse = restTemplate.postForEntity(getRootUrl() + "/vehicles", user, Vehicle.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePost() {
		int id = 1;
		Vehicle vehicle = restTemplate.getForObject(getRootUrl() + "/vehicles/" + id, Vehicle.class);
		vehicle.setMake("Toyota");
		vehicle.setModel("Corolla");

		restTemplate.put(getRootUrl() + "/vehicles/" + id, vehicle);

		Vehicle updatedUser = restTemplate.getForObject(getRootUrl() + "/vehicles/" + id, Vehicle.class);
		Assert.assertNotNull(updatedUser);
	}

	@Test
	public void testDeletePost() {
		int id = 2;
		Vehicle vehicle = restTemplate.getForObject(getRootUrl() + "/vehicles/" + id, Vehicle.class);
		Assert.assertNotNull(vehicle);

		restTemplate.delete(getRootUrl() + "/vehicles/" + id);

		try {
			restTemplate.getForObject(getRootUrl() + "/vehicles/" + id, Vehicle.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}
