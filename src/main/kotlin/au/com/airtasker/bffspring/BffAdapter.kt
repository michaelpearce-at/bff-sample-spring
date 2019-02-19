package au.com.airtasker.bffspring

import au.com.airtasker.bffspring.entities.Claimable
import au.com.airtasker.bffspring.entities.api.ClaimableResponse
import au.com.airtasker.bffspring.entities.api.toClaimable
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

class BffAdapter {
    fun claimable(authToken: String): List<Claimable> {
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.set("X-Auth-Token", authToken)
        val entity: HttpEntity<String> = HttpEntity("parameters", headers)
        val responseEntity: ResponseEntity<ClaimableResponse> =
                restTemplate.exchange("https://api.dev.airtasker.com/api/v2/removals/claimable",
                        HttpMethod.GET,
                        entity,
                        ClaimableResponse::class.java)

        return responseEntity.body?.data?.map {
            it.toClaimable()
        } ?: throw IllegalStateException("Could not get data")
    }
}
