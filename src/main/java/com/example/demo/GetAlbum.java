package com.example.demo;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetAlbum {



  private static String id = "";
 
  private static final SpotifyApi spotifyApi = ClientCredential.return_API();

  private static GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(id)
//          .market(CountryCode.SE)
    .build();

    public void newRequest(String ids) {
      id = ids;
      getAlbumRequest = spotifyApi.getAlbum(id)
//          .market(CountryCode.SE)
    .build();

    }


  public static String getAlbum_Sync() {
    try {
      final Album album = getAlbumRequest.execute();

      System.out.println("Name: " + album.getName());
      return album.getName();
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
      return e.getMessage();
    }
  }

  public static void getAlbum_Async() {
    try {
      final CompletableFuture<Album> albumFuture = getAlbumRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Album album = albumFuture.join();

      System.out.println("Name: " + album.getName());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getAlbum_Sync();
    getAlbum_Async();
  }
}