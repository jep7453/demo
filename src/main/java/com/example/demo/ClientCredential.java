package com.example.demo;



import com.wrapper.spotify.SpotifyApi;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import com.wrapper.spotify.model_objects.credentials.ClientCredentials;

import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import org.apache.hc.core5.http.ParseException;



import java.io.IOException;

import java.util.concurrent.CancellationException;

import java.util.concurrent.CompletableFuture;

import java.util.concurrent.CompletionException;

import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;




public class ClientCredential {

  private static final String clientId = "6ffddbc222ce4a4192cd716e4b1a8f70";

  private static final String clientSecret = "04876b9104f0410abb758abe2ad9603e";

  private static final String id = "5zT1JLIj9E57p3e1rFm9Uq";



  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()

    .setClientId(clientId)

    .setClientSecret(clientSecret)

    .build();

  private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()

    .build();



  public static void clientCredentials_Sync() {

    try {

      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();



      // Set access token for further "spotifyApi" object usage

      spotifyApi.setAccessToken(clientCredentials.getAccessToken());



      System.out.println("Expires in: " + clientCredentials.getExpiresIn());

    } catch (IOException | SpotifyWebApiException | ParseException e) {

      System.out.println("Error: " + e.getMessage());

    }

  }



  public static void clientCredentials_Async() {

    try {

      final CompletableFuture<ClientCredentials> clientCredentialsFuture = clientCredentialsRequest.executeAsync();



      // Thread free to do other tasks...



      // Example Only. Never block in production code.

      final ClientCredentials clientCredentials = clientCredentialsFuture.join();



      // Set access token for further "spotifyApi" object usage

      spotifyApi.setAccessToken(clientCredentials.getAccessToken());



      System.out.println("Expires in: " + clientCredentials.getExpiresIn());

    } catch (CompletionException e) {

      System.out.println("Error: " + e.getCause().getMessage());

    } catch (CancellationException e) {

      System.out.println("Async operation cancelled.");

    }

  }

  public static SpotifyApi return_API(){
    clientCredentials_Sync();

    clientCredentials_Async();
    return spotifyApi;
  }





  public static void main(String[] args) {

    clientCredentials_Sync();

    clientCredentials_Async();
  

  }

}