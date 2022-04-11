package org.quaerense.spacewanderers.data.network

import org.quaerense.spacewanderers.data.network.model.MainObjectDto
import org.quaerense.spacewanderers.data.network.model.AsteroidDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("browse")
    suspend fun getAllAsteroids(
        @Query(PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(PARAM_PAGE) page: Int
    ): MainObjectDto

    @GET("{id}")
    suspend fun getAsteroid(
        @Path("id") id: Int,
        @Query(PARAM_API_KEY) apiKey: String = API_KEY
    ): AsteroidDto

    companion object {

        private const val PARAM_API_KEY = "api_key"
        private const val PARAM_PAGE = "page"

        private const val API_KEY = "dbZ7hy4dnezmjqAJ1uAJ3W7Ay7QqbWIWsSopVBfa"
//        private const val API_KEY = "TB75BqGuPpDDzMLmVMOxdzde9Vsr7uLUCMDgfUBP"
    }
}