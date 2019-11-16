package com.uhc.domain.exception

class DefaultException(
    override val message: String = "Unexpected Error"
) : Exception()