package com.prototype.architecture.layer_01_presentation.github

import com.prototype.architecture.layer_03_domain.github.GithubRepoId
import com.prototype.architecture.layer_03_domain.github.GithubRepoName

data class GithubReposResponse(
    val id: GithubRepoId,
    val name: GithubRepoName,
    val private: Boolean
)