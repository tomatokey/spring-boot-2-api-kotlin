package com.prototype.architecture.layer_03_domain.github

interface GithubReposRepository {
    fun get(githubUserName: GithubUserName): List<GithubReposEntity>?
}