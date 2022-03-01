package com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.services;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.exceptions.ControlledException;
import com.specialagentsqueaky.twitchchannelpointrewardmousekeyboardcontroller.exceptions.ScriptException;

public class TwitchService {

    private final LogService logService;
    private final ChannelRewardService channelRewardService;

    public TwitchService(LogService logService, ChannelRewardService channelRewardService) {
        this.logService = logService;
        this.channelRewardService = channelRewardService;
    }

    public void start(String channelID) {

        TwitchClientBuilder clientBuilder = TwitchClientBuilder.builder();

//        OAuth2Credential credential = new OAuth2Credential(
//                "twitch",
//                "accessToken"
//        );

        //region TwitchClient
        TwitchClient client = clientBuilder
//                .withEnableChat(true)
                .withEnablePubSub(true)
                .build();

        client.getPubSub().listenForChannelPointsRedemptionEvents(null, channelID);

        client.getEventManager().onEvent(RewardRedeemedEvent.class, (RewardRedeemedEvent event) -> {
            try {
                String user = event.getRedemption().getUser().getDisplayName();
                String rewardID = event.getRedemption().getReward().getId();
                this.channelRewardService.perform(user, rewardID);
            } catch (ControlledException | ScriptException exception) {
                System.out.println("");
                System.out.println("ERROR: " + exception.getMessage());
                System.out.println("");
            } catch (Exception exception) {
                this.logService.exception(exception);
            }
        });

    }

}
