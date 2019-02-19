package au.com.airtasker.bffspring

import au.com.airtasker.bffspring.entities.Claimable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class ClaimableController {

    private val adapter = BffAdapter()

    @GetMapping("/bff/claimable")
    fun claimable(@RequestHeader(value = "X-Auth-Token") authToken: String): List<Claimable> {
        return adapter.claimable(authToken)
    }
}
