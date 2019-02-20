package au.com.airtasker.bffspring.entities.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class RawClaimableResponse(val data: List<RawClaimable>)
