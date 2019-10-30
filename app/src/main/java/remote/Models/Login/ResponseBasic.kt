package remote.Models.Login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseBasic {

    @SerializedName("success")
    @Expose
    var Success: Boolean? = null
    @SerializedName("errors")
    @Expose
    var Errors: List<String>? = null
    @SerializedName("data")
    @Expose
    var Data: User? = null
}