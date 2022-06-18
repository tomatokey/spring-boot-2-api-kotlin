package com.prototype.architecture.layer_03_domain.github

import com.prototype.architecture.layer_03_domain.SingleValueObject

data class GithubUserName(override val value: String) : SingleValueObject<String>