package io.jadiefication

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

object ChannelCommand : ListenerAdapter() {

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.guild == null) {
            return;
        }
        when (event.name) {
            "channel" -> {
                val channel = event.getOption("channel")?.asChannel
                id = channel?.id?.toLong() ?: id
                command()
                event.reply("New channel's id is: $id").queue()
            }
            else -> {
                event.reply("Unknown command").queue()
            }
        }
    }
}