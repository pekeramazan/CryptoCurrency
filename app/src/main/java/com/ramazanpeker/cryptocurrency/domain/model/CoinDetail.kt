package com.ramazanpeker.cryptocurrency.domain.model

import com.ramazanpeker.cryptocurrency.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String?,
    val name: String?,
    val description: String?,
    val symbol: String?,
    val rank: Int?,
    val isNew: Boolean?,
    val isActive: Boolean?,
    val tags: List<String?>?,
    val team: List<TeamMember>?
)
