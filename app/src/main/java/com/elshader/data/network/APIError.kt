package com.elshader.data.network


data class APIError (

        val message : String,
        val errors : Errors
)

data class Errors (

        val email : List<String>
)