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
public class GithubRepo_Create_Test {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void test1CreateGithubRepository() {
		RepoRequest repoRequest = new RepoRequest("test-webclient-repository", "Repository created for testing WebClient");

		webTestClient.post().uri("/api/repos")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.body(Mono.just(repoRequest), RepoRequest.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
				.expectBody()
				.jsonPath("$.name").isNotEmpty()
				.jsonPath("$.name").isEqualTo("test-webclient-repository");
	}

}
