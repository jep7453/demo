package com.example.demo;



public class Album {



  private String id;

  private String name;



  public String getId() {

    return id;

  }



  public void setId(String id) {

    this.id = id;
    findAlbumName();

  }

  public void findAlbumName() {
      GetAlbum getAlbum = new GetAlbum();
      getAlbum.newRequest(id);
      setName(GetAlbum.getAlbum_Sync());

  }


  public String getName() {

    return name;

  }



  public void setName(String name) {

    this.name = name;

  }



}