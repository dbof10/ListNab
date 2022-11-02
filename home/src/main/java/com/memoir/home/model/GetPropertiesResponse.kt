package com.memoir.home.model

data class GetPropertiesResponse(val results: List<Property>)

data class Property(
    val id: String, val listerBranding: Branding?, val listing: Listing,
    @Transient val isFavorite: Boolean?
) {
    companion object {
        val EMPTY = Property(
            "id", null, Listing(
                "listing",
                Price("CHF", Buy(0.0)),
                Address(null, "location", ""),
                Localization(De(emptyList(), Text("title")))
            ), false
        )
    }
}

data class Branding(val logoUrl: String, val name: String, val legalName: String)

data class Address(val street: String?, val locality: String, val postalCode: String)

data class Listing(
    val id: String,
    val prices: Price,
    val address: Address,
    val localization: Localization
)

data class Price(val currency: String, val buy: Buy)

data class Buy(val price: Double)

data class Localization(val de: De)

data class De(val attachments: List<Attachment>, val text: Text)

data class Attachment(val url: String)

data class Text(val title: String)