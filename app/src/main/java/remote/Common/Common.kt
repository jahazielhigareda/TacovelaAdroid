package remote.Common

import remote.api.IApi
import remote.api.RetrofitClient

object Common {
    val BASE_URL = "http://restaurantapi.somee.com/api/v1/"

    val api:IApi
    get() = RetrofitClient.getClient(BASE_URL).create(IApi::class.java)
}