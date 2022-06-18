package com.prototype.architecture.layer_04_infrastructure.github

import com.prototype.architecture.layer_03_domain.github.GithubReposEntity
import com.prototype.architecture.layer_03_domain.github.GithubReposRepository
import com.prototype.architecture.layer_03_domain.github.GithubUserName
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import java.net.URI

@Repository
class DefaultGithubReposRepository(
        private val restTemplate: RestTemplate
) : GithubReposRepository {

    override fun get(githubUserName: GithubUserName): List<GithubReposEntity>? {
        val uri = URI.create("https://api.github.com/users/${githubUserName.value}/repos")
        return restTemplate.getForObject(uri, Array<GithubReposEntity>::class.java)?.toList()
    }

}