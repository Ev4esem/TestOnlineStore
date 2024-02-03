package com.example.testonlinestore.domain.retrofit

import com.example.testonlinestore.domain.model.catalog.Catalog
import retrofit2.Response
import retrofit2.http.GET

interface CatalogAPI {

    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getProducts() : Response<List<Catalog>>

}