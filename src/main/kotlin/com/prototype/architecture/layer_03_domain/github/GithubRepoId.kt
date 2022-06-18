package com.prototype.architecture.layer_03_domain.github

import com.prototype.architecture.layer_03_domain.SingleValueObject

data class GithubRepoId(override val value: Int) : SingleValueObject<Int>