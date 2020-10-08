package com.spring.webflux.webclient;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.spring.webflux.webclient.payload.RepoRequest;

import reactor.core.publisher.Mono;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GithubRepo_UpdateRepo_Test {

	@Autowired
	private WebTestClient webTestClient;


	@Test
	public void test4EditGithubRepository() {
		RepoRequest newRepoDetails = new RepoRequest("updated-webclient-repository", "Updated name and description");
		webTestClient.patch()
				.uri("/api/repos/{repo}", "test-webclient-repository")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(newRepoDetails), RepoRequest.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody()
				.jsonPath("$.name").isEqualTo("updated-webclient-repository");
	}

}
