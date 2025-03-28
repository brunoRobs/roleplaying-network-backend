package com.roleplay_network.rpn.services;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class SheetService {
  final String directory = "sheets";

  public byte[] find(String name) {
    Resource pdfFile = new ClassPathResource(String.format("/%s/%s.pdf", directory, name));
    try {
      byte[] characterSheet = Files.readAllBytes(pdfFile.getFile().toPath());
      return characterSheet;
    } catch (IOException e) {
      return null;
    }
  }
}
