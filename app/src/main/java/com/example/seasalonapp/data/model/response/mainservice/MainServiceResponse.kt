import com.example.seasalonapp.data.model.response.mainservice.Services

data class MainServiceResponse(
    val meta: Meta,
    val data: ServiceData
)

data class Meta(
    val code: Int,
    val status: String,
    val message: String
)

data class ServiceData(
    val data: List<Services>
)