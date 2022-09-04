package com.example.addon.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import meteordevelopment.meteorclient.systems.commands.Command;
import meteordevelopment.meteorclient.systems.friends.Friend;
import meteordevelopment.meteorclient.systems.friends.Friends;
import meteordevelopment.meteorclient.utils.player.ChatUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class CommandExample extends Command {
    public CommandExample() {
        super("friendall", "friend all online players.");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(ctx -> {
            execute();
            return SINGLE_SUCCESS;
        });
    }

    private void execute() {
        assert mc.player != null;
        ChatUtils.sendMsg(Text.of("Adding all friends, will lag."));
        mc.player.networkHandler.getPlayerList().forEach(player -> {
            GameProfile profile = player.getProfile();
            Friends.get().add(new Friend(profile.getName(), profile.getId()));
        });
        ChatUtils.sendMsg(Text.of("Added all online players as a friend."));
    }
}
