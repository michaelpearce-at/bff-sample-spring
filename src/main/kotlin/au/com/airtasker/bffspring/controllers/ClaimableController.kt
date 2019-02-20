package au.com.airtasker.bffspring.controllers

import au.com.airtasker.bffspring.bff.BffAdapter
import au.com.airtasker.bffspring.entities.Claimable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class ClaimableController @Autowired constructor(val adapter: BffAdapter) {

    @GetMapping("/bff/claimable")
    fun claimable(@RequestHeader(value = "X-Auth-Token") authToken: String): List<Claimable> {
        return adapter.claimable(authToken)
    }
}
