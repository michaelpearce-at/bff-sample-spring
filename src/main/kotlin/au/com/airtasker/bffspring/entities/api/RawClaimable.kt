package au.com.airtasker.bffspring.entities.api

import au.com.airtasker.bffspring.entities.Claimable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class RawClaimable(
        val id: String,
        val title: String,
        val priceDetails: RawPriceDetails,
        val dueAt: LocalDate
)

data class RawPriceDetails(
        val valueSubunits: Int,
        val currencyCode: String
)

fun RawClaimable.toClaimable(): Claimable {
    return Claimable(
            id = this.id,
            title = this.title,
            price = this.priceDetails.toFormattedPrice(),
            dueAt = this.dueAt.toFormattedDate()
    )
}

private val dateFormatter by lazy { DateTimeFormatter.ofPattern("d MMM uuuu") }

private fun LocalDate.toFormattedDate(): String {
    return this.format(dateFormatter)
}

private fun RawPriceDetails.toFormattedPrice(): String {
    return NumberFormat.getCurrencyInstance().run {
        currency = Currency.getInstance(this@toFormattedPrice.currencyCode)
        format(BigDecimal(this@toFormattedPrice.valueSubunits).movePointLeft(currency.defaultFractionDigits))
    }
}
