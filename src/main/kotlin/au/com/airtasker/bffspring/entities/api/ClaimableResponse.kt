package au.com.airtasker.bffspring.entities.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ClaimableResponse(val data: List<RawClaimable>)
