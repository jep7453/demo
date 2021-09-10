package com.example.demo;

import com.google.gson.JsonObject;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.SnapshotResult;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import com.wrapper.spotify.requests.data.browse.GetRecommendationsRequest;
import com.wrapper.spotify.requests.data.playlists.*;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class CreateRecPlaylist {
    private static final SpotifyApi spotifyApi = ClientCredential.return_API();
    private static final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations().seed_genres("disco").build();
    private static final CreatePlaylistRequest createPlaylist = spotifyApi.createPlaylist(spotifyApi.getClientId(), "Temp").build();


    public static Playlist createPlaylist_Sync() {
        try {
            final Playlist playlist = createPlaylist.execute();

            return playlist;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static TrackSimplified[] getRecommendations_Sync() {
        try {
            final Recommendations recommendations = getRecommendationsRequest.execute();

            return recommendations.getTracks();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


    public static void main(String[] args) {
        // Get Rec tracks uris
        TrackSimplified[] tracks = getRecommendations_Sync();
        String[] trackUris = new String[tracks.length];
        for (int i = 0; i < tracks.length; i++) {
            trackUris[i] = tracks[i].toString();
        }
        //Request that spotify add to our created playlist and add the Rec tracks
        try {
            SnapshotResult snapshotResult = spotifyApi.addItemsToPlaylist(createPlaylist_Sync().getId(), trackUris).build().execute();

            System.out.println(snapshotResult);
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
