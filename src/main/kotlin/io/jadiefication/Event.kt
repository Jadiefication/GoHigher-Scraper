package io.jadiefication

data class Event(
    val name: String,
    val date: String,
    val description: String,
    val type: String,
    val realizator: String,
    val url: String,
    val fields: List<String>,
    val location: String
) {
    override fun toString(): String {
        return """
            |Event Details:
            |  Name       : $name
            |  Date       : $date
            |  Description: $description
            |  Type       : $type
            |  Realizator : $realizator
            |  URL        : $url
            |  Fields     : ${fields.joinToString(", ")}
            |  Location   : ${location.ifEmpty { "Nespecifikovaná" }}
        """.trimMargin()
    }
}
