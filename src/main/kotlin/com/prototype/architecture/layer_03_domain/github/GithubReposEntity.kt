package com.prototype.architecture.layer_03_domain.github

data class GithubReposEntity(
    val id: GithubRepoId,
    val name: GithubRepoName,
    val private: Boolean
)