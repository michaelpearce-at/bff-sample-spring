package au.com.airtasker.bffspring.bff

import au.com.airtasker.bffspring.entities.Claimable
import au.com.airtasker.bffspring.entities.api.RawClaimableResponse
import au.com.airtasker.bffspring.entities.api.toClaimable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class BffAdapter @Autowired constructor(restTemplateBuilder: RestTemplateBuilder) {
    private var restTemplate: RestTemplate = restTemplateBuilder.build()

    fun claimable(authToken: String): List<Claimable> {
        val headers = HttpHeaders()
        headers.set("X-Auth-Token", authToken)
        val entity: HttpEntity<String> = HttpEntity("parameters", headers)
        val responseEntity: ResponseEntity<RawClaimableResponse> =
                restTemplate.exchange("https://api.dev.airtasker.com/api/v2/removals/claimable",
                        HttpMethod.GET,
                        entity,
                        RawClaimableResponse::class.java)

        return responseEntity.body?.data?.map {
            it.toClaimable()
        } ?: throw IllegalStateException("Could not get data")
    }
}
