package com.prototype.architecture.layer_01_presentation.github

import com.prototype.architecture.layer_02_application.github.GithubReposGetUseCase
import com.prototype.architecture.layer_03_domain.github.GithubUserName
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/github/repos/{githubUserName}")
class GithubReposController(
        private val githubReposGetUseCase: GithubReposGetUseCase
) {

    @GetMapping
    fun get(@PathVariable("githubUserName") githubUserName: GithubUserName): List<GithubReposResponse>? {
        return githubReposGetUseCase.invoke(githubUserName)
                ?.map { GithubReposResponse(it.id, it.name, it.private) }
    }

}