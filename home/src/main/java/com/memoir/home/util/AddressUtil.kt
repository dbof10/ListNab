package com.memoir.home.util

import com.memoir.home.model.Address

object AddressUtil {


    fun format(address: Address): String {
        val segments = listOf(address.street, address.locality, address.postalCode)
        return segments.filter {
            !it.isNullOrEmpty()
        }
            .joinToString(separator = ", ")
    }
}