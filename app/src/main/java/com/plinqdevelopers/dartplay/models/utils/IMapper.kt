package com.plinqdevelopers.dartplay.models.utils

interface IMapper<Domain, Network> {
    fun mapToDomain(network: Network): Domain
}
