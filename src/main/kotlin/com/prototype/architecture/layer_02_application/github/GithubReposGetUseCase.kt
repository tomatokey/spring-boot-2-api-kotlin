package com.prototype.architecture.layer_02_application.github

import com.prototype.architecture.layer_03_domain.github.GithubReposEntity
import com.prototype.architecture.layer_03_domain.github.GithubReposRepository
import com.prototype.architecture.layer_03_domain.github.GithubUserName
import org.springframework.stereotype.Service

@Service
class GithubReposGetUseCase(
    private val githubReposRepository: GithubReposRepository
) {
    fun invoke(githubUserName: GithubUserName): List<GithubReposEntity>? {
        return githubReposRepository.get(githubUserName)
    }
}