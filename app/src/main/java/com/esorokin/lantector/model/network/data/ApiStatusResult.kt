package com.esorokin.lantector.model.network.data

enum class ApiStatusResult(val code: String = "") {
    SUCCESS("OK"),
    ERROR("ERROR");

    companion object {
        fun from(code: String): ApiStatusResult {
            ApiStatusResult.values().forEach { if (it.code == code) return it }
            return ERROR
        }
    }
}
