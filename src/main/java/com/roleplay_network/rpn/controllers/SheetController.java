package com.roleplay_network.rpn.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roleplay_network.rpn.services.SheetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/sheets")
public class SheetController {
  @Autowired
  SheetService sheetService;

  @GetMapping("/show/{name}")
  public ResponseEntity<?> showSheet(@PathVariable String name) {
    byte[] sheet = sheetService.find(name);
    if (sheet != null) {
      HttpHeaders header = new HttpHeaders();
      header.setContentType(MediaType.APPLICATION_PDF);
      header.setContentDisposition(ContentDisposition.builder("inline").filename(name).build());
      return new ResponseEntity<>(sheet, header, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/download/{name}")
  public ResponseEntity<?> downloadSheet(@PathVariable String name) {
    byte[] sheet = sheetService.find(name);
    if (sheet != null) {
      HttpHeaders header = new HttpHeaders();
      header.setContentType(MediaType.APPLICATION_PDF);
      header.setContentDisposition(ContentDisposition.builder("attachment").filename(name).build());
      return new ResponseEntity<>(sheet, header, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
