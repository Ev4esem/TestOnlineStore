package com.example.testonlinestore.data.retrofit

import com.example.testonlinestore.domain.mapper.CatalogDetailDto
import com.example.testonlinestore.domain.mapper.CatalogListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogAPI {

    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getProducts() : CatalogListDto

    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010/{id}")
    suspend fun getProductById(
        @Path("id") id : String
    ) : CatalogDetailDto

}