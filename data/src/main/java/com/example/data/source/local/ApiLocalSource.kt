package com.example.data.source.local

import com.example.domain.model.PublicationModel

class ApiLocalSource {

    private val _cachePublications: MutableMap<Int, PublicationModel> = mutableMapOf()

    fun cachePublication(input: PublicationModel) {
        _cachePublications[input.id] = input
    }

    fun getCachePublication(id: Int) = _cachePublications[id]

}