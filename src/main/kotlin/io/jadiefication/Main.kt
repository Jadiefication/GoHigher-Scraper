package io.jadiefication

import io.github.cdimascio.dotenv.dotenv
import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.html5.a
import it.skrape.selects.html5.div
import it.skrape.selects.html5.span
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.interactions.InteractionContextType
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder
import java.util.EnumSet
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

val dotenv = dotenv() // loads .env from project root
val scheduler: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
val token: String = dotenv["BOT_KEY"]!!

val interestingFields = listOf(
    "Automatizace a digitalizace",
    "Medicínská informatika",
    "Programování",
    "Robotika",
    "Umělá inteligence",
    "Virtuální realita"
)

val jda = JDABuilder.create(token,
    EnumSet<GatewayIntent>.of(GatewayIntent.GUILD_MESSAGES,
        GatewayIntent.MESSAGE_CONTENT,
        GatewayIntent.DIRECT_MESSAGES
    )
)
    .addEventListeners(ChannelCommand)
    .build()
var id = 1461838274615050261

val command = {
    val scrapedEvents = skrape(HttpFetcher) {
        request {
            url = "https://www.podporatalentu.cz/databaze-aktivit/"
            timeout = 15_000
        }
        response {
            htmlDocument {
                div {
                    withClass = "event_box"
                    findAll {
                        this.map { it ->
                            val titleElement = it.div { withClass = "title"; findFirst { this } }
                            val eventFootObory = it.div { withClass = "event-obory"; findFirst { this.div { withClass = "foot-item"; findFirst { this } } } }
                            val eventBottom = it.div { withClass = "event-bottom"; findFirst { this } }

                            Event(
                                name = titleElement.a { findFirst { text } },
                                date = it.span { withClass = "date_info"; findFirst { text } },
                                description = it.div { withClass = "event-desciption"; findFirst { text } },
                                type = titleElement.span { withClass = "item_typ_aktivity"; findFirst { text } },
                                realizator = eventBottom.findAll(".foot-item").last().text.replace("Realizátor:", "").trim(),
                                url = titleElement.a { findFirst { attributes["href"] ?: "" } },
                                fields = try {
                                    eventFootObory.findAll(".link_").map { it.span { findFirst { text.trim() } } }
                                } catch (_: Exception) {
                                    listOf(eventFootObory.text.substringAfter("Obory:"))
                                },
                                location = try {
                                    it.span { withClass = "location_info"; findFirst { text } }
                                } catch (_: Exception) {
                                    ""
                                }.trim()
                            )
                        }
                    }
                }
            }
        }
    }

    val channel = jda.getForumChannelById(id)
    val filteredEvents = scrapedEvents.filter { event ->
        event.fields.any { field -> interestingFields.contains(field) }
    }
    filteredEvents.forEach {
        if (channel?.threadChannels?.any { post -> post.name == it.name } != true) {
            channel?.createForumPost(
                it.name, MessageCreateBuilder()
                .apply {
                    setContent(it.toString())
                }
                .build())?.queue()
        }
    }
}

fun main() {
    val commands = jda.updateCommands()
    commands.addCommands(Commands.slash("channel", "Set the forms channel of the bot")
        .addOptions(OptionData(OptionType.CHANNEL, "channel", "The channel the bot will write to", true))
        .setContexts(InteractionContextType.GUILD)
        .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
    )
    commands.queue()
    scheduler.scheduleAtFixedRate(command, 0,
        7,
        TimeUnit.DAYS)
}