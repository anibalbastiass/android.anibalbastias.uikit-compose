package com.anibalbastias.uikitcompose.components.templates.listoflements

data class Element(
    val text: String,
    val onElementSelect: () -> Unit
)