package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AlbumController {

  @GetMapping("/album")
  public String albumForm(Model model) {
    model.addAttribute("album", new Album());
    return "album";
  }

  @PostMapping("/album")
  public String albumSubmit(@ModelAttribute Album album, Model model) {
    model.addAttribute("album", album);
    return "albumResult";
  }

}
